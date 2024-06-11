<%-- 
    Document   : product
    Created on : May 24, 2024, 4:04:05 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Product</title>
        <link rel="stylesheet" href="/css/user/product.css">
    </head>
    <body>
        <jsp:include page="../layout/header.jsp"/>
        <form method="get" class="filter">
            <input type="hidden" name="name" value="${name}"/>
            <div class="filter-price">
                <div class="filter-price-description">
                    <span id="filter-price-description-range1">
                        $${minPrice}
                    </span>
                    <span> &dash; </span>
                    <span id="filter-price-description-range2">
                        $${maxPrice}
                    </span>
                </div>
                <div class="filter-price-input">
                    <div class="filter-price-input-slider-track"></div>
                    <input type="range" name="priceMin" min="${minPrice}" max="${maxPrice}" lastMinPrice="${lastMinPrice}" id="slider-1" oninput="slideOne()">
                    <input type="range" name="priceMax" min="${minPrice}" max="${maxPrice}" lastMaxPrice="${lastMaxPrice}" id="slider-2" oninput="slideTwo()">
                </div>
            </div>
            <select class="filter-order" name="filter" id="orderSelect">
                <option id="filter-display" data-bind="${filter}" value="" style="text-align: center;"></option>
                <option id="Id-DESC" value="Id DESC">Newest</option>
                <option id="Id-ASC" value="Id ASC">Oldest</option>
                <option id="Price-DESC" value="Price DESC">Price highest to lowest</option>
                <option id="Price-ASC" value="Price ASC">Price lowest to highest</option>
                <option id="Name-ASC" value="Name ASC">Name A-Z</option>
                <option id="Name-DESC" value="Name DESC">Name Z-A</option>
            </select>
            <button class="filter-button" type="submit">Apply</button>
        </form>

        <div class="product-category-container">
            <div class="groupOfCategory">
                <div class="groupOfCategory-category ${categoryName == null ? "active" : ""}" onclick="window.location.href = '/product'">
                    <i class="fa-solid fa-house"></i>
                    <p>All</p>
                </div>
                <div class="groupOfCategory-category ${categoryName == "Food" ? "active" : ""}" onclick="window.location.href = '/product/category?name=Food'" >
                    <i class="fa-solid fa-utensils"></i>
                    <p>Food</p>
                </div>
                <div class="groupOfCategory-category ${categoryName == "Drink" ? "active" : ""}" onclick="window.location.href = '/product/category?name=Drink'" >
                    <i class="fa-solid fa-martini-glass"></i>
                    <p>Drink</p>
                </div>
                <div class="groupOfCategory-category ${categoryName == "Dessert" ? "active" : ""}" onclick="window.location.href = '/product/category?name=Dessert'" >
                    <i class="fa-solid fa-ice-cream"></i>                    
                    <p>Dessert</p>
                </div>
                <div class="groupOfCategory-category ${categoryName == "Snack" ? "active" : ""}" onclick="window.location.href = '/product/category?name=Snack'" >
                    <i class="fa-solid fa-burger"></i>
                    <p>Snack</p>
                </div>
            </div>

            <div class="groupOfProduct">
                <c:forEach items="${listProduct}" var="product" varStatus="i">
                    <div class="groupOfProduct-product" id="p${i.index}" onclick="window.location.href = '/product/detail?id=${product.id}'">
                        <div class="groupOfProduct-product-header">
                            <div class="groupOfProduct-product-category">${product.category.name}</div>
                            <div class="groupOfProduct-product-price">$${product.price}</div>
                        </div>
                        <div class="groupOfProduct-product-image"> 
                            <img src="data:image/jpeg;base64,${product.imgBase64}">
                        </div>
                        <div class="groupOfProduct-product-name">${product.name}</div>
                        <button class="groupOfProduct-product-button-viewdetail">
                            VIEW DETAIL
                        </button>
                    </div>
                </c:forEach>
            </div>
        </div>
        <jsp:include page="../layout/paging.jsp"/>
        <jsp:include page="../layout/footer.jsp"/>
    </body>
    <script src="/js/product.js"></script>

</html>