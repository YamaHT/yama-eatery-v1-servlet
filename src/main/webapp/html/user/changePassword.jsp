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
            <form action="" class="form-box changePassword active">
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
                    <input type="password" name="comPass" id="comPass" required>
                    <label for="comPass">Confirm Password</label>
                    <i class="fa-solid fa-key"></i>
                </div>
                <button type="submit" class="form-box-button">SUBMIT</button>
            </form>
            <form action="" class="form-box otp">
                <h1 class="form-box-title">OTP Authentication</h1>
                <p class="form-box-welcome">Enter the 6 digit OTP sent to your email</p>
                <div class="form-box-otpInput">
                    <input type="text" name="otp" maxlength="1">
                    <input type="text" name="otp" maxlength="1">
                    <input type="text" name="otp" maxlength="1">
                    <input type="text" name="otp" maxlength="1">
                    <input type="text" name="otp" maxlength="1">
                    <input type="text" name="otp" maxlength="1">
                </div>
                <button type="submit" class="form-box-button" disabled>VERIFY</button>
                <hr style="margin: 5% 0;" />
                <p class="form-box-link">Didn't received OTP? <a href="#" onclick="return register()">Resend</a></p>
            </form>
        </div>

        <jsp:include page="../layout/footer.jsp"/>
    </body>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="/js/changeforgotPassword.js"></script>

</html>
