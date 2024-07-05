<%-- 
    Document   : home
    Created on : May 24, 2024, 4:03:53 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Home</title>
        <link rel="stylesheet" href="/css/user/home.css">
    </head>

    <body>
        <c:if test="${sessionScope.account == null}">
            <jsp:include page="../layout/header.jsp" />
        </c:if>
        <c:if test="${sessionScope.account != null}">
            <jsp:include page="../layout/header_loggedIn.jsp" />
        </c:if>
        <div class="banner">
            <img class="banner-image" src="/image/banner.jpg" style="display:block; opacity: 1" />
            <img class="banner-image" src="/image/brand.jpg" />
            <img class="banner-image" src="/image/category_dessert.jpg" />
            <img class="banner-image" src="/image/category_food.jpg" />
            <div class="banner-groupOfFeature">
                <div class="banner-groupOfFeature-feature">
                    <i class="fa-solid fa-truck-fast"></i>
                    <p>
                        Fast Shipping
                    </p>
                </div>
                <div class="banner-groupOfFeature-feature">
                    <i class="fa-solid fa-utensils"></i>
                    <p>
                        High quality food
                    </p>
                </div>
                <div class="banner-groupOfFeature-feature">
                    <i class="fa-solid fa-user-shield"></i>
                    <p>
                        Secured payment
                    </p>
                </div>
                <div class="banner-groupOfFeature-feature">
                    <i class="fa-solid fa-door-open"></i>
                    <p>Open 16h/day</p>
                </div>
            </div>
        </div>

        <div class="category">
            <h1 class="title">
                OUR CATEGORY
            </h1>
            <div class="category-groupOfCategory">
                <div class="category-groupOfCategory-category" style="background: #964b0080" onclick="window.location.href = '/product/category?name=Food'">
                    <img src="/image/category_food.jpg" >
                    <p>Food</p>
                    <span></span>
                </div>
                <div class="category-groupOfCategory-category" style="background: #FF000080" onclick="window.location.href = '/product/category?name=Drink'">
                    <img src="/image/category_drink.jpg" >
                    <p>Drink</p>
                    <span></span>
                </div>
                <div class="category-groupOfCategory-category" style="background: #FF990080" onclick="window.location.href = '/product/category?name=Dessert'">
                    <img src="/image/category_dessert.jpg" >
                    <p>Dessert</p>
                    <span></span>
                </div>
                <div class="category-groupOfCategory-category" style="background: #00FF0080" onclick="window.location.href = '/product/category?name=Snack'">
                    <img src="/image/category_snack.jpg" >
                    <p>Snack</p>
                    <span></span>
                </div>
            </div>
        </div>

        <div class="mostSoldProduct">
            <h1 class="title">
                MOST SOLD PRODUCT
            </h1>
            <div class="mostSoldProduct-groupOfProduct">
                <c:forEach items="${listMostSold}" var="product">
                    <div class="mostSoldProduct-groupOfProduct-detail" onclick="window.location.href = '/product/detail?id=${product.id}'">
                        <img src="data:image/jpeg;base64, ${product.imgBase64}" >
                        <p>${product.name}</p>
                        <p class="mostSoldProduct-groupOfProduct-detail-price">$${product.price}</p>
                    </div>
                </c:forEach>
            </div>
            <button type="button" class="mostSoldProduct-button-loadmore" onclick="loadMoreMostSold()">Load more</button>
        </div>

        <div class="newProduct">
            <h1 class="title">
                NEW PRODUCT
            </h1>
            <div class="newProduct-groupOfProduct">
                <c:forEach items="${listNewProduct}" var="product">
                    <div class="newProduct-groupOfProduct-detail" onclick="window.location.href = '/product/detail?id=${product.id}'" >
                        <img src="data:image/jpeg;base64, ${product.imgBase64}" >
                        <p>${product.name}</p>
                        <p class="newProduct-groupOfProduct-detail-price">$${product.price}</p>
                    </div>
                </c:forEach>
            </div>
            <button type="button" class="newProduct-button-loadmore" onclick="loadMoreNewProduct()">Load more</button>
        </div>
        <jsp:include page="../layout/footer.jsp"/>
    </body>
    <script src="/js/home.js"></script>

</html>
