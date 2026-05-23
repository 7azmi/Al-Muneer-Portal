/**
 * Packed masonry layout — places variable-height items in the shortest column.
 * Pure JS, no dependencies. Re-layouts on resize and after images load.
 */
(function (global) {
    'use strict';

    function MasonryLayout(container, options) {
        this.container = typeof container === 'string'
            ? document.querySelector(container)
            : container;
        if (!this.container) return;

        this.gap = (options && options.gap) ?? 14;
        this.minColumnWidth = (options && options.minColumnWidth) ?? 240;
        this.items = () => Array.from(this.container.querySelectorAll('.masonry-item:not(.masonry-hidden)'));
        this._resizeTimer = null;
        this._boundResize = this._onResize.bind(this);

        this.container.classList.add('masonry-packed-container');
        window.addEventListener('resize', this._boundResize);
        this._watchImages();
        this.layout();
    }

    MasonryLayout.prototype._onResize = function () {
        clearTimeout(this._resizeTimer);
        this._resizeTimer = setTimeout(() => this.layout(), 120);
    };

    MasonryLayout.prototype._watchImages = function () {
        this.container.querySelectorAll('.masonry-item img').forEach((img) => {
            if (img.complete) return;
            img.addEventListener('load', () => this.layout(), { once: true });
        });
    };

    MasonryLayout.prototype.layout = function () {
        const items = this.items();
        if (!items.length) {
            this.container.style.height = '0px';
            return;
        }

        const width = this.container.clientWidth;
        if (width <= 0) return;

        const cols = Math.max(1, Math.floor((width + this.gap) / (this.minColumnWidth + this.gap)));
        const colWidth = (width - this.gap * (cols - 1)) / cols;
        const colHeights = new Array(cols).fill(0);

        items.forEach((item) => {
            item.style.position = 'absolute';
            item.style.width = colWidth + 'px';
            item.style.left = '';
            item.style.top = '';
        });

        // Measure with width applied
        items.forEach((item) => {
            let minCol = 0;
            for (let c = 1; c < cols; c++) {
                if (colHeights[c] < colHeights[minCol]) minCol = c;
            }

            const left = minCol * (colWidth + this.gap);
            const top = colHeights[minCol];

            item.style.left = left + 'px';
            item.style.top = top + 'px';
            colHeights[minCol] += item.offsetHeight + this.gap;
        });

        const maxH = Math.max(...colHeights, 0);
        this.container.style.height = Math.max(0, maxH - this.gap) + 'px';
    };

    MasonryLayout.prototype.refresh = function () {
        this._watchImages();
        this.layout();
    };

    MasonryLayout.prototype.destroy = function () {
        window.removeEventListener('resize', this._boundResize);
        clearTimeout(this._resizeTimer);
    };

    global.MasonryLayout = MasonryLayout;

    global.initMasonryLayouts = function () {
        document.querySelectorAll('[data-masonry]').forEach((el) => {
            if (el._masonryInstance) {
                el._masonryInstance.refresh();
            } else {
                el._masonryInstance = new MasonryLayout(el, {
                    gap: parseInt(el.dataset.masonryGap || '14', 10),
                    minColumnWidth: parseInt(el.dataset.masonryMinCol || '240', 10)
                });
            }
        });
    };
})(window);
