<%-- 
    Document   : addResource
    Created on : Jul 8, 2024, 2:18:08 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Resource</title>
        <link rel="stylesheet" href="/css/admin/addProduct.css">
    </head>
    <body>
        <form action="/admin/management/resource/add" method="post" class="add-product-form" enctype="multipart/form-data">
            <h1 class="add-product-form-title">ADD RESOURCE</h1>
            <div class="add-product-form-input">
                <label for="name">Name</label>
                <input type="text" id="name" name="name" placeholder="Enter resource name..." required>
            </div>
            <div class="add-product-form-input add-product-form-input-image">
                <label for="image">Image</label>
                <input type="file" id="file-upload" name="image" accept="image/*" style="cursor: pointer;" required>
                <div>
                    <img style="display: none" id="image-upload" src="">
                </div>
            </div>
            <div class="add-product-form-button">
                <button type="reset" class="add-product-form-button-cancel" onclick="changeManagement('resource', 'resource')">CANCEL</button>
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
                    document.getElementById('image-upload').style.display = 'inline-block';
                    document.getElementById('image-upload').src = e.target.result;
                };
                reader.readAsDataURL(file);
            } else {
                alert('Choose file image please.');
                event.target.value = '';
            }
        });


        $('.add-product-form').submit(function (e) {
            e.preventDefault();

            var formData = new FormData(this);

            $.ajax({
                url: '/admin/management/resource/add',
                type: 'POST',
                data: formData,
                processData: false,
                contentType: false,
                success: function (response) {
                    changeManagement('resource', 'resource');
                }
            });
        });
    </script>
</html>
