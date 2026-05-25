// ═══════════════════════════════════════════
// Al-Muneer Portal — Main JavaScript
// ═══════════════════════════════════════════

document.addEventListener('DOMContentLoaded', () => {
    initNavToggle();
    initSmoothScroll();
    initScrollSpy();
    initActiveNav();
    initLightbox();
});

// ── Smooth scroll for in-page anchors ──
function initSmoothScroll() {
    document.querySelectorAll('a[href^="#"], a[href^="/#"]').forEach((a) => {
        a.addEventListener('click', (e) => {
            const raw = a.getAttribute('href');
            const hash = raw.includes('#') ? raw.substring(raw.indexOf('#')) : null;
            if (!hash || hash === '#') return;

            const target = document.querySelector(hash);
            if (!target) return;

            e.preventDefault();
            target.scrollIntoView({ behavior: 'smooth', block: 'start' });

            const links = document.getElementById('nav-links');
            if (links) links.classList.remove('open');

            if (raw.startsWith('/#') && window.location.pathname !== '/') {
                window.location.href = '/' + hash;
            } else {
                history.replaceState(null, '', hash);
            }
        });
    });
}

// ── Highlight nav tab for section in view (home page) ──
function initScrollSpy() {
    const sections = document.querySelectorAll('main section[id]');
    const navLinks = document.querySelectorAll('#nav-links a[data-section]');
    if (!sections.length || !navLinks.length) return;

    const observer = new IntersectionObserver(
        (entries) => {
            entries.forEach((entry) => {
                if (!entry.isIntersecting) return;
                const id = entry.target.id;
                navLinks.forEach((link) => {
                    link.classList.toggle('active', link.dataset.section === id);
                });
            });
        },
        { rootMargin: '-40% 0px -50% 0px', threshold: 0 }
    );

    sections.forEach((s) => observer.observe(s));
}

