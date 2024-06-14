<%-- 
    Document   : contactUs
    Created on : May 24, 2024, 4:03:28 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Contact Us</title>
        <link rel="stylesheet" href="/css/user/contactUs.css">
    </head>

    <body>
    <c:if test="${sessionScope.account == null}">
        <jsp:include page="../layout/header.jsp" />
    </c:if>
    <c:if test="${sessionScope.account != null}">
        <jsp:include page="../layout/header_loggedIn.jsp" />
    </c:if>
    <div class="banner"><img src="/image/contact-banner.jpg" alt="banner"></div>
    <div class="contact-detail">
        <div class="contact-detail-content">
            <div class="contact-detail-content-title">
                <h3>// CONTACT DETAILS</h3>
                <h1>Contact us</h1>
                <p>Give us a call or drop by anytime, we endeavour to answer all enquiries within 24 hours on business
                    days. We will be happy to answer your questions.</p>
            </div>
            <div class="contact-detail-content-address" onclick="copyToClipboard(this)">
                <i class="fa-solid fa-globe"></i>
                <div>
                    Our address:
                    <p>600, Nguyen Van Cu Street, An Binh Ward, Ninh Kieu District, Can Tho City.</p>
                </div>

            </div>

            <div class="contact-detail-content-email">
                <i class="fa-solid fa-envelope"></i>
                <div>
                    Our Mailbox:
                    <p>DuyLPCE181153@fpt.edu.vn</p>
                </div>
            </div>

            <div class="contact-detail-content-phone">
                <i class="fa-solid fa-phone-volume"></i>
                <div>
                    Our phone:
                    <p>0123698745</p>
                </div>
            </div>
        </div>
        <form class="contact-detail-form" method="post">
            <h1>Really to Get Started?</h1>
            <p>
                Your email address will not be published. Required fields are marked *
            </p>
            <input type="text" name="name" placeholder="Your name *">
            <input type="text" name="email" placeholder="Your email *">
            <textarea name="message" id="message" placeholder="Message..." oninput="auto_grow(this)"></textarea>
            <button>SEND MESSAGE</button>
        </form>
    </div>
    <iframe class="map"
            src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3929.0532902996324!2d105.72985131020346!3d10.012457072779751!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31a0882139720a77%3A0x3916a227d0b95a64!2zVHLGsOG7nW5nIMSQ4bqhaSBo4buNYyBGUFQgQ-G6p24gVGjGoQ!5e0!3m2!1sen!2s!4v1716290887768!5m2!1sen!2s"></iframe>
        <jsp:include page="../layout/footer.jsp"/>
</body>

<script>
    function copyToClipboard(content) {
        var copyText = content.children[1];
        copyText.select();
        copyText.setSelectionRange(0, 99999);
        navigator.clipboard.writeText(copyText.value);
        alert("Copied the text: " + copyText.value);
    }
    function auto_grow(element) {
        element.style.height = "max-content";
        element.style.height = (element.scrollHeight) + "px";
    }
</script>

</html>