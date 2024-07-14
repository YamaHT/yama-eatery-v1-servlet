<%-- 
    Document   : management
    Created on : May 24, 2024, 4:02:08 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Management</title>
        <link rel="stylesheet" href="/css/admin/management.css">
        <script src="https://kit.fontawesome.com/31a6f4185b.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.17.0/xlsx.full.min.js"></script>
    </head>

    <body>
        <div class="management">
            <div class="management-left">
                <div class="management-left-sidebar">
                    <div class="management-left-sidebar-logo">
                        <img src="/image/brand.jpg">
                    </div>
                    <div class="management-left-sidebar-component" id="overview" onclick="changeManagement('overview', 'overview')">
                        <i class="fa-solid fa-chart-column"></i>
                        <p>Overview<br />Management</p>
                    </div>
                    <div class="management-left-sidebar-component" id="product" onclick="changeManagement('product', 'product')">
                        <i class="fa-solid fa-box-archive"></i>
                        <p>Product<br />Management</p>
                    </div>
                    <div class="management-left-sidebar-component" id="feedback" onclick="changeManagement('feedback', 'feedback')">
                        <i class="fa-solid fa-message"></i>
                        <p>Feedback<br />Management</p>
                    </div>
                    <div class="management-left-sidebar-component" id="order" onclick="changeManagement('order', 'order')">
                        <i class="fa-solid fa-file-invoice"></i>
                        <p>Order<br />Management</p>
                    </div>
                    <div class="management-left-sidebar-component" id="resource" onclick="changeManagement('resource', 'resource')">
                        <i class="fa-solid fa-warehouse"></i>
                        <p>Resource<br />Management</p>
                    </div>
                </div>
                <button type="button" class="management-left-resize" onclick="resizeSidebar()"><i
                        class="fa-solid fa-angle-left"></i></button>
            </div>
            <div class="management-right">
                <div class="management-right-header">
                    <p>OVERVIEW MANAGEMENT</p>
                    <i class="fa-solid fa-arrow-right-from-bracket" onclick="window.location.href = '/auth/logout'"></i>
                </div>
                <!-- ========== Start link html ========== -->
                <div class="management-right-body">
                </div>
                <!-- ========== End link html ========== -->
            </div>
        </div>
    </body>
    <script>
        history.pushState({}, '', '/admin/management');

        window.onload = () => {
            changeManagement("overview", "overview");
        }

        function changeManagement(lastEndPoint, title) {
            var component = document.getElementById(title);
            if (component
                    && component.classList.contains('active')
                    && title === lastEndPoint)
            {
                return;
            }

            const components = document.querySelectorAll('.management-left-sidebar-component');
            components.forEach((c) => {
                c.classList.remove('active');
            });

            if (component) {
                component.classList.add('active');
            }

            document.querySelector('.management-right-header p').innerHTML = title + ' Management';

            const rightBody = document.querySelector('.management-right-body');
            fetch("/html/layout/loading.jsp")
                    .then(response => {
                        return response.text();
                    })
                    .then(data => {
                        rightBody.innerHTML = data;
                    })
                    .catch(error => {
                        console.error('There was a problem with the fetch operation:', error);
                    });

            const jspPageUrl = `/admin/management/` + lastEndPoint;
            fetch(jspPageUrl)
                    .then(response => {
                        return response.text();
                    })
                    .then(data => {
                        rightBody.innerHTML = data;
                        const scripts = rightBody.querySelectorAll('script');
                        scripts.forEach(script => {
                            const newScript = document.createElement('script');
                            newScript.text = script.text;
                            rightBody.appendChild(newScript);
                            script.parentNode.removeChild(script);
                        });
                    })
                    .catch(error => {
                        rightBody.innerHTML = 'Error loading content';
                    });
        }

        function resizeSidebar() {
            const buttonResize = document.querySelector('.management-left-resize');
            const sidebar = document.querySelector('.management-left');
            const logoImage = document.querySelector('.management-left-sidebar-logo img');
            if (!buttonResize.classList.contains('resized')) {
                buttonResize.classList.add('resized');
                sidebar.classList.add('active');
                logoImage.src = `/image/logofff.jpg`;
            } else {
                buttonResize.classList.remove('resized');
                sidebar.classList.remove('active');
                logoImage.src = `/image/brand.jpg`;
            }
        }
        
        // BLOCK DEVTOOLS
        document.addEventListener('contextmenu', (e) => e.preventDefault());
        (function () {
            const threshold = 160;
            const checkDevTools = () => {
                if (window.outerWidth - window.innerWidth > threshold || window.outerHeight - window.innerHeight > threshold) {
                    document.body.innerHTML = "<h1>Developer tools are not allowed!</h1>\n<button type='button' onclick='location.reload()'>Return to previous</button>";
                }
            };
            setInterval(checkDevTools, 1000);
        })();
    </script>

</html>
