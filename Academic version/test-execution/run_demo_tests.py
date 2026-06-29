#!/usr/bin/env python3
"""Demo test execution against local Al-Muneer Portal (STD appendix)."""

from __future__ import annotations

import json
import re
from dataclasses import dataclass, field
from datetime import datetime, timezone
from pathlib import Path

from playwright.sync_api import sync_playwright, Page, expect

BASE_URL = "http://localhost:8080"
ROOT = Path(__file__).resolve().parent
SCREENSHOTS = ROOT / "screenshots"
REPORT_PATH = ROOT / "demo_test_report.md"


@dataclass
class StepResult:
    step: int
    action: str
    expected: str
    actual: str
    passed: bool
    screenshot: str | None = None


@dataclass
class CaseResult:
    case_id: str
    name: str
    status: str
    steps: list[StepResult] = field(default_factory=list)
    notes: str = ""


def shot(page: Page, filename: str) -> str:
    SCREENSHOTS.mkdir(parents=True, exist_ok=True)
    path = SCREENSHOTS / filename
    page.screenshot(path=str(path), full_page=False)
    return f"screenshots/{filename}"


def add_step(
    results: list[StepResult],
    step: int,
    action: str,
    expected: str,
    actual: str,
    passed: bool,
    page: Page | None = None,
    screenshot_name: str | None = None,
) -> None:
    screenshot = shot(page, screenshot_name) if page and screenshot_name else None
    results.append(
        StepResult(step, action, expected, actual, passed, screenshot)
    )


def run_tc_a_000_01(page: Page) -> CaseResult:
    case_id = "TC_A_000_01"
    name = "Verify successful admin login with valid credentials"
    steps: list[StepResult] = []

    page.goto(f"{BASE_URL}/admin/login")
    login_visible = page.locator("#admin-login-form").is_visible()
    add_step(
        steps, 1, "Navigate to /admin/login", "Login form displayed",
        "Login form visible" if login_visible else "Login form not found",
        login_visible, page, f"{case_id}_step1_login_form.png",
    )

    page.fill("#username", "admin")
    page.fill("#password", "admin123")
    page.click("#login-submit")
    page.wait_for_url("**/admin/dashboard**", timeout=10000)
    on_dashboard = "/admin/dashboard" in page.url
    dashboard_title = page.locator("h1.admin-page-title").first.text_content() or ""
    dash_ok = on_dashboard and "Dashboard" in dashboard_title
    add_step(
        steps, 2, "Enter valid credentials; submit", "Redirect to /admin/dashboard; dashboard loads",
        f"URL={page.url}, title={dashboard_title.strip()}",
        dash_ok, page, f"{case_id}_step2_dashboard.png",
    )

    page.goto(f"{BASE_URL}/admin/inquiries")
    page.wait_for_load_state("networkidle")
    inquiries_ok = "/admin/inquiries" in page.url and page.locator("h1.admin-page-title").text_content()
    inquiries_title = (page.locator("h1.admin-page-title").text_content() or "").strip()
    add_step(
        steps, 3, "Access /admin/inquiries", "Inquiry management page loads (not redirected to login)",
        f"URL={page.url}, title={inquiries_title}",
        inquiries_ok and "Booking Inquiries" in inquiries_title,
        page, f"{case_id}_step3_inquiries.png",
    )

    status = "PASS" if all(s.passed for s in steps) else "FAIL"
    return CaseResult(case_id, name, status, steps)


def run_tc_a_000_02(page: Page) -> CaseResult:
    case_id = "TC_A_000_02"
    name = "Verify failed login with invalid credentials"
    steps: list[StepResult] = []

    # Fresh context — no admin session
    page.context.clear_cookies()
    page.goto(f"{BASE_URL}/admin/login")
    login_visible = page.locator("#admin-login-form").is_visible()
    add_step(
        steps, 1, "Navigate to /admin/login", "Login form displayed",
        "Login form visible" if login_visible else "Login form not found",
        login_visible, page, f"{case_id}_step1_login_form.png",
    )

    page.fill("#username", "admin")
    page.fill("#password", "wrongpassword")
    page.click("#login-submit")
    page.wait_for_load_state("networkidle")
    error_text = (page.locator(".alert-error").text_content() or "").strip()
    still_login = "/admin/login" in page.url
    err_ok = still_login and "Invalid username or password" in error_text
    add_step(
        steps, 2, "Enter valid username with wrong password; submit",
        "Error message shown; remains on login page",
        f"URL={page.url}, error={error_text!r}",
        err_ok, page, f"{case_id}_step2_error.png",
    )

    page.context.clear_cookies()
    page.goto(f"{BASE_URL}/admin/dashboard")
    page.wait_for_load_state("networkidle")
    redirected = "/admin/login" in page.url
    add_step(
        steps, 3, "Navigate to /admin/dashboard without logging in",
        "Redirected to /admin/login",
        f"URL={page.url}",
        redirected, page, f"{case_id}_step3_redirect.png",
    )

    status = "PASS" if all(s.passed for s in steps) else "FAIL"
    return CaseResult(case_id, name, status, steps)


