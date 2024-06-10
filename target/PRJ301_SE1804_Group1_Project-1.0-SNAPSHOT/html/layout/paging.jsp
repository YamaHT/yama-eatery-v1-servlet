<%-- 
    Document   : paging
    Created on : May 24, 2024, 4:01:45 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/css/layout/paging.css">
<div class="paging">
    <button type="button" class="paging-first" onclick="changingPage(1)" ${page == 1 ? 'disabled' : ''}>
        <i class="fa-solid fa-angles-left"></i>
    </button>
    <button type="button" class="paging-before" onclick="changingPage(${page-1})"  ${page == 1 ? 'disabled' : ''}>
        <i class="fa-solid fa-angle-left"></i>
    </button>
    <div class="paging-groupOfPage">
        <c:forEach begin="1" end="${endPage}" var="i">
            <button type="button" class="paging-groupOfPage-page ${page == i ? 'active' : ''}" 
                    onclick="changingPage(${i})" ${page == i? 'disabled' : ''}>
                ${i}
            </button>
        </c:forEach>
    </div>
    <button type="button" class="paging-after" onclick="changingPage(${page+1})" ${page == endPage ? 'disabled' : ''}>
        <i class="fa-solid fa-angle-right"></i>
    </button>
    <button type="button" class="paging-last" onclick="changingPage(${endPage})" ${page == endPage ? 'disabled' : ''}>
        <i class="fa-solid fa-angles-right"></i>
    </button>
</div>
<script>
    function changingPage(page) {
        let url = window.location.href;

        if (url.match(/(\?|&)page=\d+/)) {
            url = url.replace(/(\?|&)page=\d+/, '');
        }

        if (url.includes('?')) {
            window.location.href = url + "&page=" + page;
        } else {
            window.location.href = url + "?page=" + page;
        }
    }
</script>