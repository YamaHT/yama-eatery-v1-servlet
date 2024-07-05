<%-- 
    Document   : transaction
    Created on : Jun 5, 2024, 7:04:36 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transaction</title>
        <link rel="stylesheet" href="/css/user/transaction.css">
    </head>
    <body>
        <jsp:include page="/html/layout/header_loggedIn.jsp"/>
        <div class="transaction">
            <div class="transaction-left">
                <button class="transaction-left-button-return" onclick="window.location.href = '/order'"><i
                        class="fa-solid fa-arrow-left"></i></button>
                <h1>List Product In Cart</h1>
                <div class="transaction-left-listItem">
                    <c:forEach items="${listOrderDetail}" var="orderDetail">
                        <div class="transaction-left-listItem-item">
                            <div class="transaction-left-listItem-item-image"><img src="data:image/jpeg;base64, ${orderDetail.product.imgBase64}" /></div>
                            <div class="transaction-left-listItem-item-info">
                                <p class="transaction-left-listItem-item-info-name">${orderDetail.product.name}</p>
                                <p class="transaction-left-listItem-item-info-price">$<span>${orderDetail.product.price}</span>/1 product</p>
                            </div>
                            <p class="transaction-left-listItem-item-quantity">${orderDetail.amount}</p>
                            <p class="transaction-left-listItem-item-subtotal">$<span>${orderDetail.subtotal}</span></p>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class="transaction-right">
                <h1>CHECKOUT</h1>
                <form action="/order/checkout" method="post" class="transaction-right-form">
                    <div>
                        <div class="transaction-right-form-input">
                            <label for="name">Full name</label>
                            <input type="text" name="name" id="name" value="${sessionScope.account.profile.name}" required />
                        </div>
                        <div class="transaction-right-form-input">
                            <label for="phone">Phone number</label>
                            <input type="text" name="phone" id="phone" value="${sessionScope.account.profile.phone}" pattern="\d{10}"
                                   oninvalid="alert('Vui lòng nhập sđt 10 số')" required />
                        </div>
                        <div class="transaction-right-form-input">
                            <label for="address">Address</label>
                            <input type="text" name="address" id="address" value="${sessionScope.account.profile.address}" required />
                        </div>
                        <div class="transaction-right-form-input transaction-right-form-input-select">
                            <label for="delivery">Delivery</label>
                            <select name="delivery" id="delivery" required>
                                <option data-bind="0" value="">----- Delivery -----</option>
                                <c:forEach items="${listDelivery}" var="delivery" >
                                    <option data-bind="${delivery.price}" value="${delivery.id}">${delivery.type} - $${delivery.price} (Time: ${delivery.time} minute)</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="transaction-right-form-detail">
                        <div class="transaction-right-form-detail-quantity">
                            <div>Total Quantity</div>
                            <div class="bold-value">${order.quantity}</div>
                        </div>
                        <div class="transaction-right-form-detail-price">
                            <div>Total Price</div>
                            <div id="totalPrice" class="bold-value">$<span>${order.total}</span></div>
                        </div>
                    </div>
                    <button type="submit" class="transaction-right-form-button-checkout">CHECKOUT</button>
                </form>
            </div>
        </div>
        <jsp:include page="/html/layout/footer.jsp"/>
        <script>
            var deliverySelect = document.getElementById('delivery');
            var totalPrice = document.querySelector('#totalPrice span');
            deliverySelect.addEventListener("change", function () {
                var selectedOption = deliverySelect.options[deliverySelect.selectedIndex];
                var selectedPrice = parseFloat(selectedOption.getAttribute('data-bind'));
                let newTotalPrice = parseFloat(`${order.total}`) + selectedPrice;
                totalPrice.innerText = newTotalPrice.toFixed(1);
            });
        </script>
    </body>
</html>
