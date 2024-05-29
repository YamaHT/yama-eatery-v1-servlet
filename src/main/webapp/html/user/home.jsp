<%-- 
    Document   : home
    Created on : May 24, 2024, 4:03:53 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Home</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user/home.css">
    </head>

    <body>
        <jsp:include page="../layout/header.jsp"/>
        <div class="banner">
            <img class="banner-image" src="${pageContext.request.contextPath}/image/banner.jpg" alt="" />
            <img class="banner-image" src="${pageContext.request.contextPath}/image/brand.jpg" alt="" />
            <img class="banner-image" src="${pageContext.request.contextPath}/image/category_dessert.jpg" alt="" />
            <img class="banner-image" src="${pageContext.request.contextPath}/image/category_food.jpg" alt="" />
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
                <div class="category-groupOfCategory-category" style="background: #964b0080">
                    <img src="${pageContext.request.contextPath}/image/category_food.jpg" alt="">
                    <p>Food</p>
                    <span></span>
                </div>
                <div class="category-groupOfCategory-category" style="background: #FF000080">
                    <img src="${pageContext.request.contextPath}/image/category_drink.jpg" alt="">
                    <p>Drink</p>
                    <span></span>
                </div>
                <div class="category-groupOfCategory-category" style="background: #FF990080">
                    <img src="${pageContext.request.contextPath}/image/category_dessert.jpg" alt="">
                    <p>Dessert</p>
                    <span></span>
                </div>
                <div class="category-groupOfCategory-category" style="background: #00FF0080">
                    <img src="${pageContext.request.contextPath}/image/category_snack.jpg" alt="">
                    <p>Snack</p>
                    <span></span>
                </div>
            </div>
        </div>

        <div class="mostSoldProduct">
            <h1 class="title">
                TOP 10 MOST SOLD PRODUCT
            </h1>
            <div class="mostSoldProduct-groupOfProduct">
                <div class="mostSoldProduct-groupOfProduct-detail">
                    <img src="image/category_food.jpg" alt="">
                    <p>Yasuo</p>
                    <p class="mostSoldProduct-groupOfProduct-detail-price">$160.0</p>
                </div>
                
            </div>
            <button class="mostSoldProduct-button-loadmore">Load more</button>
        </div>

        <div class="newProduct">
            <h1 class="title">
                TOP 10 NEW PRODUCT
            </h1>
            <div class="newProduct-groupOfProduct">
                <div class="newProduct-groupOfProduct-detail">
                    <img src="image/category_food.jpg" alt="">
                    <p>Yasuo</p>
                    <p class="newProduct-groupOfProduct-detail-price">$160.0</p>
                </div>
                
            </div>
            <button class="newProduct-button-loadmore">Load more</button>
        </div>
        <jsp:include page="../layout/footer.jsp"/>
    </body>
    <script src="${pageContext.request.contextPath}/js/home.js"></script>

</html>
