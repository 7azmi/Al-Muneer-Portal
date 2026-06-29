"""Shared helpers for full UAT execution."""

from __future__ import annotations

import json
import random
import re
import subprocess
from dataclasses import dataclass, field, asdict
from datetime import datetime, timedelta, timezone
from pathlib import Path
from typing import Any

from playwright.sync_api import Page, BrowserContext

BASE_URL = "http://localhost:8080"
ROOT = Path(__file__).resolve().parent
SCREENSHOTS = ROOT / "uat-screenshots"
FIGURES = ROOT / "uat-figures"
RESULTS_JSON = ROOT / "uat_results.json"

ADMIN_USER = "admin"
ADMIN_PASS = "admin123"
DB_ENV = {"PGPASSWORD": "almuneer_dev"}


@dataclass
class StepResult:
    step: int
    action: str
    expected: str
    actual: str
    passed: bool
    figure: str | None = None
    screenshot: str | None = None


@dataclass
class CaseResult:
    case_id: str
    name: str
    module: str
    status: str  # PASS, FAIL, BLOCKED, SKIP
    steps: list[StepResult] = field(default_factory=list)
    comment: str = ""
    performed_by: str = "tester"


class FigureCounter:
    def __init__(self) -> None:
        self._section = 0
        self._sub = 0
        self._letter = ord("a") - 1
        self._map: dict[str, str] = {}

    def next(self, case_id: str, step: int) -> str:
        key = f"{case_id}_s{step}"
        if key in self._map:
            return self._map[key]
        self._letter += 1
        if self._letter > ord("z"):
            self._letter = ord("a")
        label = f"Figure {self._section}.{self._sub} ({chr(self._letter)})"
        self._map[key] = label
        return label

    def new_section(self, section_num: int, title: str = "") -> None:
        self._section = section_num
        self._sub = 0
        self._letter = ord("a") - 1

    def new_subsection(self, sub: int) -> None:
        self._sub = sub
        self._letter = ord("a") - 1


class HumanVoice:
  """Yemeni-flavoured tester notes — lowercase, occasional typos, arabic mix."""

  PASS = [
    "ok worked fine",
    "تمام no issues",
    "all good يعني",
    "كويس everything matched",
    "الحمد لله passed",
    "looks correct to me",
    "fine — same as expected",
    "ما شاء الله smooth",
    "didnt see any problem",
    "زين تمام",
  ]
  PASS_DETAIL = [
    "redirect was fast, dashboard loaded ok",
    "faq opened when i clicked — first one was already open",
    "calendar colours make sense, booked date shows red",
    "got the 9-digit code on confirmation الحمد لله",
    "upload went through, saw it in admin payments",
    "ai panel loaded after few seconds — api key is placeholder but page didnt freeze",
    "whatsapp template preview filled the name placeholder كويس",
  ]
  FAIL = [
    "didnt work as expected",
    "something wrong here",
    "not matching std يعني",
    "failed — need to check",
  ]
  BLOCKED = [
    "couldnt test — gallery not empty on local db",
    "skipped bc owner data still there",
    "prereq not met on my machine",
    "didnt want to delete all tiers يعني",
  ]
  MINOR = [
    "small ui thing but function ok",
    "took a sec to load on my laptop",
    "nav link still shows faq even when section hidden — noted",
    "typo in flash message? anyway it worked",
  ]

  @classmethod
  def pick(cls, pool: list[str], extra: str = "") -> str:
    base = random.choice(pool)
    if extra:
      return f"{base}. {extra}".lower()
    return base.lower()


def future_date(days: int = 45) -> str:
    d = datetime.now() + timedelta(days=days)
    return d.strftime("%Y-%m-%d")


def shot(page: Page, filename: str, full_page: bool = False) -> str:
    SCREENSHOTS.mkdir(parents=True, exist_ok=True)
    path = SCREENSHOTS / filename
    page.screenshot(path=str(path), full_page=full_page)
    return f"uat-screenshots/{filename}"


