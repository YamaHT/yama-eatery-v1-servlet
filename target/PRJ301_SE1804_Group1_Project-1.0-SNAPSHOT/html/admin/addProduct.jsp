<%-- 
    Document   : addProduct
    Created on : May 24, 2024, 4:01:57 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add product</title>
    <link rel="stylesheet" href="css/admin/addProduct.css">
</head>

<body>
    <form class="add-product-form">
        <h1 class="add-product-form-title">ADD PRODUCT</h1>
        <div class="add-product-form-input">
            <label for="name">Name</label>
            <input type="text" id="name" placeholder="Enter product name..." required>
        </div>
        <div class="add-product-form-input add-product-form-input-image">
            <label for="image">Image</label>
            <input type="file" id="file-upload" name="file-upload" accept="image/*" style="cursor: pointer;" required>
            <div>
                <img id="image-upload" src="">
            </div>
        </div>
        <div class="add-product-form-input">
            <label for="price">Price</label>
            <input type="number" name="price" min="0" placeholder="Enter product price..." required>
        </div>
        <div class="add-product-form-input">
            <label for="description">Description</label>
            <textarea name="description" id="description" cols="40" rows="5"
                placeholder="Enter product description..."></textarea>
        </div>
        <div class="add-product-form-input">
            <label for="inventory">Inventory</label>
            <input type="number" name="inventory" min="0" step="1" placeholder="Enter product inventory..." required>
        </div>
        <div class="add-product-form-input">
            <label for="category">Category</label>
            <select name="category" id="category" style="cursor: pointer;" required>
                <option value="">-- Product category --</option>
                <option value="1">Category 1</option>
                <option value="2">Category 2</option>
                <option value="3">Category 3</option>
                <option value="4">Category 4</option>
            </select>
        </div>
        </div>
        <div class="add-product-form-button">
            <button type="reset" class="add-product-form-button-cancel">CANCEL</button>
            <button type="submit" class="add-product-form-button-add">ADD</button>
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