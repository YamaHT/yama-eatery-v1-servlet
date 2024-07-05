<%-- 
    Document   : productManagement
    Created on : May 24, 2024, 4:02:52 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/css/admin/productManagement.css">
<link rel="stylesheet" href="/css/layout/paging.css">
<%
    java.util.List<Data.Model.Category> listCategory = new Data.Repository.User.ProductRepository().getAllCategory();
    request.setAttribute("listCategory", listCategory);
%>
<div class="product-management">
    <div class="product-management-button-add">
        <button type="button" onclick="changeManagement('product/add', 'ADD PRODUCT')">ADD PRODUCT</button>
    </div>

    <div class="product-management-header">
        <div class="product-management-header-search">
            <input type="search" id="searchName" value="${searchName}" placeholder="Search...">
            <button type="button" onclick="filterProduct(1)"><i class="fa-solid fa-magnifying-glass"></i></button>
        </div>
        <select class='product-management-header-select'>
            <option value=''>-- Category --</option>
            <c:forEach items='${listCategory}' var='category'>
                <option value='${category.name}'>${category.name}</option>
            </c:forEach>
        </select>
    </div>


    <table class="table-of-product">
        <thead>
            <tr class="table-of-product-header">
                <th>ID</th>
                <th>Product</th>
                <th></th>
                <th>Category</th>
                <th>Inventory</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${listProduct}" var="product" >
                <tr class="table-of-product-body">
                    <td class="table-of-product-body-id">${product.id}</td>
                    <td><img src="data:image/jpeg;base64, ${product.imgBase64}"></td>
                    <td class="table-of-product-body-name-price">
                        <h5>${product.name}</h5>
                        <p>Price: $${product.price}</p>
                    </td>
                    <td class="table-of-product-body-category">${product.category.name}</td>
                    <td class="table-of-product-body-inventory">
                        <p>${product.inventory}</p>
                    </td>
                    <td>
                        <div class="table-of-product-body-button">
                            <button type="button" onclick="changeManagement('product/update?id=${product.id}', 'Update product')"
                                    class="table-of-product-body-button-update">Update</button>
                            <button type="button" 
                                    onclick="changeManagement('product/delete?id=${product.id}', 'product')" 
                                    class="table-of-product-body-button-delete">Delete</button>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="paging">
        <button type="button" class="paging-first" onclick="changeManagement('product?page=1', 'Product')" ${page <= 1 ? 'disabled' : ''}>
            <i class="fa-solid fa-angles-left"></i>
        </button>
        <button type="button" class="paging-before" onclick="changeManagement('product?page=${page-1}', 'Product')"  ${page <= 1 ? 'disabled' : ''}>
            <i class="fa-solid fa-angle-left"></i>
        </button>
        <div class="paging-groupOfPage">
            <c:forEach begin="1" end="${endPage}" var="i">
                <button type="button" class="paging-groupOfPage-page ${page == i ? 'active' : ''}" 
                        onclick="changeManagement('product?page=${i}', 'Product')" 
                        ${page == i ? 'disabled' : ''}>
                    ${i}
                </button>
            </c:forEach>
        </div>
        <button type="button" class="paging-after" onclick="changeManagement('product?page=${page+1}', 'Product')" ${page >= endPage ? 'disabled' : ''}>
            <i class="fa-solid fa-angle-right"></i>
        </button>
        <button type="button" class="paging-last" onclick="changeManagement('product?page=${endPage}', 'Product')" ${page >= endPage ? 'disabled' : ''}>
            <i class="fa-solid fa-angles-right"></i>
        </button>
    </div>
</div>
<script>
    var search = document.querySelector("#searchName");
    search.addEventListener("input", function () {
        filterProduct(1);
    });

    var selectInput = document.querySelector(".product-management-header-select");
    selectInput.addEventListener('change', function () {
        filterProduct(1);
    });

    async function filterProduct(page) {
        var searchName = document.querySelector('#searchName').value;
        var categoryName = selectInput.value;
        var fetchPage = await fetch("/admin/management/product/filter?searchName=" + searchName + "&categoryName=" + categoryName + "&page=" + page);
        var response = await fetchPage.text();
        document.querySelector('.table-of-product tbody').innerHTML = response.split('#####')[0];
        document.querySelector('.paging').innerHTML = response.split('#####')[1];
    }
</script>