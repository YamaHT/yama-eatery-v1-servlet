<%-- 
    Document   : updateProduct
    Created on : May 24, 2024, 4:02:03 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Update product</title>
        <link rel="stylesheet" href="/css/admin/updateProduct.css">
    </head>

    <body>
        <form action="/admin/management/update" method="post" enctype="multipart/form-data" class="update-product-form">
            <h1 class="update-product-form-title">UPDATE PRODUCT</h1>
            <div class="update-product-form-input">
                <label for="id">Id</label>
                <input type="text" id="id" name="id" value="${product.id}" style="background: #ccc;" readonly required>
            </div>
            <div class="update-product-form-input">
                <label for="name">Name</label>
                <input type="text" id="name" name="name" value="${product.name}" placeholder="Enter product name..." required>
            </div>
            <div class="update-product-form-input update-product-form-input-image">
                <label for="image">Image</label>
                <input type="file" id="file-upload" name="image" accept="image/*" style="cursor: pointer;">
                <div>
                    <img id="image-upload" src="data:image/jpeg;base64, ${product.imgBase64}">
                </div>
            </div>
            <div class="update-product-form-input">
                <label for="price">Price</label>
                <input type="number" step="any" name="price" min="0" value="${product.price}" placeholder="Enter product price..." required>
            </div>
            <div class="update-product-form-input">
                <label for="description">Description</label>
                <textarea name="description" id="description" cols="40" rows="5"
                          placeholder="Enter product description..." required>${product.description}</textarea>
            </div>
            <div class="update-product-form-input">
                <label for="inventory">Inventory</label>
                <input type="number" name="inventory" min="0" step="1" value="${product.inventory}" placeholder="Enter product inventory..." required>
            </div>
            <div class="update-product-form-input">
                <label for="category">Category</label>
                <select name="category" id="category" style="cursor: pointer;" required>
                    <c:forEach items="${listCategory}" var='category'>
                        <option ${category.id == product.category.id ? 'selected' : ''} value="${category.id}">${category.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="update-product-form-button">
            <button type="reset" class="update-product-form-button-cancel" onclick="changeManagement('product', 'product')">CANCEL</button>
            <button type="submit" class="update-product-form-button-update">UPDATE</button>
        </div>
    </form>
</body>
<script>
    document.getElementById('file-upload').addEventListener('change', function (event) {
        const file = event.target.files[0];
        if (file && file.type.startsWith('image/')) {
            const reader = new FileReader();
            reader.onload = function (e) {
                document.getElementById('image-upload').src = e.target.result;
            };
            reader.readAsDataURL(file);
        } else {
            alert('Choose file image please.');
            event.target.value = '';
        }
    });


    $('.update-product-form').submit(function (e) {
        e.preventDefault();

        var formData = new FormData(this);

        $.ajax({
            url: '/admin/management/product/update',
            type: 'POST',
            data: formData,
            processData: false,
            contentType: false,
            success: function (response) {
                changeManagement('product', 'product');
            },
            error: function (xhr, status, error) {
                changeManagement('product', 'product');
            }
        });
    });
</script>

</html>