def run_tc_v_001_01(page: Page) -> CaseResult:
    case_id = "TC_V_001_01"
    name = "Verify venue section and FAQ accordion on home page"
    steps: list[StepResult] = []

    page.goto(f"{BASE_URL}/")
    home_ok = page.locator("#hero").is_visible()
    add_step(
        steps, 1, "Open /", "Home page loads",
        "Hero section visible" if home_ok else "Home page did not load",
        home_ok, page, f"{case_id}_step1_home.png",
    )

    page.locator('a[href="/#venue"]').first.click()
    page.wait_for_timeout(500)
    venue = page.locator("#venue")
    venue.scroll_into_view_if_needed()
    venue_visible = venue.is_visible()
    map_link = page.locator('#venue a:has-text("Open in Google Maps")').count() > 0
    venue_ok = venue_visible and map_link
    add_step(
        steps, 2, "Scroll to #venue (click Venue in nav)",
        "Description, capacity, contact, location, services, map embed, Open in Google Maps link",
        f"venue visible={venue_visible}, maps link={map_link}",
        venue_ok, page, f"{case_id}_step2_venue.png",
    )

    page.locator('a[href="/#faq"]').first.click()
    page.wait_for_timeout(500)
    faq_section = page.locator("#faq")
    faq_section.scroll_into_view_if_needed()
    faq_visible = faq_section.is_visible()
    faq_count = page.locator("#faq .faq-item").count()
    add_step(
        steps, 3, "Scroll to #faq (click FAQ in nav)",
        "FAQ section visible with question summaries",
        f"FAQ visible={faq_visible}, items={faq_count}",
        faq_visible and faq_count >= 1, page, f"{case_id}_step3_faq.png",
    )

    # Expand second FAQ if present
    items = page.locator("#faq .faq-item")
    if items.count() > 1:
        items.nth(1).locator("summary").click()
    else:
        items.first.locator("summary").click()
    page.wait_for_timeout(300)
    open_count = page.locator("#faq details[open]").count()
    add_step(
        steps, 4, "Click a FAQ question",
        "Answer expands in accordion",
        f"Open FAQ items={open_count}",
        open_count >= 1, page, f"{case_id}_step4_faq_expand.png",
    )

    page.goto(f"{BASE_URL}/faq")
    page.wait_for_load_state("networkidle")
    redirect_ok = page.url.rstrip("/").endswith("/#faq") or "#faq" in page.url
    add_step(
        steps, 5, "Open /faq in address bar", "Redirects to /#faq",
        f"URL={page.url}",
        redirect_ok, page, f"{case_id}_step5_faq_redirect.png",
    )

    status = "PASS" if all(s.passed for s in steps) else "FAIL"
    return CaseResult(case_id, name, status, steps)


