<%-- 
    Document   : profile
    Created on : May 24, 2024, 4:04:16 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Profile</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user/profile.css">
    </head>

    <body>
        <jsp:include page="../layout/header.jsp"/>
        <div class="profile">
            <div class="profile-image">
                <img id="avatar" src="" alt="">
                <button type="button" onclick="document.getElementById('file').click()">
                    <i class="fa-solid fa-camera"></i>
                </button>
            </div>
            <div class="profile-form">
                <div class="profile-form-input">
                    <div class="profile-form-input-tag">
                        <label for="fullname">Fullname</label>
                        <input type="text" id="fullname" name="fullname" value="Yasuo">
                    </div>

                    <div class="profile-form-input-tag">
                        <label for="phone">Phone</label>
                        <input type="text" id="phone" name="phone" value="0123698745">
                    </div>

                    <div class="profile-form-input-tag">
                        <label for="address">Address</label>
                        <input type="text" id="address" name="address" value="09 Nguyễn Văn Cừ, Ninh Kiều, Cần Thơ">
                    </div>

                    <div class="profile-form-input-tag-less">
                        <label for="email">Email:</label>
                        <p>dangttce182414@fpt.edu.vn</p>
                        <button>
                            <i class="fa-solid fa-pen"></i>
                        </button>
                    </div>

                    <div class="profile-form-input-tag-less">
                        <label for="birthday">Birthday:</label>
                        <p>30-10-2004</p>
                        <button>
                            <i class="fa-solid fa-calendar-days"></i>
                        </button>
                    </div>
                    <button type="button" class="profile-form-input-button-changepassword">
                        Change password
                    </button>
                </div>
                <input type="file" onchange="onFileSelected(event)" name="file" id="file">
                <button class="profile-button-save">SAVE</button>
            </div>
        </div>
        <jsp:include page="../layout/footer.jsp"/>
    </body>
    <script src="${pageContext.request.contextPath}/js/profile.js"></script>

</html>