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
                        <p>User Enrolled</p>
                        <i class="fa-solid fa-user"></i>
                    </div>
                    <p class="overview-body-chart-general-component-value">${countUser}</p>
                    <p class="overview-body-chart-general-component-plus">+${countUserLastMonth} last month</p>
                </div>
                <div class="overview-body-chart-general-component" id="generel-product">
                    <div class="overview-body-chart-general-component-title">
                        <p>Product Sold</p>
                        <i class="fa-solid fa-box-archive"></i>
                    </div>
                    <p class="overview-body-chart-general-component-value">${productSold}</p>
                    <p class="overview-body-chart-general-component-plus">+${productSoldLastMonth} last month</p>
                </div>
                <div class="overview-body-chart-general-component" id="generel-revenue">
                    <div class="overview-body-chart-general-component-title">
                        <p>Revenue</p>
                        <i class="fa-solid fa-coins"></i>
                    </div>
                    <p class="overview-body-chart-general-component-value">$${revenue}</p>
                    <p class="overview-body-chart-general-component-plus">+$${revenueLastMonth} last month</p>
                </div>
            </div>
            <div class="overview-body-chart-product">
                <div class="overview-body-chart-bar-title">
                    <p>Monthly product sold</p>
                    <select name="productYear" id="productYear">
                        <option ${productYear == thisYear ? 'selected' : ''} value="${thisYear}">${thisYear}</option>
                        <option ${productYear == lastYear ? 'selected' : ''} value="${lastYear}">${lastYear}</option>
                    </select>
                </div>
                <canvas id="chart-product"></canvas>
            </div>
            <div class="overview-body-chart-revenue">
                <div class="overview-body-chart-bar-title">
                    <p>Monthly revenue</p>
                    <select name="revenueYear" id="revenueYear">
                        <option ${revenueYear == thisYear ? 'selected' : ''} value="${thisYear}">${thisYear}</option>
                        <option ${revenueYear == lastYear ? 'selected' : ''} value="${lastYear}">${lastYear}</option>
                    </select>
                </div>
                <canvas id="chart-revenue"></canvas>
            </div>
            <div type="button" class="overview-body-chart-export" onclick="exportData()"><i class="fa-solid fa-file-export"> Export </i></div>
        </div>
        <div class="overview-body-statistic">
            <div class="overview-body-statistic-mostSold">
                <div class="overview-body-statistic-mostSold-title">
                    MOST SOLD PRODUCT ON ${date}
                </div>
                <div class="overview-body-statistic-mostSold-product">
                    <div class="overview-body-statistic-mostSold-product-title">
                        <div class="overview-body-statistic-mostSold-product-title-text">Sold:</div>
                        <div class="overview-body-statistic-mostSold-product-title-value">${quantitySold}</div>
                    </div>
                    <div class="overview-body-statistic-mostSold-product-image">
                        <img src="data:image/jpeg;base64, ${mostSoldProduct.imgBase64}">
                    </div>
                    <div class="overview-body-statistic-mostSold-product-name">Id: ${mostSoldProduct.id}</div>
                    <div class="overview-body-statistic-mostSold-product-name">${mostSoldProduct.name}</div>
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
<script>
    document.getElementById('productYear').addEventListener('change', function () {
        updateSelection();
    });

    document.getElementById('revenueYear').addEventListener('change', function () {
        updateSelection();
    });

    function updateSelection() {
        const productYear = document.getElementById('productYear').value;
        const revenueYear = document.getElementById('revenueYear').value;
        changeManagement(`overview?productYear=` + productYear + `&revenueYear=` + revenueYear, 'overview');
    }

    function exportData() {
        // Define months and data arrays
        let months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
        let dataProduct = [
            months,
    ${chartProduct}
        ];
        let dataRevenue = [
            months,
    ${chartRevenue}
        ];

        // Create a new workbook
        let wb = XLSX.utils.book_new();

        // Convert data to sheets
        let wsProduct = XLSX.utils.aoa_to_sheet(dataProduct);
        let wsRevenue = XLSX.utils.aoa_to_sheet(dataRevenue);

        // Append sheets to workbook with meaningful names
        XLSX.utils.book_append_sheet(wb, wsProduct, 'Product');
        XLSX.utils.book_append_sheet(wb, wsRevenue, 'Revenue');

        // Generate XLSX file and trigger download
        XLSX.writeFile(wb, 'Export.xlsx');
    }

    //////////////////////
    /// chart-product ///
    ////////////////////
    new Chart(document.getElementById('chart-product'), {
        type: 'bar',
        data: {
            labels: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12],
            datasets: [{
                    label: 'Total product sold',
                    data: ${chartProduct},
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
                    data: ${chartRevenue},
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
                    data: [${productSoldThisMonth}, Math.max(0, 100 - ${productSoldThisMonth})],
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
                    orderArray.push(0, 1);
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
                    data: [${revenueThisMonth}, Math.max(0, 1000 - ${revenueThisMonth})],
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
                    orderArray.push(0, 1);
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