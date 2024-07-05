<%-- 
    Document   : registerOTP
    Created on : Jun 12, 2024, 2:07:55 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>OTP Verification</title>
        <link rel="stylesheet" href="/css/user/changeForgotPassword.css"/>
    </head>
    <body>
        <jsp:include page="../layout/header.jsp"/>
        <div class="form-box-container">
            <form action="/auth/register" method="post" class="form-box otp active">
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
                <p class="form-box-link">Didn't received OTP? <a id="resendOTP">Resend</a></p>
            </form>
        </div>

        <jsp:include page="../layout/footer.jsp"/>
    </body>
    <script src="/js/forgotPassword.js"></script>
    <script>
                    history.pushState({}, '', '/auth/registerOTP');
    </script>
</html>
