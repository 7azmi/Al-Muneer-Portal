// ═══════════════════════════════════════════
// Al-Muneer Portal — Main JavaScript
// ═══════════════════════════════════════════

document.addEventListener('DOMContentLoaded', () => {
    initNavToggle();
    initActiveNav();
    initLightbox();
});

// ── Active Nav Highlighting ──
function initActiveNav() {
    const path = window.location.pathname;
    const links = document.querySelectorAll('#nav-links a');
    links.forEach(link => {
        const href = link.getAttribute('href');
        if (href === '/' && path === '/') {
            link.classList.add('active');
        } else if (href !== '/' && path.startsWith(href)) {
            link.classList.add('active');
        }
    });
}

// ── Mobile Nav Toggle ──
function initNavToggle() {
    const toggle = document.getElementById('nav-toggle');
    const links = document.getElementById('nav-links');
    if (toggle && links) {
        toggle.addEventListener('click', () => {
            links.classList.toggle('open');
        });
    }
}

// ── Gallery Lightbox ──
function initLightbox() {
    const lightbox = document.getElementById('lightbox');
    if (!lightbox) return;

    const content = lightbox.querySelector('.lightbox-content');
    const closeBtn = lightbox.querySelector('.lightbox-close');

    // Open lightbox for images
    document.querySelectorAll('.gallery-item[data-type="IMAGE"]').forEach(item => {
        item.addEventListener('click', () => {
            const src = item.dataset.src;
            const caption = item.dataset.caption || '';
            content.innerHTML = `<img src="${src}" alt="${caption}">`;
            lightbox.classList.add('active');
        });
    });

    // Open lightbox for YouTube
    document.querySelectorAll('.gallery-item[data-type="YOUTUBE"]').forEach(item => {
        item.addEventListener('click', () => {
            const videoId = extractYouTubeId(item.dataset.url);
            if (videoId) {
                content.innerHTML = `<iframe src="https://www.youtube.com/embed/${videoId}?autoplay=1" allowfullscreen></iframe>`;
                lightbox.classList.add('active');
            }
        });
    });

    // Close lightbox
    if (closeBtn) {
        closeBtn.addEventListener('click', closeLightbox);
    }
    lightbox.addEventListener('click', (e) => {
        if (e.target === lightbox) closeLightbox();
    });
    document.addEventListener('keydown', (e) => {
        if (e.key === 'Escape') closeLightbox();
    });

    function closeLightbox() {
        lightbox.classList.remove('active');
        content.innerHTML = '';
    }
}

function extractYouTubeId(url) {
    if (!url) return null;
    const match = url.match(/(?:youtu\.be\/|youtube\.com\/(?:embed\/|v\/|watch\?v=|watch\?.+&v=))([^&?#]+)/);
    return match ? match[1] : null;
}

// ── Calendar ──
function initCalendar(elementId, year, month, isAdmin = false) {
    const container = document.getElementById(elementId);
    if (!container) return;

    let currentYear = year;
    let currentMonth = month;
    let selectedDate = null;

    const monthNames = ['January', 'February', 'March', 'April', 'May', 'June',
        'July', 'August', 'September', 'October', 'November', 'December'];
    const dayNames = ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'];

    function render(slots) {
        const firstDay = new Date(currentYear, currentMonth - 1, 1).getDay();
        const daysInMonth = new Date(currentYear, currentMonth, 0).getDate();
        const today = new Date();

        let html = `
            <div class="calendar-header">
                <button id="cal-prev" onclick="calNav(-1)">‹</button>
                <h2>${monthNames[currentMonth - 1]} ${currentYear}</h2>
                <button id="cal-next" onclick="calNav(1)">›</button>
            </div>
            <div class="calendar-grid ${isAdmin ? 'admin-calendar' : ''}">
        `;

        dayNames.forEach(d => {
            html += `<div class="calendar-day-header">${d}</div>`;
        });

        for (let i = 0; i < firstDay; i++) {
            html += `<div class="calendar-day empty"></div>`;
        }

        for (let day = 1; day <= daysInMonth; day++) {
            const dateStr = `${currentYear}-${String(currentMonth).padStart(2, '0')}-${String(day).padStart(2, '0')}`;
            const status = slots[dateStr] || '';
            const isToday = today.getFullYear() === currentYear &&
                            today.getMonth() === currentMonth - 1 &&
                            today.getDate() === day;
            const isSelected = selectedDate === dateStr;

            let classes = 'calendar-day';
            if (isToday) classes += ' today';
            if (isSelected) classes += ' selected';

            let statusDot = '';
            if (status) {
                statusDot = `<span class="status-dot ${status.toLowerCase()}"></span>`;
            }

            const clickHandler = isAdmin ? `onclick="calSelectDate('${dateStr}')"` : '';

            html += `<div class="${classes}" ${clickHandler}>${day}${statusDot}</div>`;
        }

        html += '</div>';
        html += `
            <div class="calendar-legend">
                <div class="legend-item"><span class="legend-dot available"></span> Available</div>
                <div class="legend-item"><span class="legend-dot pending"></span> Pending</div>
                <div class="legend-item"><span class="legend-dot booked"></span> Booked</div>
            </div>
        `;

        container.innerHTML = html;
    }

    function loadSlots() {
        fetch(`/api/calendar/slots?year=${currentYear}&month=${currentMonth}`)
            .then(r => r.json())
            .then(slots => render(slots))
            .catch(() => render({}));
    }

    window.calNav = function(dir) {
        currentMonth += dir;
        if (currentMonth > 12) { currentMonth = 1; currentYear++; }
        if (currentMonth < 1) { currentMonth = 12; currentYear--; }
        loadSlots();
    };

    window.calSelectDate = function(dateStr) {
        selectedDate = dateStr;
        const panel = document.getElementById('slot-status-panel');
        if (panel) {
            panel.style.display = 'block';
            document.getElementById('selected-date-display').textContent = dateStr;
            document.getElementById('slot-date-input').value = dateStr;
        }
        loadSlots();
    };

    loadSlots();
}
