<%-- 
    Document   : overviewManagement
    Created on : May 24, 2024, 4:02:46 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/css/admin/overviewManagement.css">

<div class="overview">
    <h1 class="overview-header">
        Monitor health of <br /> your business
    </h1>

    <div class="overview-body">
        <div class="overview-body-chart">
            <div class="overview-body-chart-general">
                <div class="overview-body-chart-general-component" id="general-user">
                    <div class="overview-body-chart-general-component-title">
                        <p>User</p>
                        <i class="fa-solid fa-user"></i>
                    </div>
                    <p class="overview-body-chart-general-component-value">100</p>
                    <p class="overview-body-chart-general-component-plus">+1 last month</p>
                </div>
                <div class="overview-body-chart-general-component" id="generel-product">
                    <div class="overview-body-chart-general-component-title">
                        <p>Product</p>
                        <i class="fa-solid fa-box-archive"></i>
                    </div>
                    <p class="overview-body-chart-general-component-value">200</p>
                    <p class="overview-body-chart-general-component-plus">+20 last month</p>
                </div>
                <div class="overview-body-chart-general-component" id="generel-revenue">
                    <div class="overview-body-chart-general-component-title">
                        <p>Revenue</p>
                        <i class="fa-solid fa-coins"></i>
                    </div>
                    <p class="overview-body-chart-general-component-value">$10000</p>
                    <p class="overview-body-chart-general-component-plus">+$100 last month</p>
                </div>
            </div>
            <div class="overview-body-chart-product">
                <div class="overview-body-chart-bar-title">
                    <p>Total product sold</p>
                    <select name="productYear" id="">
                        <option value="2024">2024</option>
                        <option value="2023">2023</option>
                    </select>
                </div>
                <canvas id="chart-product"></canvas>
            </div>
            <div class="overview-body-chart-revenue">
                <div class="overview-body-chart-bar-title">
                    <p>Total revenue</p>
                    <select name="revenueYear" id="">
                        <option value="2024">2024</option>
                        <option value="2023">2023</option>
                    </select>
                </div>
                <canvas id="chart-revenue"></canvas>
            </div>
        </div>
        <div class="overview-body-statistic">
            <div class="overview-body-statistic-mostSold">
                <div class="overview-body-statistic-mostSold-title">
                    MOST SOLD PRODUCT
                </div>
                <div class="overview-body-statistic-mostSold-product">
                    <div class="overview-body-statistic-mostSold-product-title">
                        <div class="overview-body-statistic-mostSold-product-title-text">Sold:</div>
                        <div class="overview-body-statistic-mostSold-product-title-value">50</div>
                    </div>
                    <div class="overview-body-statistic-mostSold-product-image">
                        <img src="../image/category_food.jpg" alt="category_food">
                    </div>
                    <div class="overview-body-statistic-mostSold-product-name">Product name</div>
                </div>
            </div>
            <div class="overview-body-statictis-product-title">Product sold on ${date}</div>
            <div class="overview-body-statictis-product">
                <canvas id="statistic-product"></canvas>
            </div>
            <div class="overview-body-statictis-revenue-title">Revenue on ${date}</div>
            <div class="overview-body-statictis-revenue">
                <canvas id="statistic-revenue"></canvas>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>

    //////////////////////
    /// chart-product ///
    ////////////////////
    new Chart(document.getElementById('chart-product'), {
        type: 'bar',
        data: {
            labels: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12],
            datasets: [{
                    label: 'Total product sold',
                    data: [10, 12, 15, 11, 16, 23, 19, 20, 24, 12, 32, 15],
                    backgroundColor: '#0AA'
                }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            },
            plugins: {
                legend: {
                    display: false
                }
            }
        }
    });

    //////////////////////
    /// chart-revenue ///
    ////////////////////

    new Chart(document.getElementById('chart-revenue'), {
        type: 'bar',
        data: {
            labels: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12],
            datasets: [{
                    label: 'Total revenue received',
                    data: [10, 120, 150, 101, 160, 230, 109, 200, 240, 102, 32, 150],
                    backgroundColor: '#0A0'
                }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true,
                    ticks: {
                        callback: function (value, index, values) {
                            return '$' + value;
                        }
                    }
                }

            },
            plugins: {
                legend: {
                    display: false
                }
            }
        }
    });

    //////////////////////////
    /// Statistic-product ///
    ////////////////////////
    new Chart(document.getElementById('statistic-product'), {
        type: 'doughnut',
        data: {
            labels: ['Product', 'Goal'],
            datasets: [{
                    data: [75, Math.max(0, 100 - 75)],
                    backgroundColor: [
                        "#00FFFF80",
                        "#AAAAAA80"
                    ],
                    borderColor: [
                        "#0000FF80",
                        "#AAAAAAB0"
                    ],
                    borderWidth: 1,
                    cutOff: '60%',
                    rotation: 180
                }]
        },
        options: {
            plugins: {
                tooltip: {
                    enabled: false
                },
                legend: {
                    display: false
                }
            }
        },
        plugins: [{
                id: 'text-center',
                afterDatasetsDraw(chart, args, plugins) {
                    const {ctx, data, chartArea: {top, bottom, left, right, width, height},
                        scales: {x, y}} = chart;
                    ctx.save();

                    const orderArray = [];
                    if (data.datasets[0].data[0] > data.datasets[0].data[1]) {
                        orderArray.push(0, 1);
                    } else {
                        orderArray.push(1, 0);
                    }
                    ctx.textAlign = 'center';
                    // upperValue
                    ctx.fillStyle = data.datasets[0].borderColor[orderArray[0]];
                    ctx.font = 'bold 60px Segoe UI';
                    ctx.fillText(data.datasets[0].data[orderArray[0]], chart.getDatasetMeta(0).data[0].x,
                            chart.getDatasetMeta(0).data[0].y - 15);

                    // measure text length
                    const textWidth = ctx.measureText(data.datasets[0].data[orderArray[0]]).width;

                    // lowerValue
                    ctx.fillStyle = '#555';
                    ctx.font = 'bold 40px Segoe UI';
                    ctx.fillText('100', chart.getDatasetMeta(0).data[0].x,
                            chart.getDatasetMeta(0).data[0].y + 50);

                    // line
                    ctx.beginPath();
                    ctx.strokeStyle = 'rgba(102,102,102,1)';
                    ctx.lineWidth = 3;
                    ctx.moveTo(chart.getDatasetMeta(0).data[0].x - textWidth / 2, chart.getDatasetMeta(0).data[0].y);
                    ctx.lineTo(chart.getDatasetMeta(0).data[0].x + textWidth / 2, chart.getDatasetMeta(0).data[0].y);
                    ctx.stroke();
                }
            }]
    });

    //////////////////////////
    /// Statistic-revenue ///
    ////////////////////////
    new Chart(document.getElementById('statistic-revenue'), {
        type: 'doughnut',
        data: {
            labels: ['Revenue', 'Goal'],
            datasets: [{
                    data: [750, Math.max(0, 1000 - 750)],
                    backgroundColor: [
                        "#00FF00B0",
                        "#AAAAAA80"
                    ],
                    borderColor: [
                        "#009900",
                        "#AAAAAAB0"
                    ],
                    borderWidth: 1,
                    cutOff: '60%',
                    rotation: 180
                }]
        },
        options: {
            plugins: {
                tooltip: {
                    enabled: false
                },
                legend: {
                    display: false
                }
            }
        },
        plugins: [{
                id: 'text-center',
                afterDatasetsDraw(chart, args, plugins) {
                    const {ctx, data, chartArea: {top, bottom, left, right, width, height},
                        scales: {x, y}} = chart;
                    ctx.save();

                    const orderArray = [];
                    if (data.datasets[0].data[0] > data.datasets[0].data[1]) {
                        orderArray.push(0, 1);
                    } else {
                        orderArray.push(1, 0);
                    }
                    ctx.textAlign = 'center';
                    // upperValue
                    ctx.fillStyle = data.datasets[0].borderColor[orderArray[0]];
                    ctx.font = 'bold 40px Segoe UI';
                    ctx.fillText('$' + data.datasets[0].data[orderArray[0]], chart.getDatasetMeta(0).data[0].x,
                            chart.getDatasetMeta(0).data[0].y - 15);

                    // measure text length
                    const textWidth = ctx.measureText(data.datasets[0].data[orderArray[0]]).width;

                    // lowerValue
                    ctx.fillStyle = '#555';
                    ctx.font = 'bold 25px Segoe UI';
                    ctx.fillText('$1000', chart.getDatasetMeta(0).data[0].x,
                            chart.getDatasetMeta(0).data[0].y + 30);

                    // line
                    ctx.beginPath();
                    ctx.strokeStyle = 'rgba(102,102,102,1)';
                    ctx.lineWidth = 3;
                    ctx.moveTo(chart.getDatasetMeta(0).data[0].x - textWidth / 2, chart.getDatasetMeta(0).data[0].y);
                    ctx.lineTo(chart.getDatasetMeta(0).data[0].x + textWidth / 2, chart.getDatasetMeta(0).data[0].y);
                    ctx.stroke();
                }
            }]
    });

</script>