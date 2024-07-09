<%-- 
    Document   : feedback
    Created on : May 24, 2024, 4:03:32 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <c:if test="${sessionScope.account == null}">
            <jsp:include page="../layout/header.jsp" />
        </c:if>
        <c:if test="${sessionScope.account != null}">
            <jsp:include page="../layout/header_loggedIn.jsp" />
        </c:if>
        <div class="feedback-banner">
            <img src="/image/feedbackBanner.jpg" alt="feedbackBanner">
        </div>
        <c:if test="${sessionScope.account != null && !myFeedback}">
            <div class="feedback-comment">
                <div class="feedback-comment-image">
                    <c:if test="${sessionScope.account.profile.image == null}">
                        <img src="/image/logo.jpg" alt="avatar">
                    </c:if>
                    <c:if test="${sessionScope.account.profile.image != null}">
                        <img src="data:image/jpeg;base64,${sessionScope.account.profile.imgBase64}" alt="avatar">
                    </c:if>
                </div>
                <form action="/home/feedback" class="feedback-comment-form" method="post">
                    <textarea name="feedback" placeholder="Enter your feedback..." required oninput="auto_grow(this)"></textarea>
                    <div class="feedback-comment-form-button">
                        <button type="reset" class="feedback-comment-form-button-cancel"
                                oninput="auto_grow(this)">Cancel</button>
                        <button type="submit" class="feedback-comment-form-button-send">Send</button>
                    </div>
                </form>
            </div>
        </c:if>
        <c:forEach items="${listFeedback}" var="feedback">
            <div class="feedback">
                <div class="feedback-user-image">
                    <c:if test="${feedback.account.profile.image == null}">
                        <img src="/image/logo.jpg">
                    </c:if>
                    <c:if test="${feedback.account.profile.image != null}">
                        <img src="data:image/jpeg;base64,${feedback.account.profile.imgBase64}">
                    </c:if>
                </div>
                <div class="feedback-user-content">
                    <div class="feedback-user-content-header">
                        <p>${feedback.account.profile.name}</p>
                        <i class="fa-solid fa-circle"></i>
                        <span>${feedback.feedbackDate}</span>
                    </div>

                    <p class="feedback-user-content-comment">
                        ${feedback.feedback}
                    </p>
                </div>
                <c:if test="${feedback.response != null}">
                    <div class="feedback-admin">
                        <div class="feedback-admin-info">
                            <p>Yama's assistance</p>
                            <i class="fa-solid fa-circle"></i>
                            <span>${feedback.responseDate}</span>
                        </div>
                        <div class="feedback-admin-response">${feedback.response}</div>
                    </div>
                </c:if>
            </div>
        </c:forEach>
        <c:if test="${!myFeedback}">
            <jsp:include page="../layout/paging.jsp"/>
        </c:if>
        <jsp:include page="../layout/footer.jsp"/>
    </body>
    <script>
        function auto_grow(element) {
            element.style.height = "max-content";
            element.style.height = (element.scrollHeight) + "px";
        }
    </script>

</html>
