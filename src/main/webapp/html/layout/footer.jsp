<%-- 
    Document   : footer
    Created on : May 24, 2024, 4:01:40 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    java.util.List<Data.Model.Chat> listChat = new Data.Repository.User.ChatRepository().getListChat(0);
    request.setAttribute("listChat", listChat);
%>
<div class="chatbox-open-icon" onclick="showChatbox()">
    <i class="fa-regular fa-comment-dots"></i>
</div>
<div class="chatbox-container">
    <div class="chatbox-header">
        <div class="chatbox-header-content">
            <p>Chatbox</p>
            <i class="fa-solid fa-circle"></i>
        </div>
        <div class="chatbox-header-close">
            <i class="fa-solid fa-xmark" onclick="hideChatbox()"></i>
        </div>
    </div>
    <div class="chatbox-body">
        <c:forEach items='${listChat}' var='chat'>
            <div class="chatbox-body-chat ${chat.account.id == sessionScope.account.id ? 'main-user' : ''}">
                <div class="chatbox-body-chat-avatar">
                    <c:if test="${chat.account.profile.image == null}">
                        <img src="/image/logo.jpg">
                    </c:if>
                    <c:if test="${chat.account.profile.image != null}">
                        <img src="data:image/jpeg;base64, ${chat.account.profile.imgBase64}">
                    </c:if>
                </div>
                <div class="chatbox-body-chat-message">
                    <p class="chatbox-body-chat-message-username">${chat.account.profile.name}</p>
                    <p class="chatbox-body-chat-message-content">${chat.message}</p>
                </div>
            </div>
        </c:forEach>
    </div>
    <form class="chatbox-messageInput">
        <input id="messageChat" type="text" placeholder="Comment...">
        <emoji-picker class="hidden"></emoji-picker>
        <i class="fa-solid fa-face-smile" onclick="showEmoji()"></i>
        <i class="fa-solid fa-comment" onclick="sendMessage()"></i>
    </form>
</div>

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
<!-- General JS (Show-hide chatbox, Show emoji) -->
<script src="/js/footer.js"></script>

<!-- Script for websocket chatbox -->
<script>
            var websocket = new WebSocket("ws://localhost:8080/home/chat");
            websocket.onopen = function (message) {
                return true;
            };
            websocket.onmessage = function (message) {
                const data = JSON.parse(message.data);
                var userEmail = `${sessionScope.account.email}`;

                document.querySelector('.chatbox-body').innerHTML +=
                        "<div class='chatbox-body-chat " + (userEmail === data.sender ? "main-user" : "") + "'>" +
                        "<div class='chatbox-body-chat-avatar'>" +
                        (data.account.imgBase64 === 'null'
                                ? "<img src='/image/logo.jpg'>"
                                : "<img src='data:image/jpeg;base64, " + data.account.imgBase64 + "'>") +
                        "</div>" +
                        "<div class='chatbox-body-chat-message'>" +
                        "<p class='chatbox-body-chat-message-username'>" + data.account.name + "</p>" +
                        "<p class='chatbox-body-chat-message-content'>" + data.message + "</p>" +
                        "</div>" +
                        "</div>";
                if (userEmail === data.sender) {
                    chatboxBody.scrollTop = chatboxBody.scrollHeight;
                }
            };

            function sendMessage() {
                if (`${sessionScope.account}` === ``) {
                    showError("You must login to chat");
                    return;
                }

                var message = document.querySelector('#messageChat');
                var messageData = {
                    sender: `${sessionScope.account.email}`,
                    message: message.value
                };
                websocket.send(JSON.stringify(messageData));
                message.value = '';
            }


            document.querySelector('.chatbox-messageInput').addEventListener('submit', (e) => {
                e.preventDefault();
                sendMessage();
            });
</script>

<!-- Script for handle error notification -->
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