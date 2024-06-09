<%-- 
    Document   : historyOrder
    Created on : May 28, 2024, 2:40:31 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="/css/user/historyOrder.css">
        <title>History Order</title>
    </head>

    <body>
        <jsp:include page="../layout/header.jsp"/>
        <div class="historyOrder-container">
            <div class="historyOrder">
                <h1 class="historyOrder-header">History Order</h1>
                <div class="historyOrder-body">
                    <table class="historyOrder-body-table">
                        <thead>
                            <tr>
                                <th>Recipent<br />Name</th>
                                <th>Address</th>
                                <th>Phone</th>
                                <th>Order<br />Date</th>
                                <th>Delivery<br />Date</th>
                                <th>Delivery<br />Type</th>
                                <th>Amount</th>
                                <th>Total</th>
                                <th>Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr class="historyOrder-body-table-content" onclick="showContent(0)">
                                <td>Le Phuoc Duy</td>
                                <td>09 Nguyen Van Cu, Ninh Kieu, Can Tho</td>
                                <td>0987654321</td>
                                <td>00:00:00 25/05/2024</td>
                                <td>00:05:00 25/05/2024</td>
                                <td>FAST</td>
                                <td>7</td>
                                <td><strong>$100</strong></td>
                                <td class="historyOrder-body-table-status incart">
                                    <p>Success</p>
                                </td>
                            </tr>
                            <tr class="historyOrder-body-table-subcontent" id="subcontent-0" style="display: none;">
                                <td></td>
                                <td class="historyOrder-body-table-subcontent-image"><img src="/image/category_dessert.jpg">
                                </td>
                                <td>Banh ngot</td>
                                <td>$<span>120</span>/1 product</td>
                                <td>7</td>
                                <td><strong>$840</strong></td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <jsp:include page="../layout/footer.jsp"/>
    </body>
    <script>
        function showContent(id) {
            const subcontents = document.querySelectorAll('.historyOrder-body-table-subcontent');
            subcontents.forEach((sub) => {
                sub.style.display = 'none';
            });
            const contents = document.querySelectorAll('#subcontent-' + id);
            contents.forEach((content) => {
                content.style.display = 'table-row';
            });

        }
    </script>

</html>