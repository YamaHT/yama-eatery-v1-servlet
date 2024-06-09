<%-- 
    Document   : updateProduct
    Created on : May 24, 2024, 4:02:03 PM
    Author     : ADMIN
--%>

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
    <form class="update-product-form">
        <h1 class="update-product-form-title">UPDATE PRODUCT</h1>
        <div class="update-product-form-input">
            <label for="id">Id</label>
            <input type="text" id="id" style="background: #ccc;" readonly>
        </div>
        <div class="update-product-form-input">
            <label for="name">Name</label>
            <input type="text" id="name" placeholder="Enter product name...">
        </div>
        <div class="update-product-form-input update-product-form-input-image">
            <label for="image">Image</label>
            <input type="file" id="file-upload" name="file-upload" accept="image/*" style="cursor: pointer;">
            <div>
                <img id="image-upload" src="" alt="img-product">
            </div>
        </div>
        <div class="update-product-form-input">
            <label for="price">Price</label>
            <input type="number" name="price" min="0" placeholder="Enter product price...">
        </div>
        <div class="update-product-form-input">
            <label for="description">Description</label>
            <textarea name="description" id="description" cols="40" rows="5"
                placeholder="Enter product description..."></textarea>
        </div>
        <div class="update-product-form-input">
            <label for="inventory">Inventory</label>
            <input type="number" name="inventory" min="0" step="1" placeholder="Enter product inventory...">
        </div>
        <div class="update-product-form-input">
            <label for="category">Category</label>
            <select name="category" id="category" style="cursor: pointer;">
                <option value="">-- Product category --</option>
            </select>
        </div>
        </div>
        <div class="update-product-form-button">
            <button type="reset" class="update-product-form-button-cancel">CANCEL</button>
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
</script>

</html>