def capture_figure(
    page: Page,
    figures: FigureCounter,
    case_id: str,
    step: int,
    filename: str,
) -> tuple[str, str]:
    fig_label = figures.next(case_id, step)
    rel = shot(page, filename)
    FIGURES.mkdir(parents=True, exist_ok=True)
    dest = FIGURES / filename
    src = ROOT / rel
    if src.exists():
        dest.write_bytes(src.read_bytes())
    return fig_label, rel


def add_step(
    steps: list[StepResult],
    step: int,
    action: str,
    expected: str,
    actual: str,
    passed: bool,
    page: Page | None = None,
    figures: FigureCounter | None = None,
    case_id: str = "",
    screenshot_name: str | None = None,
) -> None:
    figure = None
    screenshot = None
    if page and screenshot_name and figures:
        figure, screenshot = capture_figure(page, figures, case_id, step, screenshot_name)
    elif page and screenshot_name:
        screenshot = shot(page, screenshot_name)
    steps.append(StepResult(step, action, expected, actual, passed, figure, screenshot))


def sql_query(query: str) -> list[str]:
    try:
        out = subprocess.run(
            [
                "psql", "-h", "localhost", "-U", "almuneer", "-d", "almuneer_portal",
                "-t", "-A", "-c", query,
            ],
            capture_output=True,
            text=True,
            env={**DB_ENV},
            timeout=15,
        )
        if out.returncode != 0:
            return []
        return [line.strip() for line in out.stdout.splitlines() if line.strip()]
    except Exception:
        return []


def sql_one(query: str) -> str | None:
    rows = sql_query(query)
    return rows[0] if rows else None


def goto(page: Page, url: str, timeout: int = 20000) -> None:
    page.goto(url, wait_until="domcontentloaded", timeout=timeout)


def settle(page: Page, ms: int = 400) -> None:
    """Short pause instead of networkidle (charts/ai keep network busy)."""
    page.wait_for_timeout(ms)


def admin_login(page: Page) -> None:
    goto(page, f"{BASE_URL}/admin/login")
    page.fill("#username", ADMIN_USER)
    page.fill("#password", ADMIN_PASS)
    page.click("#login-submit")
    page.wait_for_url("**/admin/dashboard**", timeout=15000)
    settle(page)


def admin_logout(context: BrowserContext) -> None:
    context.clear_cookies()


def visitor_clear_cookies(context: BrowserContext) -> None:
    context.clear_cookies()


def accept_dialogs(page: Page) -> None:
    def _handle(dialog) -> None:
        try:
            dialog.accept()
        except Exception:
            pass
    page.on("dialog", _handle)


def case_status(steps: list[StepResult], blocked: bool = False) -> str:
    if blocked:
        return "BLOCKED"
    if not steps:
        return "SKIP"
    if all(s.passed for s in steps):
        return "PASS"
    if any(s.passed for s in steps):
        return "FAIL"
    return "FAIL"


def make_case(
    case_id: str,
    name: str,
    module: str,
    steps: list[StepResult],
    comment: str = "",
    blocked: bool = False,
    performed_by: str = "ahmed (local tester)",
) -> CaseResult:
    status = case_status(steps, blocked)
    if not comment:
        if status == "PASS":
            comment = HumanVoice.pick(HumanVoice.PASS)
        elif status == "FAIL":
            comment = HumanVoice.pick(HumanVoice.FAIL)
        elif status == "BLOCKED":
            comment = HumanVoice.pick(HumanVoice.BLOCKED)
    return CaseResult(
        case_id=case_id,
        name=name,
        module=module,
        status=status,
        steps=steps,
        comment=comment.lower(),
        performed_by=performed_by,
    )


def save_results(results: list[CaseResult], meta: dict[str, Any]) -> None:
    payload = {
        "meta": meta,
        "cases": [asdict(r) for r in results],
    }
    RESULTS_JSON.write_text(json.dumps(payload, indent=2, ensure_ascii=False), encoding="utf-8")
