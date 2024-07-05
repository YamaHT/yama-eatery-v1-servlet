<%-- 
    Document   : historyOrder
    Created on : May 28, 2024, 2:40:31 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <jsp:include page="../layout/header_loggedIn.jsp"/>
        <div class="historyOrder-container">
            <div class="historyOrder">
                <h1 class="historyOrder-header">History Order</h1>
                <div class="historyOrder-body">
                    <table class="historyOrder-body-table">
                        <thead>
                            <tr>
                                <th>Recipient<br />Name</th>
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
                            <c:forEach begin="0" end="${listOrder.size()-1}" step="1" var="i" >
                                <tr class="historyOrder-body-table-content" onclick="showContent(${i})">
                                    <td>${listOrder[i].shipping.recipientName}</td>
                                    <td>${listOrder[i].shipping.address}</td>
                                    <td style="padding: 1% 0;">${listOrder[i].shipping.phone}</td>
                                    <td>${listOrder[i].orderDate}</td>
                                    <td>${listOrder[i].shipping.deliveryDate}</td>
                                    <td>${listOrder[i].shipping.delivery.type}</td>
                                    <td>${listOrder[i].quantity}</td>
                                    <td><strong>$${listOrder[i].total}</strong></td>
                                    <c:if test="${listOrder[i].status.id != 3}">
                                        <td class="historyOrder-body-table-status ${listOrder[i].status.name}">
                                            <p>${listOrder[i].status.name}</p>
                                        </td>
                                    </c:if>
                                    <c:if test="${listOrder[i].status.id == 3}">
                                        <%@ page import="java.util.Date" %>
                                        <c:set var="currentDate" value="<%= new Date() %>" />
                                        <c:if test="${listOrder[i].shipping.deliveryDate.before(currentDate)}">
                                            <td class="historyOrder-body-table-status Success">
                                                <p>Success</p>
                                            </td>
                                        </c:if>
                                        <c:if test="${listOrder[i].shipping.deliveryDate.after(currentDate)}">
                                            <td class="historyOrder-body-table-status Delivering">
                                                <p>Delivering</p>
                                            </td>
                                        </c:if>
                                    </c:if>

                                </tr>
                                <c:forEach items="${listOrderDetailInOrder[i]}" var="orderDetail">
                                    <tr class="historyOrder-body-table-subcontent" id="subcontent-${i}" style="display: none;">
                                        <td></td>
                                        <td class="historyOrder-body-table-subcontent-image"><img src="data:image/jpeg;base64, ${orderDetail.product.imgBase64}">
                                        </td>
                                        <td>${orderDetail.product.name}</td>
                                        <td>$<span>${orderDetail.product.price}</span>/1 product</td>
                                        <td>${orderDetail.amount}</td>
                                        <td><strong>$${orderDetail.subtotal}</strong></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                </c:forEach>
                            </c:forEach>

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