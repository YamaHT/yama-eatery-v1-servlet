<%-- 
    Document   : feedback
    Created on : May 24, 2024, 4:03:32 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Feedback</title>
        <link rel="stylesheet" href="/css/user/feedback.css">
    </head>

    <body>
        <jsp:include page="../layout/header.jsp"/>
        <div class="feedback-banner">
            <img src="/image/feedbackBanner.jpg" alt="feedbackBanner">
        </div>
        <div class="feedback-comment">
            <div class="feedback-comment-image"><img src="Component/Image/category_food.jpg" alt="avatar"></div>
            <form class="feedback-comment-form" method="post">
                <textarea name="feedback" placeholder="Enter your feedback..." oninput="auto_grow(this)"></textarea>
                <div class="feedback-comment-form-button">
                    <button type="reset" class="feedback-comment-form-button-cancel"
                            oninput="auto_grow(this)">Cancel</button>
                    <button type="submit" class="feedback-comment-form-button-send">Send</button>
                </div>
            </form>
        </div>
        <div class="feedback">
            <div class="feedback-user-image"><img src=""></div>
            <div class="feedback-user-content">
                <div class="feedback-user-content-header">
                    <p>Dullahan</p>
                    <i class="fa-solid fa-circle"></i>
                    <span>00:00:00 13/12/2024</span>
                </div>

                <p class="feedback-user-content-comment">
                    Chủ shop quá đẹp trai.
                </p>
            </div>
            <div class="feedback-admin">
                <div class="feedback-admin-info">
                    <p>Yama's assistance</p>
                    <i class="fa-solid fa-circle"></i>
                    <span>00:00:00 13/12/2024</span>
                </div>
                <div class="feedback-admin-response">Very handsome is my name</div>
            </div>
        </div>
        <jsp:include page="../layout/footer.jsp"/>
    </body>
    <script>
        function auto_grow(element) {
            element.style.height = "max-content";
            element.style.height = (element.scrollHeight) + "px";
        }
    </script>

</html>
