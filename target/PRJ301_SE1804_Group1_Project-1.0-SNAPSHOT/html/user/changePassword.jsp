<%-- 
    Document   : changePassword
    Created on : May 24, 2024, 4:03:23 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Change Password</title>
        <link rel="stylesheet" href="/css/user/changeForgotPassword.css">
    </head>

    <body>
        <jsp:include page="../layout/header_loggedIn.jsp"/>
        <div class="form-box-container">
            <form action="/auth/changePassword" method="post" class="form-box changePassword active">
                <h1 class="form-box-title">CHANGE PASSWORD</h1>
                <div class="form-box-input">
                    <input type="password" name="oldPass" id="oldPass" required>
                    <label for="oldPass">Old password</label>
                    <i class="fa-solid fa-unlock"></i>
                </div>
                <div class="form-box-input">
                    <input type="password" name="newPass" id="newPass" required>
                    <label for="newPass">New Password</label>
                    <i class="fa-solid fa-key"></i>
                </div>
                <div class="form-box-input">
                    <input type="password" name="conPass" id="conPass" required>
                    <label for="comPass">Confirm Password</label>
                    <i class="fa-solid fa-key"></i>
                </div>
                <button type="submit" class="form-box-button">SUBMIT</button>
            </form>
        </div>

        <jsp:include page="../layout/footer.jsp"/>
    </body>
</html>
