/**
 * Visitor gallery: category filters + lightbox for .masonry-item elements.
 */
(function () {
    'use strict';

    document.addEventListener('DOMContentLoaded', () => {
        initGalleryFilters();
        initMasonryLayouts();
        initMasonryLightbox();
        applyCategoryFromUrl();
    });

    function initGalleryFilters() {
        document.querySelectorAll('[data-gallery-filters]').forEach((bar) => {
            const gridId = bar.dataset.galleryGrid;
            const grid = gridId ? document.getElementById(gridId) : bar.parentElement.querySelector('[data-masonry]');
            if (!grid) return;

            bar.querySelectorAll('[data-filter]').forEach((btn) => {
                btn.addEventListener('click', (e) => {
                    e.preventDefault();
                    const category = btn.dataset.filter;
                    bar.querySelectorAll('[data-filter]').forEach((b) => b.classList.remove('active'));
                    btn.classList.add('active');

                    grid.querySelectorAll('.masonry-item').forEach((item) => {
                        const cat = (item.dataset.category || '').trim();
                        const show = category === 'all' || cat === category;
                        item.classList.toggle('masonry-hidden', !show);
                        item.style.display = show ? '' : 'none';
                    });

                    if (grid._masonryInstance) {
                        grid._masonryInstance.refresh();
                    }
                });
            });
        });
    }

    function applyCategoryFromUrl() {
        const params = new URLSearchParams(window.location.search);
        const category = params.get('category');
        if (!category || category === 'all') return;

        const buttons = document.querySelectorAll('[data-gallery-filters] [data-filter]');
        const match = Array.from(buttons).find(
            (b) => b.dataset.filter.toLowerCase() === category.toLowerCase()
        );
        if (match) match.click();
    }

    function initMasonryLightbox() {
        const lightbox = document.getElementById('lightbox');
        if (!lightbox) return;

        const content = document.getElementById('lightbox-content');
        const captionEl = document.getElementById('lightbox-caption');
        const thumbs = document.getElementById('lightbox-thumbnails');
        const closeBtn = document.getElementById('lightbox-close');
        const prevBtn = document.getElementById('lightbox-prev');
        const nextBtn = document.getElementById('lightbox-next');

        let visibleItems = [];
        let current = 0;

        function getVisibleItems() {
            return Array.from(document.querySelectorAll('.masonry-item:not(.masonry-hidden)'))
                .filter((el) => el.style.display !== 'none' && el.offsetParent !== null);
        }

        function extractYouTubeId(url) {
            if (!url) return null;
            const m = url.match(/(?:youtu\.be\/|[?&]v=)([^&?#]+)/);
            return m ? m[1] : null;
        }

        function show(idx) {
            visibleItems = getVisibleItems();
            if (!visibleItems.length) return;
            current = ((idx % visibleItems.length) + visibleItems.length) % visibleItems.length;
            const item = visibleItems[current];
            const type = item.dataset.type;
            const cap = item.dataset.caption || '';

            content.innerHTML = '';
            if (type === 'IMAGE') {
                const img = document.createElement('img');
                img.src = item.dataset.src;
                img.alt = cap;
                content.appendChild(img);
            } else if (type === 'YOUTUBE') {
                const id = extractYouTubeId(item.dataset.url);
                const frame = document.createElement('iframe');
                frame.src = `https://www.youtube.com/embed/${id}?autoplay=1`;
                frame.allow = 'autoplay; fullscreen';
                frame.allowFullscreen = true;
                content.appendChild(frame);
            }

            captionEl.textContent = cap;
            lightbox.classList.add('active');
            document.body.style.overflow = 'hidden';

            thumbs.querySelectorAll('.thumb-item').forEach((t, i) => {
                t.classList.toggle('active', i === current);
            });
            const active = thumbs.querySelectorAll('.thumb-item')[current];
            if (active) active.scrollIntoView({ behavior: 'smooth', inline: 'center', block: 'nearest' });
        }

        function buildThumbs() {
            visibleItems = getVisibleItems();
            thumbs.innerHTML = '';
            visibleItems.forEach((item, i) => {
                const el = document.createElement('div');
                el.className = 'thumb-item' + (i === current ? ' active' : '');
                const img = item.querySelector('img');
                const t = document.createElement('img');
                t.src = img ? img.src : '';
                t.alt = '';
                el.appendChild(t);
                el.addEventListener('click', () => show(i));
                thumbs.appendChild(el);
            });
        }

        function close() {
            lightbox.classList.remove('active');
            content.innerHTML = '';
            document.body.style.overflow = '';
        }

        document.querySelectorAll('.masonry-item').forEach((item) => {
            item.addEventListener('click', () => {
                visibleItems = getVisibleItems();
                current = visibleItems.indexOf(item);
                if (current < 0) current = 0;
                buildThumbs();
                show(current);
            });
        });

        if (closeBtn) closeBtn.addEventListener('click', close);
        if (prevBtn) prevBtn.addEventListener('click', (e) => { e.stopPropagation(); show(current - 1); });
        if (nextBtn) nextBtn.addEventListener('click', (e) => { e.stopPropagation(); show(current + 1); });

        lightbox.addEventListener('click', (e) => {
            if (e.target === lightbox || e.target.classList.contains('lightbox-main')) close();
        });

        document.addEventListener('keydown', (e) => {
            if (!lightbox.classList.contains('active')) return;
            if (e.key === 'ArrowLeft') show(current - 1);
            if (e.key === 'ArrowRight') show(current + 1);
            if (e.key === 'Escape') close();
        });
    }
})();
