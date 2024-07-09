<%-- 
    Document   : resourceManagement
    Created on : May 24, 2024, 4:02:58 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/css/admin/resourcemanagement.css">

<div class="resource">
    <div class="resource-title">
        <button onclick="changeManagement('resource/add', 'ADD RESOURCE')" type="button" class="resource-title-button-add"> Add
            <i class="fa-solid fa-plus"></i>
        </button>
        <form action="search" class="resource-title-form">
            <input id="searchName" type="search" placeholder="Search name here...">
            <button type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>
        </form>
    </div>

    <table class="resource-table">
        <thead>
            <tr class="resource-table-head">
                <th>No.</th>
                <th>Name</th>
                <th>Image</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${listResource}" var="resource" varStatus="i">
                <tr class="resource-table-body">
                    <td>${i.index + 1}</td>
                    <td>${resource.name}</td>
                    <td class="resource-table-body-image"><img src="data:image/jpeg;base64, ${resource.imgBase64}"></td>
                    <td><i class="fa-solid fa-trash" data-bind="${resource.name}"></i></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<script>
    document.querySelector('form').addEventListener('submit', (e) => {
        e.preventDefault();
        searchResourceByName();
    });

    document.querySelector('#searchName').addEventListener('input', (e) => {
        e.preventDefault();
        searchResourceByName();
    });

    document.querySelectorAll('.fa-trash').forEach(deleteButton => {
        deleteButton.addEventListener('click', (e) => {
            e.preventDefault();
            var name = deleteButton.getAttribute('data-bind');
            deleteResource(name);
        });
    });

    async function searchResourceByName() {
        var name = document.querySelector('#searchName').value;
        var fetchPage = await fetch('/admin/management/resource/search?name=' + name);
        var response = await fetchPage.text();
        document.querySelector('.resource-table tbody').innerHTML = response;
    }

    async function deleteResource(name) {
        var fetchPage = await fetch('/admin/management/resource/delete?name=' + name);
        var response = await fetchPage.text();
        document.querySelector('.resource-table tbody').innerHTML = response;
    }
</script>