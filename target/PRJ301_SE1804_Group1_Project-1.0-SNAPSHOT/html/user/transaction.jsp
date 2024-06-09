<%-- 
    Document   : transaction
    Created on : Jun 5, 2024, 7:04:36 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transaction</title>
        <link rel="stylesheet" href="/css/user/transaction.css">
    </head>
    <body>
        <jsp:include page="/html/layout/header.jsp"/>
        <div class="transaction">
            <div class="transaction-left">
                <button class="transaction-left-button-return" onclick="window.location.href = '/Product/Index'"><i
                        class="fa-solid fa-arrow-left"></i></button>
                <h1>List Product In Cart</h1>
                <div class="transaction-left-listItem">
                    <div class="transaction-left-listItem-item">
                        <div class="transaction-left-listItem-item-image"><img src="/Image/category_dessert.jpg" /></div>
                        <div class="transaction-left-listItem-item-info">
                            <p class="transaction-left-listItem-item-info-name">product Name</p>
                            <p class="transaction-left-listItem-item-info-price">$<span>10</span>/1 product</p>
                        </div>
                        <p class="transaction-left-listItem-item-quantity">10</p>
                        <p class="transaction-left-listItem-item-subtotal">$<span>100</span></p>
                    </div>
                </div>
            </div>
            <div class="transaction-right">
                <h1>CHECKOUT</h1>
                <form class="transaction-right-form">
                    <div>
                        <div class="transaction-right-form-input">
                            <label for="FullName">Full name</label>
                            <input type="text" asp-for="FullName" id="FullName" value="" required />
                        </div>
                        <div class="transaction-right-form-input">
                            <label for="Phone">Phone number</label>
                            <input type="text" asp-for="Phone" id="Phone" value="" pattern="\d{10}"
                                   oninvalid="alert('Vui lòng nhập sđt 10 số')" required />
                        </div>
                        <div class="transaction-right-form-input">
                            <label for="Address">Address</label>
                            <input type="text" asp-for="Address" id="Address" value="" required />
                        </div>
                        <div class="transaction-right-form-input transaction-right-form-input-select">
                            <label for="Delivery">Delivery</label>
                            <select name="Delivery" id="Delivery" required>
                                <option value="">----- Delivery -----</option>
                                <option value="@deli.Price">@deli.Name - $@deli.Price</option>
                            </select>
                        </div>
                    </div>
                    <div class="transaction-right-form-detail">
                        <div class="transaction-right-form-detail-quantity">
                            <div>Total Quantity</div>
                            <div class="bold-value">999</div>
                        </div>
                        <div class="transaction-right-form-detail-price">
                            <div>Total Price</div>
                            <div class="bold-value">$<span>9999</span></div>
                        </div>
                    </div>
                    <button type="submit" class="transaction-right-form-button-checkout">CHECKOUT</button>
                </form>
            </div>
        </div>
        <jsp:include page="/html/layout/footer.jsp"/>

    </body>
</html>
