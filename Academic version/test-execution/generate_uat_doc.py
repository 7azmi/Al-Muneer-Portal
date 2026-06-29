"""Generate UAT markdown document from test results (UTM-style)."""

from __future__ import annotations

import json
from pathlib import Path

from uat_helpers import ROOT, RESULTS_JSON, CaseResult

UAT_PATH = Path(__file__).resolve().parent.parent / "appendix: UAT.md"


MODULE_SECTIONS = [
    ("PART 1", "VISITOR MODULE (PUBLIC WEBSITE)", [
        ("1.0", "View Venue Information & FAQ", "TC_V_001", ["TC_V_001_01", "TC_V_001_02"]),
        ("2.0", "View Media Gallery", "TC_V_002", ["TC_V_002_01", "TC_V_002_02"]),
        ("3.0", "Check Availability Calendar", "TC_V_003", ["TC_V_003_01", "TC_V_003_02"]),
        ("4.0", "View Pricing Panel", "TC_V_004", ["TC_V_004_01", "TC_V_004_02"]),
        ("5.0", "Submit Booking Inquiry", "TC_V_005", [
            "TC_V_005_01", "TC_V_005_02", "TC_V_005_03", "TC_V_005_04", "TC_V_005_05",
        ]),
        ("6.0", "Submit Payment Proof", "TC_V_011", ["TC_V_011_01", "TC_V_011_02"]),
        ("7.0", "Submit Feedback", "TC_V_012", ["TC_V_012_01", "TC_V_012_02"]),
    ]),
    ("PART 2", "ADMINISTRATOR MODULE (ADMIN DASHBOARD)", [
        ("1.0", "Login", "TC_A_000", ["TC_A_000_01", "TC_A_000_02"]),
        ("2.0", "Manage Hall Information & FAQ", "TC_A_006", ["TC_A_006_01", "TC_A_006_02"]),
        ("3.0", "Manage Media Gallery", "TC_A_007", ["TC_A_007_01", "TC_A_007_02", "TC_A_007_03"]),
        ("4.0", "Manage Pricing Panel", "TC_A_008", ["TC_A_008_01", "TC_A_008_02"]),
        ("5.0", "Manage Calendar & Inquiries", "TC_A_009", ["TC_A_009_01", "TC_A_009_02"]),
        ("6.0", "View Traffic Analytics", "TC_A_010", ["TC_A_010_01", "TC_A_010_02"]),
        ("7.0", "Manage Payment Status", "TC_A_013", ["TC_A_013_01", "TC_A_013_02"]),
        ("8.0", "View / Generate Reports", "TC_A_014", ["TC_A_014_01", "TC_A_014_02"]),
        ("9.0", "Manage Feedback", "TC_A_015", ["TC_A_015_01", "TC_A_015_02"]),
        ("10.0", "Manage WhatsApp Notification Templates", "TC_A_016", ["TC_A_016_01", "TC_A_016_02"]),
    ]),
]


def load_results() -> tuple[dict, dict[str, CaseResult]]:
    data = json.loads(RESULTS_JSON.read_text(encoding="utf-8"))
    cases = {}
    for c in data["cases"]:
        cases[c["case_id"]] = CaseResult(
            case_id=c["case_id"],
            name=c["name"],
            module=c.get("module", ""),
            status=c["status"],
            steps=[],  # filled below
            comment=c.get("comment", ""),
            performed_by=c.get("performed_by", "tester"),
        )
        cases[c["case_id"]].steps = c.get("steps", [])
    return data["meta"], cases


def status_label(s: str) -> str:
    return {"PASS": "Pass", "FAIL": "Fail", "BLOCKED": "Blocked", "SKIP": "Skip"}.get(s, s)


