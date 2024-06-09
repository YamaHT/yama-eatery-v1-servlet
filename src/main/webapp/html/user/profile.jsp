<%-- 
    Document   : profile
    Created on : May 24, 2024, 4:04:16 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Profile</title>
        <link rel="stylesheet" href="/css/user/profile.css">
    </head>

    <body>
        <c:if test="${sessionScope.account == null}">
            <jsp:include page="../layout/header.jsp"/>
        </c:if>
        <c:if test="${sessionScope.account != null}">
            <jsp:include page="../layout/header_loggedIn.jsp"/>
        </c:if>
        <div class="profile">
            <div class="profile-image">
                <img id="avatar" src="data:image/jpeg;base64,${sessionScope.account.profile.imgBase64}" alt="Avatar">
                <button type="button" onclick="document.getElementById('file').click()">
                    <i class="fa-solid fa-camera"></i>
                </button>
            </div>
            <form action="/profile/update" method="post" class="profile-form" enctype="multipart/form-data">
                <div class="profile-form-input">
                    <div class="profile-form-input-tag">
                        <label for="fullname">Full name</label>
                        <input type="text" id="fullname" name="fullname" value="${sessionScope.account.profile.name}">
                    </div>

                    <div class="profile-form-input-tag">
                        <label for="phone">Phone</label>
                        <input type="text" id="phone" name="phone" value="${sessionScope.account.profile.phone}" pattern="\d{10}"
                               oninvalid="setCustomValidity('Please enter a valid 10-digit phone number')"
                               oninput="setCustomValidity('')">
                    </div>

                    <div class="profile-form-input-tag">
                        <label for="address">Address</label>
                        <input type="text" id="address" name="address" value="${sessionScope.account.profile.address}">
                    </div>

                    <div class="profile-form-input-tag-less">
                        <label for="email">Email:</label>
                        <p>${sessionScope.account.email}</p>
                        <button>
                            <i class="fa-solid fa-pen"></i>
                        </button>
                    </div>

                    <div class="profile-form-input-tag-less">
                        <label for="birthday">Birthday:</label>
                        <input type="text" id="birthday" name="birthday" value="${birthday}" readonly>
                        <button type="button" id="date-picker-button">
                            <i class="fa-solid fa-calendar-days"></i>
                            <input type="date" id="birthday-input" onchange="updateBirthdayDisplay(event)">
                        </button>

                    </div>

                    <button  class="profile-form-input-button-changepassword">
                        Change password
                    </button>
                </div>
                <input type="file" onchange="onFileSelected(event)" name="image" id="file">
                <button type="submit" class="profile-button-save">SAVE</button>
            </form>
        </div>
        <jsp:include page="../layout/footer.jsp"/>
    </body>
    <script src="/js/profile.js"></script>

</html>