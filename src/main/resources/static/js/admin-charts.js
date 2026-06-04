// Chart.js utility functions for admin analytics and reports

const chartInstances = {};

function renderTopPagesChart(elementId, data) {
    const ctx = document.getElementById(elementId);
    if (!ctx) return;

    if (chartInstances[elementId]) {
        chartInstances[elementId].destroy();
    }

    const labels = data.map(item => truncatePagePath(item.page, 30));
    const values = data.map(item => item.hits);

    chartInstances[elementId] = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [{
                label: 'Visits',
                data: values,
                backgroundColor: 'rgba(201, 168, 76, 0.8)',
                borderColor: 'rgba(201, 168, 76, 1)',
                borderWidth: 1,
                borderRadius: 4
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: true,
            indexAxis: 'y',
            plugins: {
                legend: {
                    display: false
                }
            },
            scales: {
                x: {
                    beginAtZero: true,
                    ticks: {
                        stepSize: 1
                    }
                }
            }
        }
    });
}

function renderDailyTrendChart(elementId, data) {
    const ctx = document.getElementById(elementId);
    if (!ctx) return;

    if (chartInstances[elementId]) {
        chartInstances[elementId].destroy();
    }

    const labels = data.map(item => item.date);
    const values = data.map(item => item.hits);

    chartInstances[elementId] = new Chart(ctx, {
        type: 'line',
        data: {
            labels: labels,
            datasets: [{
                label: 'Daily Visits',
                data: values,
                borderColor: 'rgba(201, 168, 76, 1)',
                backgroundColor: 'rgba(201, 168, 76, 0.1)',
                borderWidth: 2,
                fill: true,
                tension: 0.3,
                pointBackgroundColor: 'rgba(201, 168, 76, 1)',
                pointBorderColor: '#fff',
                pointBorderWidth: 2,
                pointRadius: 4
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: true,
            plugins: {
                legend: {
                    display: true,
                    position: 'top'
                }
            },
            scales: {
                y: {
                    beginAtZero: true,
                    ticks: {
                        stepSize: 1
                    }
                }
            }
        }
    });
}

function renderInquiryStatusChart(elementId, data) {
    const ctx = document.getElementById(elementId);
    if (!ctx) return;

    if (chartInstances[elementId]) {
        chartInstances[elementId].destroy();
    }

    const statusColors = {
        'NEW': 'rgba(59, 130, 246, 0.8)',
        'VIEWED': 'rgba(168, 85, 247, 0.8)',
        'CONTACTED': 'rgba(236, 72, 153, 0.8)',
        'PAYMENT_PENDING': 'rgba(251, 146, 60, 0.8)',
        'CONFIRMED': 'rgba(34, 197, 94, 0.8)',
        'COMPLETED': 'rgba(34, 197, 94, 0.6)',
        'CANCELLED_BY_ADMIN': 'rgba(239, 68, 68, 0.8)',
        'CANCELLED_BY_VISITOR': 'rgba(239, 68, 68, 0.6)'
    };

    const labels = data.map(item => item.status);
    const values = data.map(item => item.count);
    const colors = data.map(item => statusColors[item.status] || 'rgba(201, 168, 76, 0.8)');

    chartInstances[elementId] = new Chart(ctx, {
        type: 'doughnut',
        data: {
            labels: labels,
            datasets: [{
                data: values,
                backgroundColor: colors,
                borderColor: '#fff',
                borderWidth: 2
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: true,
            plugins: {
                legend: {
                    position: 'right',
                    labels: {
                        usePointStyle: true,
                        padding: 15
                    }
                }
            }
        }
    });
}

function renderPaymentStatusChart(elementId, data) {
    const ctx = document.getElementById(elementId);
    if (!ctx) return;

    if (chartInstances[elementId]) {
        chartInstances[elementId].destroy();
    }

    const statusColors = {
        'PENDING_VERIFICATION': 'rgba(251, 146, 60, 0.8)',
        'VERIFIED': 'rgba(34, 197, 94, 0.8)',
        'REJECTED': 'rgba(239, 68, 68, 0.8)'
    };

    const labels = data.map(item => item.status.replace(/_/g, ' '));
    const values = data.map(item => item.count);
    const colors = data.map(item => statusColors[item.status] || 'rgba(201, 168, 76, 0.8)');

    chartInstances[elementId] = new Chart(ctx, {
        type: 'doughnut',
        data: {
            labels: labels,
            datasets: [{
                data: values,
                backgroundColor: colors,
                borderColor: '#fff',
                borderWidth: 2
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: true,
            plugins: {
                legend: {
                    position: 'right',
                    labels: {
                        usePointStyle: true,
                        padding: 15
                    }
                }
            }
        }
    });
}

function renderFeedbackRatingsChart(elementId, data) {
    const ctx = document.getElementById(elementId);
    if (!ctx) return;

    if (chartInstances[elementId]) {
        chartInstances[elementId].destroy();
    }

    const labels = data.map(item => item.rating + ' ⭐');
    const values = data.map(item => item.count);

    chartInstances[elementId] = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [{
                label: 'Count',
                data: values,
                backgroundColor: 'rgba(201, 168, 76, 0.8)',
                borderColor: 'rgba(201, 168, 76, 1)',
                borderWidth: 1,
                borderRadius: 4
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: true,
            plugins: {
                legend: {
                    display: false
                }
            },
            scales: {
                y: {
                    beginAtZero: true,
                    ticks: {
                        stepSize: 1
                    }
                }
            }
        }
    });
}

function truncatePagePath(path, maxLength) {
    if (path.length <= maxLength) return path;
    return path.substring(0, maxLength) + '...';
}