def run_tc_v_005_02(page: Page) -> CaseResult:
    case_id = "TC_V_005_02"
    name = "Verify validation for missing required fields"
    steps: list[StepResult] = []

    page.goto(f"{BASE_URL}/inquiry")
    form_visible = page.locator("#inquiry-submit-form").is_visible()
    add_step(
        steps, 0, "Open /inquiry", "Inquiry form displayed",
        "Form visible" if form_visible else "Form not found",
        form_visible, page, f"{case_id}_step0_form.png",
    )

    # Clear name, fill other required fields so only name is missing
    page.fill("#inq-name", "")
    page.fill("#inq-wa", "772629404")
    tomorrow = page.evaluate(
        """() => {
            const d = new Date();
            d.setDate(d.getDate() + 30);
            return d.toISOString().slice(0, 10);
        }"""
    )
    page.fill("#inq-date", tomorrow)

    blocked_name = page.evaluate(
        """() => {
            const form = document.getElementById('inquiry-submit-form');
            return !form.reportValidity();
        }"""
    )
    add_step(
        steps, 1, "Leave Full Name empty; submit",
        "Browser HTML5 validation prevents submit",
        "Submit blocked by validation" if blocked_name else "Validation did not block submit",
        blocked_name, page, f"{case_id}_step1_name_validation.png",
    )

    page.fill("#inq-name", "Test Visitor")
    page.fill("#inq-wa", "")
    blocked_wa = page.evaluate(
        """() => {
            const form = document.getElementById('inquiry-submit-form');
            return !form.reportValidity();
        }"""
    )
    add_step(
        steps, 2, "Fill name; leave WhatsApp empty; submit",
        "Submit blocked; required-field indication",
        "Submit blocked by validation" if blocked_wa else "Validation did not block submit",
        blocked_wa, page, f"{case_id}_step2_whatsapp_validation.png",
    )

    status = "PASS" if all(s.passed for s in steps) else "FAIL"
    return CaseResult(case_id, name, status, steps)


def write_report(results: list[CaseResult]) -> None:
    now = datetime.now(timezone.utc).strftime("%Y-%m-%d %H:%M UTC")
    passed = sum(1 for r in results if r.status == "PASS")
    total = len(results)

    lines = [
        "# Al-Muneer Portal — Demo Test Execution Report",
        "",
        f"**Source:** `appendix: STD.md`  ",
        f"**Environment:** Local (`{BASE_URL}`)  ",
        f"**Executed:** {now}  ",
        f"**Tool:** Playwright (Chromium) — automated browser execution",
        "",
        "## Summary",
        "",
        f"| Metric | Value |",
        f"|--------|-------|",
        f"| Cases executed | {total} |",
        f"| Passed | {passed} |",
        f"| Failed | {total - passed} |",
        "",
        "> This is a **demonstration** run covering a small subset of STD test cases.",
        "",
    ]

    for result in results:
        icon = "✅" if result.status == "PASS" else "❌"
        lines.extend([
            f"## {icon} {result.case_id}: {result.name}",
            "",
            f"**Status:** {result.status}",
            "",
        ])
        if result.notes:
            lines.extend([f"**Notes:** {result.notes}", ""])

        lines.append("| Step | Action | Expected | Actual | Result | Screenshot |")
        lines.append("|------|--------|----------|--------|--------|------------|")
        for s in result.steps:
            mark = "PASS" if s.passed else "FAIL"
            img = f"![step {s.step}]({s.screenshot})" if s.screenshot else "—"
            lines.append(
                f"| {s.step} | {s.action} | {s.expected} | {s.actual} | {mark} | {img} |"
            )
        lines.append("")

    REPORT_PATH.write_text("\n".join(lines), encoding="utf-8")

    summary = {
        "executed_at": now,
        "base_url": BASE_URL,
        "passed": passed,
        "total": total,
        "cases": [
            {
                "id": r.case_id,
                "name": r.name,
                "status": r.status,
                "steps": [
                    {
                        "step": s.step,
                        "passed": s.passed,
                        "screenshot": s.screenshot,
                    }
                    for s in r.steps
                ],
            }
            for r in results
        ],
    }
    (ROOT / "demo_test_results.json").write_text(
        json.dumps(summary, indent=2), encoding="utf-8"
    )


def main() -> None:
    SCREENSHOTS.mkdir(parents=True, exist_ok=True)
    results: list[CaseResult] = []

    with sync_playwright() as p:
        browser = p.chromium.launch(headless=True)
        context = browser.new_context(viewport={"width": 1440, "height": 900})
        page = context.new_page()

        runners = [
            run_tc_a_000_01,
            run_tc_a_000_02,
            run_tc_v_001_01,
            run_tc_v_005_02,
        ]
        for runner in runners:
            # Use fresh context for auth-sensitive cases
            if runner in (run_tc_a_000_02, run_tc_v_001_01, run_tc_v_005_02):
                context.clear_cookies()
            results.append(runner(page))

        browser.close()

    write_report(results)
    print(f"Report written to {REPORT_PATH}")
    for r in results:
        print(f"  {r.case_id}: {r.status}")


if __name__ == "__main__":
    main()
