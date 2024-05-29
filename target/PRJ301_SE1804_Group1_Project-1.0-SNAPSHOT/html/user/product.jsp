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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user/product.css">
    </head>
    <body>
        <jsp:include page="../layout/header.jsp"/>
        <form class="filter">
            <div class="filter-price">
                <div class="filter-price-description">
                    <span id="filter-price-description-range1">
                        $100
                    </span>
                    <span> &dash; </span>
                    <span id="filter-price-description-range2">
                        $400
                    </span>
                </div>
                <div class="filter-price-input">
                    <div class="filter-price-input-slider-track"></div>
                    <input type="range" name="priceMin" min="100" max="400" value="100" id="slider-1" oninput="slideOne()">
                    <input type="range" name="priceMax" min="100" max="400" value="400" id="slider-2" oninput="slideTwo()">
                </div>
            </div>
            <select class="filter-order" name="order" id="orderSelect">
                <option value="" style="text-align: center;">Order by: <span>Yasuo123</span></option>
                <option value="1">Newest</option>
                <option value="2">Oldest</option>
                <option value="3">Price highest to lowest</option>
                <option value="4">Price lowest to highest</option>
                <option value="5">Name A-Z</option>
                <option value="6">Name Z-A</option>
            </select>
            <button class="filter-button" type="submit">Apply</button>
        </form>

        <div class="product-category-container">
            <div class="groupOfCategory">
                <div class="groupOfCategory-category active">
                    <i class="fa-solid fa-house"></i>
                    <p>ALL</p>
                </div>
                <div class="groupOfCategory-category">
                    <i class="fa-solid fa-house"></i>
                    <p>ALL</p>
                </div>
                <div class="groupOfCategory-category">
                    <i class="fa-solid fa-house"></i>
                    <p>ALL</p>
                </div>
                <div class="groupOfCategory-category">
                    <i class="fa-solid fa-house"></i>
                    <p>ALL</p>
                </div>
                <div class="groupOfCategory-category">
                    <i class="fa-solid fa-house"></i>
                    <p>ALL</p>
                </div>
            </div>

            <div class="groupOfProduct">
                <c:forEach items="${listProduct}" var="product" varStatus="i">
                <div class="groupOfProduct-product" id="p${i.index}">
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
    <script src="${pageContext.request.contextPath}/js/product.js"></script>

</html>