// ── Active Nav Highlighting (non-home routes) ──
function initActiveNav() {
    if (document.querySelector('main section[id]')) return;
    const path = window.location.pathname;
    document.querySelectorAll('#nav-links a').forEach((link) => {
        const href = link.getAttribute('href');
        if (!href) return;
        if (href === '/' && path === '/') {
            link.classList.add('active');
        } else if (href !== '/' && !href.startsWith('/#') && path.startsWith(href)) {
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

    const content = document.getElementById('lightbox-content');
    const captionContainer = document.getElementById('lightbox-caption');
    const thumbnailsContainer = document.getElementById('lightbox-thumbnails');
    const closeBtn = document.getElementById('lightbox-close');
    const prevBtn = document.getElementById('lightbox-prev');
    const nextBtn = document.getElementById('lightbox-next');
    
    const mediaItems = Array.from(document.querySelectorAll('.gallery-item'));
    let currentIndex = 0;
    let touchStartX = 0;
    let touchEndX = 0;

    // Initialize display items
    mediaItems.forEach((item, index) => {
        item.addEventListener('click', () => {
            openLightbox(index);
        });
    });

    function openLightbox(index) {
        currentIndex = index;
        updateLightbox();
        renderThumbnails();
        lightbox.classList.add('active');
        document.body.style.overflow = 'hidden'; // Prevent scrolling
    }

    function updateLightbox() {
        const item = mediaItems[currentIndex];
        const type = item.dataset.type;
        const caption = item.dataset.caption || '';
        
        content.innerHTML = '';
        captionContainer.textContent = caption;

        if (type === 'IMAGE') {
            const img = document.createElement('img');
            img.src = item.dataset.src;
            img.alt = caption;
            content.appendChild(img);
        } else if (type === 'YOUTUBE') {
            const videoId = extractYouTubeId(item.dataset.url);
            if (videoId) {
                content.innerHTML = `<iframe src="https://www.youtube.com/embed/${videoId}?autoplay=1" allowfullscreen></iframe>`;
            }
        }

        updateThumbnailsActive();
    }

    function renderThumbnails() {
        thumbnailsContainer.innerHTML = '';
        mediaItems.forEach((item, index) => {
            const thumb = document.createElement('div');
            thumb.className = 'thumb-item';
            if (index === currentIndex) thumb.classList.add('active');
            
            const type = item.dataset.type;
            if (type === 'IMAGE') {
                thumb.innerHTML = `<img src="${item.dataset.src}" alt="Thumb">`;
            } else {
                const videoId = extractYouTubeId(item.dataset.url);
                thumb.innerHTML = `<img src="https://img.youtube.com/vi/${videoId}/default.jpg" alt="Thumb">`;
            }

            thumb.addEventListener('click', () => {
                currentIndex = index;
                updateLightbox();
            });
            thumbnailsContainer.appendChild(thumb);
        });
        
        scrollToActiveThumbnail();
    }

    function updateThumbnailsActive() {
        const thumbs = thumbnailsContainer.querySelectorAll('.thumb-item');
        thumbs.forEach((thumb, index) => {
            thumb.classList.toggle('active', index === currentIndex);
        });
        scrollToActiveThumbnail();
    }

    function scrollToActiveThumbnail() {
        const activeThumb = thumbnailsContainer.querySelector('.thumb-item.active');
        if (activeThumb) {
            activeThumb.scrollIntoView({ behavior: 'smooth', block: 'nearest', inline: 'center' });
        }
    }

    function next() {
        currentIndex = (currentIndex + 1) % mediaItems.length;
        updateLightbox();
    }

    function prev() {
        currentIndex = (currentIndex - 1 + mediaItems.length) % mediaItems.length;
        updateLightbox();
    }

    // Controls
    if (prevBtn) prevBtn.addEventListener('click', (e) => { e.stopPropagation(); prev(); });
    if (nextBtn) nextBtn.addEventListener('click', (e) => { e.stopPropagation(); next(); });
    if (closeBtn) closeBtn.addEventListener('click', closeLightbox);

    lightbox.addEventListener('click', (e) => {
        if (e.target === lightbox || e.target.classList.contains('lightbox-main')) closeLightbox();
    });

    // Keyboard navigation
    document.addEventListener('keydown', (e) => {
        if (!lightbox.classList.contains('active')) return;
        if (e.key === 'ArrowRight') next();
        if (e.key === 'ArrowLeft') prev();
        if (e.key === 'Escape') closeLightbox();
    });

    // Swipe gestures for mobile
    lightbox.addEventListener('touchstart', e => {
        touchStartX = e.changedTouches[0].screenX;
    }, {passive: true});

    lightbox.addEventListener('touchend', e => {
        touchEndX = e.changedTouches[0].screenX;
        handleSwipe();
    }, {passive: true});

    function handleSwipe() {
        const threshold = 50;
        if (touchEndX < touchStartX - threshold) next();
        if (touchEndX > touchStartX + threshold) prev();
    }

    function closeLightbox() {
        lightbox.classList.remove('active');
        content.innerHTML = '';
        document.body.style.overflow = '';
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
                <button type="button" class="cal-nav-btn" id="cal-prev" aria-label="Previous month">
                    <span class="cal-nav-icon" aria-hidden="true">‹</span>
                </button>
                <h2 class="calendar-month-title">${monthNames[currentMonth - 1]} ${currentYear}</h2>
                <button type="button" class="cal-nav-btn" id="cal-next" aria-label="Next month">
                    <span class="cal-nav-icon" aria-hidden="true">›</span>
                </button>
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

        const prev = container.querySelector('#cal-prev');
        const next = container.querySelector('#cal-next');
        if (prev) prev.addEventListener('click', () => window.calNav(-1));
        if (next) next.addEventListener('click', () => window.calNav(1));
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

    if (isAdmin) {
        window.adminCalRefresh = () => loadSlots();
    }

    loadSlots();
}
