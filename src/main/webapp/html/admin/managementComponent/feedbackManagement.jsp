<%-- 
    Document   : feedbackManagement
    Created on : May 24, 2024, 4:02:25 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/css/admin/feedbackManagement.css">
<div class="feedback-management">
    <c:forEach items="${listFeedback}" var="feedback" varStatus="i">
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
                    <div class="feedback-user-content-header-info">
                        <div class="feedback-user-content-header-info-username">${feedback.account.username}</div>
                        <i class="fa-solid fa-circle"></i>
                        <span>${feedback.feedbackDate}</span>
                    </div>
                    <i class="fa-solid fa-x x-btn" onclick="changeManagement('feedback/ignore?id=${feedback.id}', 'feedback')"></i>
                </div>

                <p class="feedback-user-content-comment">
                    ${feedback.feedback}
                </p>
            </div>
            <div class="feedback-admin">
                <button class="feedback-admin-button" for="reply" onclick="toggleTextarea(`${i.index}`)">Reply</button>
                <form class="feedback-admin-input" id="response-${i.index}" style="display: none;">
                    <input type="hidden" name="id" value="${feedback.id}"/>
                    <textarea name="response" id="response" cols="30" rows="3" required></textarea>
                    <i class="fa-solid fa-location-arrow fa-rotate-by" style="--fa-rotate-angle: 45deg;"
                       onclick="response(`${i.index}`)"></i>
                </form>
            </div>
        </div>
    </c:forEach>
</div>
<script>
    function toggleTextarea(index) {
        var reply = document.querySelector('#response-' + index);
        if (reply.style.display === 'none') {
            reply.style.display = 'flex';
        } else {
            reply.style.display = 'none';
        }
    }

    function response(index) {
        let form = document.querySelector('#response-' + index);
        let formData = new FormData(form);
        var url = 'feedback/response?id=' + formData.get("id") + '&response=' + formData.get("response");
        console.log(url);
        changeManagement('feedback/response?id=' + formData.get("id") + '&response=' + formData.get("response"), 'feedback');
    }
</script>