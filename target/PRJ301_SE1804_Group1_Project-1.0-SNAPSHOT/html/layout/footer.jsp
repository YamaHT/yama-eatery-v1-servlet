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
<script>
    window.onload = function () {
        if (`${error}` !== '') {
            showError(`${error}`);
        }
        if (`${success}` !== '') {
            showSuccess(`${success}`);
        }
    };
    function showError(error) {
        var divError = document.createElement('div');
        divError.style.position = 'fixed';
        divError.style.display = 'flex';
        divError.style.justifyContent = 'space-between';
        divError.style.alignItems = 'center';
        divError.style.right = '2%';
        divError.style.top = '10%';
        divError.style.color = '#FFF';
        divError.style.padding = '1%';
        divError.style.background = '#F00';
        divError.style.borderRadius = '10px';
        divError.style.zIndex = '10';
        divError.innerHTML = `<i class="fa-solid fa-circle-exclamation" style="width: 20%; text-align: center; font-size: 3rem;"></i>
                <p style="width: 80%; margin: 0; font-size: 1.5rem;">` + error + `</p>`;
        document.body.appendChild(divError);
        setTimeout(function () {
            divError.remove();
        }, 3000);
    }

    function showSuccess(success) {
        var divSuccess = document.createElement('div');
        divSuccess.style.position = 'fixed';
        divSuccess.style.display = 'flex';
        divSuccess.style.justifyContent = 'space-between';
        divSuccess.style.alignItems = 'center';
        divSuccess.style.right = '2%';
        divSuccess.style.top = '10%';
        divSuccess.style.color = '#FFF';
        divSuccess.style.padding = '1%';
        divSuccess.style.background = '#0A0';
        divSuccess.style.borderRadius = '10px';
        divSuccess.style.zIndex = '10';
        divSuccess.innerHTML = `<i class="fa-solid fa-circle-check" style="width: 20%; text-align: center; font-size: 3rem;"></i>
                <p style="width: 80%; margin: 0; font-size: 1.5rem;">` + success + `</p>`;
        document.body.appendChild(divSuccess);
        setTimeout(function () {
            divSuccess.remove();
        }, 3000);
    }
</script>