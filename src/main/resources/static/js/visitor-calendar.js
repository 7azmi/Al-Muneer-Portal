/**
 * Visitor availability calendar — interactive date picking linked to inquiry flow.
 */
(function (global) {
    'use strict';

    const MONTHS = [
        'January', 'February', 'March', 'April', 'May', 'June',
        'July', 'August', 'September', 'October', 'November', 'December'
    ];
    const DAYS = ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'];

    let selectedDate = null;
    let currentYear = 0;
    let currentMonth = 0;
    let container = null;

    function parseDateStr(str) {
        const [y, m, d] = str.split('-').map(Number);
        return new Date(y, m - 1, d);
    }

    function toDateStr(y, m, d) {
        return `${y}-${String(m).padStart(2, '0')}-${String(d).padStart(2, '0')}`;
    }

    function startOfToday() {
        const t = new Date();
        return new Date(t.getFullYear(), t.getMonth(), t.getDate());
    }

    function resolveStatus(dateStr, slots) {
        const day = parseDateStr(dateStr);
        const today = startOfToday();
        if (day < today) return 'PAST';
        if (slots[dateStr]) return slots[dateStr];
        return 'AVAILABLE';
    }

    function formatDisplayDate(dateStr) {
        const d = parseDateStr(dateStr);
        return d.toLocaleDateString(undefined, {
            weekday: 'long',
            year: 'numeric',
            month: 'long',
            day: 'numeric'
        });
    }

    function dispatchSelection(dateStr) {
        document.dispatchEvent(new CustomEvent('calendar:dateSelected', {
            detail: { date: dateStr, display: formatDisplayDate(dateStr) }
        }));
    }

    function dispatchClear() {
        document.dispatchEvent(new CustomEvent('calendar:dateCleared'));
    }

    function updateSelectionPanel(dateStr) {
        const panel = document.getElementById('calendar-selection-panel');
        if (!panel) return;

        const display = document.getElementById('calendar-selected-display');
        const idleMsg = document.getElementById('calendar-idle-message');
        const selectedBlock = document.getElementById('calendar-selected-block');

        if (!dateStr) {
            panel.classList.remove('is-visible');
            panel.setAttribute('aria-hidden', 'true');
            if (idleMsg) idleMsg.hidden = false;
            if (selectedBlock) selectedBlock.hidden = true;
            return;
        }

        panel.classList.add('is-visible');
        panel.setAttribute('aria-hidden', 'false');
        if (idleMsg) idleMsg.hidden = true;
        if (selectedBlock) selectedBlock.hidden = false;
        if (display) display.textContent = formatDisplayDate(dateStr);

        document.dispatchEvent(new CustomEvent('calendar:dateSelected', {
            detail: { date: dateStr, display: formatDisplayDate(dateStr) }
        }));
    }

    function selectDate(dateStr, status) {
        if (status !== 'AVAILABLE') return;

        selectedDate = dateStr;
        loadSlots();
        updateSelectionPanel(dateStr);

        const panel = document.getElementById('calendar-selection-panel');
        if (panel) {
            panel.scrollIntoView({ behavior: 'smooth', block: 'nearest' });
        }
    }

    function render(slots) {
        if (!container) return;

        const firstDay = new Date(currentYear, currentMonth - 1, 1).getDay();
        const daysInMonth = new Date(currentYear, currentMonth, 0).getDate();
        const today = startOfToday();

        const header = document.createElement('div');
        header.className = 'calendar-header';

        const prevBtn = document.createElement('button');
        prevBtn.type = 'button';
        prevBtn.className = 'cal-nav-btn';
        prevBtn.setAttribute('aria-label', 'Previous month');
        prevBtn.innerHTML = '<span class="cal-nav-icon" aria-hidden="true">‹</span>';
        prevBtn.addEventListener('click', () => navigate(-1));

        const title = document.createElement('h2');
        title.className = 'calendar-month-title';
        title.textContent = `${MONTHS[currentMonth - 1]} ${currentYear}`;

        const nextBtn = document.createElement('button');
        nextBtn.type = 'button';
        nextBtn.className = 'cal-nav-btn';
        nextBtn.setAttribute('aria-label', 'Next month');
        nextBtn.innerHTML = '<span class="cal-nav-icon" aria-hidden="true">›</span>';
        nextBtn.addEventListener('click', () => navigate(1));

        header.append(prevBtn, title, nextBtn);

        const grid = document.createElement('div');
        grid.className = 'calendar-grid visitor-calendar-grid';

        DAYS.forEach((d) => {
            const h = document.createElement('div');
            h.className = 'calendar-day-header';
            h.textContent = d;
            grid.appendChild(h);
        });

        for (let i = 0; i < firstDay; i++) {
            const empty = document.createElement('div');
            empty.className = 'calendar-day empty';
            grid.appendChild(empty);
        }

        for (let day = 1; day <= daysInMonth; day++) {
            const dateStr = toDateStr(currentYear, currentMonth, day);
            const status = resolveStatus(dateStr, slots);
            const dayDate = parseDateStr(dateStr);
            const isToday = dayDate.getTime() === today.getTime();
            const isSelected = selectedDate === dateStr;

            const cell = document.createElement('button');
            cell.type = 'button';
            cell.className = 'calendar-day';
            cell.dataset.date = dateStr;
            cell.dataset.status = status;

            if (isToday) cell.classList.add('today');
            if (isSelected) cell.classList.add('selected');
            if (status === 'PAST') cell.classList.add('is-past');
            if (status === 'AVAILABLE') cell.classList.add('is-available', 'is-clickable');
            if (status === 'PENDING') cell.classList.add('is-pending');
            if (status === 'BOOKED') cell.classList.add('is-booked');

            const num = document.createElement('span');
            num.className = 'calendar-day-num';
            num.textContent = String(day);
            cell.appendChild(num);

            if (status !== 'PAST') {
                const dot = document.createElement('span');
                dot.className = `status-dot ${status.toLowerCase()}`;
                cell.appendChild(dot);
            }

            if (status === 'AVAILABLE') {
                cell.setAttribute('aria-label', `${formatDisplayDate(dateStr)}, available`);
                cell.addEventListener('click', () => selectDate(dateStr, status));
            } else if (status === 'PAST') {
                cell.disabled = true;
                cell.setAttribute('aria-label', `${day}, past date`);
            } else {
                cell.disabled = true;
                const label = status === 'BOOKED' ? 'booked' : 'pending';
                cell.setAttribute('aria-label', `${day}, ${label}`);
            }

            grid.appendChild(cell);
        }

        const legend = document.createElement('div');
        legend.className = 'calendar-legend';
        legend.innerHTML = `
            <div class="legend-item"><span class="legend-dot available"></span> Available</div>
            <div class="legend-item"><span class="legend-dot pending"></span> Pending</div>
            <div class="legend-item"><span class="legend-dot booked"></span> Booked</div>
            <div class="legend-item"><span class="legend-dot past"></span> Past</div>
        `;

        container.innerHTML = '';
        container.append(header, grid, legend);
    }

    function navigate(dir) {
        currentMonth += dir;
        if (currentMonth > 12) {
            currentMonth = 1;
            currentYear++;
        }
        if (currentMonth < 1) {
            currentMonth = 12;
            currentYear--;
        }
        loadSlots();
    }

    function loadSlots() {
        fetch(`/api/calendar/slots?year=${currentYear}&month=${currentMonth}`)
            .then((r) => r.json())
            .then((slots) => render(slots))
            .catch(() => render({}));
    }

    function initVisitorCalendar(elementId, year, month, initialDate) {
        container = document.getElementById(elementId);
        if (!container) return;

        currentYear = year;
        currentMonth = month;
        selectedDate = initialDate || null;

        if (selectedDate) {
            const d = parseDateStr(selectedDate);
            currentYear = d.getFullYear();
            currentMonth = d.getMonth() + 1;
            updateSelectionPanel(selectedDate);
        } else {
            updateSelectionPanel(null);
        }

        loadSlots();
    }

    global.initVisitorCalendar = initVisitorCalendar;
    global.getSelectedEventDate = () => selectedDate;
    global.clearSelectedEventDate = () => {
        selectedDate = null;
        updateSelectionPanel(null);
        dispatchClear();
        loadSlots();
    };
})(window);
