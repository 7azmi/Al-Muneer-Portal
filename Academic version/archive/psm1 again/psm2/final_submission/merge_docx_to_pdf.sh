#!/usr/bin/env bash
# Converts every .docx in an input dir to PDF and merges them (in natural/
# numeric filename order) into a single PDF placed in an output dir.
# The merged file is named after the first .docx (by sort order).
#
# Usage:
#   ./merge_docx_to_pdf.sh [input_dir] [output_dir]
#
# Defaults: input_dir=./psm_docx  output_dir=./psm_pdf  (relative to this script)

set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
INPUT_DIR="${1:-$SCRIPT_DIR/psm_docx}"
OUTPUT_DIR="${2:-$SCRIPT_DIR/psm_pdf}"

command -v soffice >/dev/null || { echo "soffice (LibreOffice) not found in PATH" >&2; exit 1; }
command -v pdfunite >/dev/null || { echo "pdfunite (poppler-utils) not found in PATH" >&2; exit 1; }

mkdir -p "$OUTPUT_DIR"

mapfile -t DOCX_FILES < <(find "$INPUT_DIR" -maxdepth 1 -iname "*.docx" ! -iname ".~lock*" | sort -V)

if [ ${#DOCX_FILES[@]} -eq 0 ]; then
  echo "No .docx files found in $INPUT_DIR" >&2
  exit 1
fi

echo "Found ${#DOCX_FILES[@]} docx file(s) in $INPUT_DIR:"
for f in "${DOCX_FILES[@]}"; do
  echo "  - $(basename "$f")"
done

FIRST_NAME="$(basename "${DOCX_FILES[0]}" .docx)"
FINAL_PDF="$OUTPUT_DIR/$FIRST_NAME.pdf"

TMP_DIR="$(mktemp -d)"
trap 'rm -rf "$TMP_DIR"' EXIT

echo "Converting to individual PDFs..."
for f in "${DOCX_FILES[@]}"; do
  soffice --headless --convert-to pdf --outdir "$TMP_DIR" "$f" >/dev/null
done

PDF_FILES=()
for f in "${DOCX_FILES[@]}"; do
  base="$(basename "$f" .docx)"
  pdf="$TMP_DIR/$base.pdf"
  [ -f "$pdf" ] || { echo "Conversion failed for: $f" >&2; exit 1; }
  PDF_FILES+=("$pdf")
done

echo "Merging ${#PDF_FILES[@]} PDF(s) into: $FINAL_PDF"
pdfunite "${PDF_FILES[@]}" "$FINAL_PDF"

echo "Done -> $FINAL_PDF"
