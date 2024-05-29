<%-- 
    Document   : feedbackManagement
    Created on : May 24, 2024, 4:02:25 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/feedbackManagement.css">
<div class="feedback-management">
    <div class="feedback">
        <div class="feedback-user-image"><img src="" alt="avatar"></div>
        <div class="feedback-user-content">
            <div class="feedback-user-content-header">
                <div class="feedback-user-content-header-info">
                    <div class="feedback-user-content-header-info-username">Dullahan</div>
                    <i class="fa-solid fa-circle"></i>
                    <span>00:00:00 18/05/2024</span>
                </div>
                <i class="fa-solid fa-x x-btn"></i>
            </div>

            <p class="feedback-user-content-comment">
                chu shop dep trai
            </p>
        </div>
        <div class="feedback-admin">
            <button class="feedback-admin-button" for="reply" onclick="toggleTextarea(1)">Reply</button>
            <form action="#" method="get" class="feedback-admin-input" id="response-1" style="display: none;">
                <textarea name="reply" id="reply" cols="30" rows="3">Very handsome is my name.</textarea>
                <i class="fa-solid fa-location-arrow fa-rotate-by" style="--fa-rotate-angle: 45deg;"
                   onclick="this.parentElement.submit();"></i>
            </form>
        </div>
    </div>
</div>
<script>
    function toggleTextarea(id) {
        var reply = document.querySelector('#response-' + id);
        if (reply.style.display === 'none') {
            reply.style.display = 'flex';
        } else {
            reply.style.display = 'none';
        }
    }
</script>