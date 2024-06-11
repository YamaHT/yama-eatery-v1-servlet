<%-- 
    Document   : productDetail
    Created on : May 24, 2024, 4:04:11 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Product Detail</title>
        <link rel="stylesheet" href="/css/user/productDetail.css">
    </head>
    <body>
        <jsp:include page="../layout/header.jsp"/>
        <div class="productDetail">
            <div class="productDetail-image">
                <img src="data:image/jpeg;base64,${product.imgBase64}" >
            </div>
            <div class="productDetail-content">
                <div class="productDetail-content-header">
                    <c:if test="${product.inventory == 0}">
                        <div style="color: #F00">OUT OF STOCK</div>
                    </c:if>
                        <c:if test="${product.inventory != 0}">
                        <div>IN STOCK</div>
                    </c:if>
                    <div>${product.category.name}</div>
                </div>
                <div class="productDetail-content-name">${product.name}</div>
                <div class="productDetail-content-price">$${product.price} <span>$${product.price*2}</span></div>
                <form class="productDetail-content-action">
                    <div class="productDetail-content-action-quantity">
                        <input type="number" name="quantity" value="0">
                    </div>
                    <div class="productDetail-content-action-addToCart">
                        <button>
                            ADD TO CART <i class="fa-solid fa-cart-shopping"></i>
                        </button>
                    </div>
                </form>
                <div class="productDetail-content-description">
                    ${product.description}
                </div>
            </div>
        </div>
        <div class="title">SIMILIAR PRODUCT</div>
        <div class="groupOfProduct">
            <c:forEach items="${listProduct}" var="pro" varStatus="i">
                <div class="groupOfProduct-product" id="p${i.index}" onclick="window.location.href = '/product/detail?id=${pro.id}'">
                    <div class="groupOfProduct-product-header">
                        <div class="groupOfProduct-product-category">${pro.category.name}</div>
                        <div class="groupOfProduct-product-price">$${pro.price}</div>
                    </div>
                    <div class="groupOfProduct-product-image"> 
                        <img src="data:image/jpeg;base64,${pro.imgBase64}" alt="category_food">
                    </div>
                    <div class="groupOfProduct-product-name">${pro.name}</div>
                    <button class="groupOfProduct-product-button-viewdetail">
                        VIEW DETAIL
                    </button>
                </div>
            </c:forEach>

        </div>
        <jsp:include page="../layout/footer.jsp"/>
    </body>

</html>