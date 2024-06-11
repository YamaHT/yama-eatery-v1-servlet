<%-- 
    Document   : header_loggedIn
    Created on : May 24, 2024, 4:01:34 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="https://kit.fontawesome.com/31a6f4185b.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="/css/layout/layout.css">
<%
    java.util.List<Data.Model.Category> listCategory = new Data.Repository.User.ProductRepository().getAllCategory();
    request.setAttribute("listCategory", listCategory);
%>
<div class="blur" onclick="hideSidenav()"></div>
<header>
    <div class="header-logo">
        <img src="/image/brand.jpg" alt="">
    </div>
    <nav class="header-navigation">
        <ul class="header-navigation-menu">
            <li><a href="#">Home</a><span></span></li>
            <li class="subnav-holder">
                <a href="#">
                    Product <i class="fa-solid fa-caret-down"></i>
                </a>
                <ul class="subnav">
                    <c:forEach items="${listCategory}" var="category">
                        <li><a href="/product/category?name=${category.name}">${category.name}</a></li>
                    </c:forEach>

                </ul>
            </li>
            <li><a href="#">About</a><span></span></li>
            <li><a href="#">Contact </a><span></span></li>
            <li><a href="#">Feedback </a><span></span></li>

        </ul>
    </nav>

    <div class="headerLoggedIn-button">
        <i class="fa-solid fa-bars header-navigation-mobile" onclick="showSidenav()"></i>
        <i class="fa-solid fa-cart-shopping header-button-cart"></i>
        <i onclick="showSearchBar(this)" class="fa-solid fa-magnifying-glass header-button-search"></i>
        <form action="/product/search" method="get" class="header-button-search-bar" style=" display: none;">
            <input type="text" id="name" name="name" placeholder="Search product..." />
            <i class="fa-solid fa-xmark header-button-search-bar-close" onclick="emptySearchInput()"></i>
            <i class="fa-solid fa-magnifying-glass header-button-search-bar-search" onclick="submitSearchForm()"></i>
        </form>
        <div class="header-button-user subnav-user-holder">
            <img src="/image/logo.jpg" alt="">
            <ul class="subnav-user">
                <li><a href="#"><i class="fa-solid fa-image"></i> Profile</a></li>
                <li><a href="#"><i class="fa-solid fa-list-check"></i> History Order</a></li>
                <li><a href="#"><i class="fa-solid fa-comments"></i> My feedback</a></li>
                <li> <a href="#"><i class="fa-solid fa-arrow-right-from-bracket"></i> Log out</a></li>
            </ul>
        </div>
    </div>
    <nav class="header-navigation-side">
        <div class="header-navigation-side-logo">
            <img src="/image/brand.jpg" alt="">
            <i class="fa-solid fa-xmark" onclick="hideSidenav()"></i>
        </div>
        <ul class="header-navigation-side-menu">
            <li><a href="#">Home</a><span></span></li>
            <li>
                <a href="#" onclick="return showSidenavSubmenu(this)">
                    Product <i class="fa-solid fa-caret-down"></i>
                </a>
                <span></span>
                <ul class="subnav">
                    <li><a href="/product">All Product</a></li>
                    <c:forEach items="${listCategory}" var="category">
                        <li><a href="/product/category?name=${category.name}">${category.name}</a></li>
                        </c:forEach>
                </ul>

            </li>
            <li><a href="#">About</a><span></span></li>
            <li><a href="#">Contact </a><span></span></li>
            <li><a href="#">Feedback </a><span></span></li>
        </ul>
    </nav>
</header>

<script src="/js/header.js"></script>