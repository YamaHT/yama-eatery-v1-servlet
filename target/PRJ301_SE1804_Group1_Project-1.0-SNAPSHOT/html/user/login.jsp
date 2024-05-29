<%-- 
    Document   : login
    Created on : May 24, 2024, 4:03:58 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user/login.css">
    </head>
    <body>
        <jsp:include page="../layout/header.jsp"/>
        <div class="form-container">
            <div class="form login active">
                <form action="" class="form-box">
                    <h1 class="form-box-title">Login</h1>
                    <div class="form-box-input">
                        <input type="text" name="username" id="username" required>
                        <label for="username">Username</label>
                        <i class="fa-solid fa-user"></i>
                    </div>
                    <div class="form-box-input">
                        <input type="password" name="password" id="password" required>
                        <label for="password">Password</label>
                        <i class="fa-solid fa-lock"></i>
                    </div>
                    <div class="form-box-forgot">
                        <a href="#">Forgot your password?</a>
                    </div>
                    <button type="submit" class="form-box-button">Login</button>
                    <p class="form-box-link">No account? <a href="#" onclick="return register()">Register here</a></p>
                    <p class="form-box-continue">Or continue with</p>
                    <button type="submit" class="form-box-button google">
                        <i class="fa-brands fa-google-plus-g"></i>
                        <p>Login with Google</p>
                    </button>

                </form>
                <div class="form-welcome">
                    <h2>Welcome back!</h2>
                    <p>Please log in to explore our exquisite range of culinary delights and place your orders for an
                        exceptional dining experience at home.
                    </p>
                    <img src="${pageContext.request.contextPath}/image/category_food.jpg">
                </div>
            </div>
            <div class="form register">
                <div class="form-welcome form-welcome-register">
                    <h2>Welcome to Yama Eatery!</h2>
                    <p>Register to join us today and start enjoying exclusive benefits and tailored culinary experiences!
                    </p>
                    <img src="${pageContext.request.contextPath}/image/banner.jpg" alt="">
                </div>
                <form action="" class="form-box">
                    <h1 class="form-box-title">Register</h1>
                    <div class="form-box-input">
                        <input type="text" name="username" id="username" required>
                        <label for="username">Username</label>
                        <i class="fa-solid fa-user"></i>
                    </div>
                    <div class="form-box-input">
                        <input type="email" name="email" id="email" required>
                        <label for="email">Email</label>
                        <i class="fa-solid fa-envelope"></i>
                    </div>
                    <div class="form-box-input">
                        <input type="password" name="password" id="password" required>
                        <label for="password">Password</label>
                        <i class="fa-solid fa-lock"></i>
                    </div>
                    <button type="submit" class="form-box-button">Register</button>
                    <p class="form-box-link">Already have? <a href="#" onclick="return login()">Login here</a></p>
                </form>
            </div>
        </div>

        <jsp:include page="../layout/footer.jsp"/>
    </body>
    <script>
        function register() {
            document.querySelector('.login').classList.remove('active');
            document.querySelector('.register').classList.add('active');
            return false;
        }
        function login() {
            document.querySelector('.register').classList.remove('active');

            document.querySelector('.login').classList.add('active');
            return false;

        }
    </script>

</html>