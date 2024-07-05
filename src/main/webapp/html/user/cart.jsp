<%-- 
    Document   : cart
    Created on : May 24, 2024, 4:03:18 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Cart</title>
        <link href="/css/user/cart.css" rel="stylesheet" />
    </head>

    <body>
        <jsp:include page="../layout/header_loggedIn.jsp"/>
        <div class="cart">
            <section class="cart-table-container">
                <table class="cart-table">
                    <tr>
                        <th>Product</th>
                        <th>Category</th>
                        <th>Amount</th>
                        <th>Subtotal</th>
                        <th></th>
                    </tr>
                    <form action="/order/update" class="cart-table-form" method="post">
                        <c:forEach items="${listOrderDetail}" var="orderDetail">
                            <tr>
                                <td>
                                    <div class="cart-table-form-info">
                                        <img src="data:image/jpeg;base64, ${orderDetail.product.imgBase64}" alt="" />
                                        <div class="cart-table-form-info-details">
                                            <p>${orderDetail.product.name}</p>
                                            <small>Price: $${orderDetail.product.price}</small>
                                        </div>
                                    </div>
                                </td>
                                <td>${orderDetail.product.category.name}</td>
                                <td>
                                    <input type="number" name="amount" class="input-quantity"
                                           value="${orderDetail.amount}" min="1" max="${orderDetail.product.inventory}" />
                                    <input type="hidden" name="productId" value="${orderDetail.product.id}" />
                                </td>
                                <td>$${orderDetail.subtotal}</td>
                                <td>
                                    <button class="cart-table-form-button-delete" type="button" 
                                            onclick="window.location.href = '/order/delete?productId=${orderDetail.product.id}'">
                                        <i class="fa-solid fa-trash"></i>
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </form>

                </table>
            </section>

            <div class="cart-info">
                <button class="cart-button-animation cart-button-animation-update" id="updateButton">
                    <span>Update</span><i></i>
                </button>
                <div class="cart-info-detail">
                    <div class="cart-info-detail-amount">
                        <p>Quantity</p>
                        <p>${order.quantity}</p>
                    </div>
                    <div class="cart-info-detail-price">
                        <p>Total</p>
                        <p>$${order.total}</p>
                    </div>
                </div>
            </div>
            <c:if test="${order.quantity != 0}">
                <button onclick="window.location.href = '/order/checkout'"
                        class="cart-button-animation cart-button-animation-checkout">
                    <span>Check out</span><i></i>
                </button>
            </c:if>
            <button class="cart-button-animation cart-button-animation-return"
                    onclick="window.location.href = '/product'">
                <span>Return to Shopping</span><i></i></button>
        </div>
        <jsp:include page="../layout/footer.jsp"/>
    </body>
    <script>
        const inputElements = document.querySelectorAll('.input-quantity');
        const updateButton = document.getElementById('updateButton');

        inputElements.forEach((input) => {
            input.addEventListener('input', function () {
                const atLeastOneChanged = Array.from(inputElements).some(input => input.value !== input.defaultValue);
                if (atLeastOneChanged) {
                    updateButton.style.display = "block";
                } else {
                    updateButton.style.display = "none";
                }
            });
        });

        const form = document.querySelector('.cart-table-form');
        updateButton.addEventListener('click', function () {
            form.submit();
        });
    </script>

</html>