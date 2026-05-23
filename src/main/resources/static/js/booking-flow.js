/**
 * Keeps inquiry URLs in sync when the visitor picks a date on the home calendar.
 */
(function () {
    'use strict';

    function buildInquiryUrl(date, pricingId) {
        const params = new URLSearchParams();
        if (date) params.set('date', date);
        if (pricingId) params.set('pricingId', pricingId);
        const qs = params.toString();
        return qs ? `/inquiry?${qs}` : '/inquiry';
    }

    function syncInquiryLinks() {
        const date = window.getSelectedEventDate?.() || null;
        document.querySelectorAll('.js-inquiry-link').forEach((el) => {
            const pricingId = el.dataset.pricingId || null;
            el.href = buildInquiryUrl(date, pricingId);
        });
    }

    document.addEventListener('DOMContentLoaded', () => {
        syncInquiryLinks();

        document.addEventListener('calendar:dateSelected', () => syncInquiryLinks());
        document.addEventListener('calendar:dateCleared', () => syncInquiryLinks());
    });
})();
