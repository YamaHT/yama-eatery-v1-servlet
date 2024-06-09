<%-- 
    Document   : productManagement
    Created on : May 24, 2024, 4:02:52 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/css/admin/productManagement.css">

<div class="product-management">
    <div class="product-management-button-add">
        <button>ADD PRODUCT</button>
    </div>

    <div class="product-management-search">
        <input type="search" placeholder="Search...">
        <button><i class="fa-solid fa-magnifying-glass"></i></button>
    </div>

    <table class="table-of-product">
        <tr class="table-of-product-header">
            <th>ID</th>
            <th>Product</th>
            <th></th>
            <th>Category</th>
            <th>Inventory</th>
            <th></th>
        </tr>
        <tr class="table-of-product-body">
            <td class="table-of-product-body-id">1</td>
            <td><img src="../image/category_snack.jpg" alt=""></td>
            <td class="table-of-product-body-name-price">
                <h5>Khoai tay nghienaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa</h5>
                <p>Price: $100</p>
            </td>
            <td class="table-of-product-body-category">Snack</td>
            <td class="table-of-product-body-inventory">
                <p>10</p>
            </td>
            <td>
                <div class="table-of-product-body-button">
                    <button class="table-of-product-body-button-update">Update</button>
                    <button class="table-of-product-body-button-delete">Delete</button>
                </div>
            </td>
        </tr>
    </table>
</div>
