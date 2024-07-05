<%-- 
    Document   : aboutUs
    Created on : May 24, 2024, 4:03:14 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>About Us</title>
        <link rel="stylesheet" href="/css/user/aboutUs.css">
    </head>

    <body>
        <c:if test="${sessionScope.account == null}">
            <jsp:include page="../layout/header.jsp" />
        </c:if>
        <c:if test="${sessionScope.account != null}">
            <jsp:include page="../layout/header_loggedIn.jsp" />
        </c:if>
        <div class="about-us">
            <div class="about-us-content">
                <h1>ABOUT US</h1>
                <p>Welcome to Yama, where culinary excellence meets a warm and inviting atmosphere. Nestled in the heart of
                    the city, Yama is your go-to destination for a delightful dining experience that tantalizes all your
                    senses. Our menu is thoughtfully curated to offer an exquisite range of food, drinks, desserts, and
                    snacks, ensuring there's something to satisfy every palate.<br /><br />

                    At Yama, we pride ourselves on using only the freshest and highest quality ingredients. Our skilled
                    chefs are passionate about crafting dishes that not only taste amazing but also celebrate the vibrant
                    flavors of our diverse cuisine. Whether you're in the mood for a hearty meal, a refreshing drink, a
                    sweet treat, or a quick snack, our extensive menu has you covered. Join us at Yama,
                    where every meal is a celebration of good food, great company, and unforgettable moments.</p>
                <button type="button" onclick="window.location.href='/product'">GET OUR FOOD NOW</button>
            </div>

            <div class="about-us-image">
                <picture>
                    <source srcset="/image/brand.jpg" media="(max-width: 768px)">
                    <img src="/image/logofff.jpg" alt="logo">
                </picture>
            </div>
        </div>
        <div class="link">
            <i class="fa-brands fa-facebook"></i>
            <i class="fa-brands fa-square-x-twitter"></i>
            <i class="fa-brands fa-instagram"></i>
            <i class="fa-brands fa-youtube"></i>
        </div>
        <jsp:include page="../layout/footer.jsp" />
    </body>

</html>