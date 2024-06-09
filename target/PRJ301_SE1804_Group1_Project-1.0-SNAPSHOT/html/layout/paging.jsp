<%-- 
    Document   : paging
    Created on : May 24, 2024, 4:01:45 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/css/layout/paging.css">
<div class="paging">
    <button type="button" class="paging-first" disabled><i class="fa-solid fa-angles-left"></i></button>
    <button type="button" class="paging-before" disabled><i class="fa-solid fa-angle-left"></i></button>
    <div class="paging-groupOfPage">
        <button type="button" class="paging-groupOfPage-page active" disabled>1</button>
        <button type="button" class="paging-groupOfPage-page">2</button>
        <button type="button" class="paging-groupOfPage-page">3</button>
        <button type="button" class="paging-groupOfPage-page">4</button>
        <button type="button" class="paging-groupOfPage-page">5</button>
    </div>
    <button type="button" class="paging-after"><i class="fa-solid fa-angle-right"></i></button>
    <button type="button" class="paging-last"><i class="fa-solid fa-angles-right"></i></button>
</div>