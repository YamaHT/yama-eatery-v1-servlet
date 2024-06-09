<%-- 
    Document   : resourceManagement
    Created on : May 24, 2024, 4:02:58 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/css/admin/resourcemanagement.css">

<div class="resource">
    <div class="resource-title">
        <button type="button" class="resource-title-button-add"> Add
            <i class="fa-solid fa-plus"></i>
        </button>
        <form action="search" class="resource-title-form">
            <input type="search" placeholder="Search name here...">
            <button type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>
        </form>
    </div>

    <table class="resource-table">
        <thead>
            <tr class="resource-table-head">
                <th>No.</th>
                <th>Name</th>
                <th>Image</th>
                <th> </th>
            </tr>
        </thead>
        <tbody>
            <tr class="resource-table-body" id="1">
                <td>1</td>
                <td>banh my u</td>
                <td class="resource-table-body-image"><img src="/image/category_food.jpg" alt=""></td>
                <td><i class="fa-solid fa-trash"></i></td>
            </tr>
        </tbody>
    </table>
</div>