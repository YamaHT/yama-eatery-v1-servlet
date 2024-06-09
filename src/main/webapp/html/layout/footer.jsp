<%-- 
    Document   : footer
    Created on : May 24, 2024, 4:01:40 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<footer>
    <div class="row">
        <div class="col" style="text-align:center">
            <img class="logo" src="/image/logo.jpg" alt="logo" />
        </div>
        <div class="col">
            <h3>Office <div class="underline"><span></span></div>
            </h3>
            <p>Country: Viet Nam</p>
            <p>Address: 600, Nguyen Van Cu Street, An Binh Ward, Ninh Kieu District, Can Tho City</p>
            <p>School: Dai Hoc FPT Can Tho</p>
            <br />
            <p>Email: DuyLPCE181153@fpt.edu.vn</p>
            <p>Phone: +84 123 456 789</p>
        </div>
        <div class="col">
            <h3>Link <div class="underline"><span></span></div>
            </h3>
            <div class="social-icons">
                <a href="https://www.facebook.com"><i class="fab fa-facebook-f"></i></a>
                <a href="https://www.twitter.com"><i class="fab fa-twitter"></i></a>
                <a href="https://chat.zalo.me"><i class="fa-regular fa-comment-dots"></i></a>
                <a href="https://www.youtube.com"><i class="fab fa-youtube"></i></a>
            </div>
        </div>
        <div class="col">
            <h3>Remind me <div class="underline"><span></span></div>
            </h3>
            <form asp-controller="Index" asp-action="RemindMe" method="post">
                <i class="far fa-envelope"></i>
                <input type="email" name="email" placeholder="Enter your email address" required />
                <button type="submit" class="btn">Submit</button>
            </form>
        </div>
    </div>
    <hr />
    <p class="copyright">Copyright © 2024 Yama, Inc. FPTU Can Tho</p>
</footer>