def write_uat_document(results: list[CaseResult] | None = None, meta: dict | None = None) -> Path:
    if results is not None:
        case_map = {r.case_id: r for r in results}
        if meta is None:
            meta = {
                "executed_at": "n/a",
                "tester": "ahmed hani (local uat — ibb)",
                "environment": "local http://localhost:8080",
                "browser": "google chrome",
                "std_version": "1.2",
            }
    else:
        meta, case_map_raw = load_results()
        case_map = case_map_raw

    lines: list[str] = [
        "# UNIVERSITI TEKNOLOGI MALAYSIA",
        "",
        "## FACULTY OF COMPUTING - UTM Johor Bahru",
        "",
        "**SECJ 3032:** Software Engineering PSM1",
        "",
        "**Semester:** 01, 2024/2025",
        "",
        "# User Acceptance Test (UAT) Document",
        "",
        "**Project:** Al-Muneer Online Portal",
        "",
        "**Version:** 1.0",
        "",
        "**Prepared by:** Ahmed Ghaleb",
        "",
        "**Appendix:** APPENDIX E — UAT",
        "",
        "---",
        "",
        "## Document Overview",
        "",
        "This document records **user acceptance testing** performed against the Al-Muneer Online Portal, "
        "following the test cases defined in Software Test Documentation (STD) Version 1.2. "
        "Testing was executed locally in a web browser with PostgreSQL seeded data.",
        "",
        "| Field | Detail |",
        "|-------|--------|",
        f"| **Test date** | {meta.get('executed_at', 'n/a')} |",
        f"| **Tester** | {meta.get('tester', 'ahmed hani')} |",
        f"| **Environment** | {meta.get('environment', 'local')} |",
        f"| **Browser** | {meta.get('browser', 'chrome')} |",
        f"| **STD reference** | Version {meta.get('std_version', '1.2')} |",
        "",
        "### Performed by",
        "",
        "1. **Visitor** — public pages (`/`, `/inquiry`, `/feedback`, `/payment/upload`)",
        "2. **Administrator** — username: `admin`, password: `admin123`",
        "",
        "### Summary",
        "",
    ]

    total = len(case_map)
    passed = sum(1 for c in case_map.values() if c.status == "PASS")
    failed = sum(1 for c in case_map.values() if c.status == "FAIL")
    blocked = sum(1 for c in case_map.values() if c.status == "BLOCKED")

    lines.extend([
        f"| Total test cases | {total} |",
        f"| Pass | {passed} |",
        f"| Fail | {failed} |",
        f"| Blocked | {blocked} |",
        "",
        "> Screenshots are stored under `test-execution/uat-screenshots/` and referenced as figures below.",
        "",
        "---",
        "",
    ])

    fig_refs: list[str] = []

    for part_title, part_desc, sections in MODULE_SECTIONS:
        lines.extend([
            f"## {part_title}",
            "",
            f"### {part_desc}",
            "",
            "**Description:**",
            "",
        ])
        if "VISITOR" in part_desc:
            lines.append(
                "This part covers visitor-facing functionality on the public website: venue information, "
                "gallery, availability calendar, pricing, booking inquiries, payment proof upload, and feedback."
            )
        else:
            lines.append(
                "This part covers administrator functionality in the admin dashboard: secure login, "
                "content management, calendar and inquiry handling, payment verification, analytics, "
                "reports, feedback review, and WhatsApp notification templates."
            )
        lines.extend([
            "",
            "**Performed by:**",
            "",
            "1. Visitor (public browser)" if "VISITOR" in part_desc else "1. Administrator (`admin`)",
            "",
            "---",
            "",
        ])

        for sec_num, sec_title, std_ref, case_ids in sections:
            lines.extend([
                f"### {sec_num} {sec_title}",
                "",
                f"**STD reference:** {std_ref}",
                "",
            ])

            # collect figures for this section
            section_figs: list[tuple[str, str]] = []
            for cid in case_ids:
                c = case_map.get(cid)
                if not c:
                    continue
                for s in c.steps if isinstance(c.steps, list) else []:
                    step = s if isinstance(s, dict) else s.__dict__
                    if step.get("figure") and step.get("screenshot"):
                        section_figs.append((step["figure"], step["screenshot"]))

            if section_figs:
                fig_line = " ".join(
                    f"**{fig}** — ![{fig}](../test-execution/{path})"
                    for fig, path in section_figs[:4]
                )
                lines.extend([fig_line, ""])
                if len(section_figs) > 4:
                    lines.append(f"*(additional figures: {len(section_figs) - 4} more screenshots in folder)*")
                    lines.append("")

            lines.extend([
                "| No | Actions | Expected Results | Pass / Fail | Comment |",
                "|----|---------|------------------|-------------|---------|",
            ])

            for i, cid in enumerate(case_ids, 1):
                c = case_map.get(cid)
                if not c:
                    lines.append(f"| {sec_num.split('.')[0]}.{i} | {cid} | — | — | not run |")
                    continue

                actions = []
                expected = []
                for s in c.steps if isinstance(c.steps, list) else []:
                    step = s if isinstance(s, dict) else s.__dict__
                    if step.get("step", 0) > 0:
                        actions.append(f"{step['step']}. {step['action']}")
                        expected.append(step.get("expected", ""))

                action_txt = "<br>".join(actions) if actions else c.name
                exp_txt = "<br>".join(expected[:3]) if expected else "per std"
                if len(expected) > 3:
                    exp_txt += "<br>…"

                comment = c.comment if isinstance(c, CaseResult) else c.get("comment", "")
                lines.append(
                    f"| {sec_num.split('.')[0]}.{i} | {action_txt} | {exp_txt} | "
                    f"**{status_label(c.status if isinstance(c, CaseResult) else c['status'])}** | {comment} |"
                )

            lines.extend(["", "---", ""])

    lines.extend([
        "## Appendix — Full Case Index",
        "",
        "| Case ID | Result | Comment |",
        "|---------|--------|---------|",
    ])
    for cid in sorted(case_map.keys()):
        c = case_map[cid]
        st = c.status if isinstance(c, CaseResult) else c["status"]
        cm = c.comment if isinstance(c, CaseResult) else c.get("comment", "")
        lines.append(f"| {cid} | {status_label(st)} | {cm} |")

    lines.extend([
        "",
        "---",
        "",
        "*End of UAT Document*",
    ])

    UAT_PATH.write_text("\n".join(lines), encoding="utf-8")
    return UAT_PATH


if __name__ == "__main__":
    write_uat_document()
    print(f"Wrote {UAT_PATH}")
