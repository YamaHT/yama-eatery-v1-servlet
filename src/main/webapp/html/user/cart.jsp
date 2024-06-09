<%-- 
    Document   : cart
    Created on : May 24, 2024, 4:03:18 PM
    Author     : ADMIN
--%>

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
                    <th>Quantity</th>
                    <th>Subtotal</th>
                    <th></th>
                </tr>
                <form action="#" class="cart-table-form" method="post">
                    <tr>
                        <td>
                            <div class="cart-table-form-info">
                                <img src="Component/Image/brand.jpg" alt="" />
                                <div class="cart-table-form-info-details">
                                    <p>Product name</p>
                                    <small>Price: 200$</small>
                                </div>
                            </div>
                        </td>
                        <td>Snack</td>
                        <td>
                            <input type="number" name="Quantity" value="2" class="input-quantity" />
                            <input type="hidden" name="ProductId" value="@suborder.ProductId" />
                        </td>
                        <td>$400</td>
                        <td>
                            <button class="cart-table-form-button-delete" type="button" onclick="deleteProduct(1)">
                                <i class="fa-solid fa-trash"></i>
                            </button>
                        </td>
                    </tr>
                </form>

            </table>
        </section>

        <div class="cart-info">
            <button class="cart-button-animation cart-button-animation-update" id="updateButton">
                <span>Update</span><i></i>
            </button>
            <div class="cart-info-detail">
                <div class="cart-info-detail-amount">
                    <p>Amount</p>
                    <p>2</p>
                </div>
                <div class="cart-info-detail-price">
                    <p>Total</p>
                    <p>$400</p>
                </div>
            </div>
        </div>
        <button onclick="window.location.href='/Order/Checkout'"
            class="cart-button-animation cart-button-animation-checkout">
            <span>Check out</span><i></i>
        </button>
        <button class="cart-button-animation cart-button-animation-return"
            onclick="window.location.href='/Product/Index'">
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

    const form = document.getElementById('.cart-table-home');
    updateButton.addEventListener('click', function () {
        form.submit();
    });

    function deleteProduct(productId) {

        $.ajax({
            type: "POST",
            url: '/Order/Delete',
            data: { ProductId: productId },
            success: function (data) {
                form.submit();
            },
            error: function () {
            }
        });
    }

</script>

</html>