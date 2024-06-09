<%-- 
    Document   : productDetail
    Created on : May 24, 2024, 4:04:11 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Product Detail</title>
        <link rel="stylesheet" href="/css/user/productDetail.css">
    </head>
    <body>
        <jsp:include page="../layout/header.jsp"/>
        <div class="productDetail">
            <div class="productDetail-image"><img src="" alt=""></div>
            <div class="productDetail-content">
                <div class="productDetail-content-header">
                    <div>IN-STOCK</div>
                    <div>Category</div>
                </div>
                <div class="productDetail-content-name">PRODUCT NAME</div>
                <div class="productDetail-content-price">$60 <span>$120</span></div>
                <form class="productDetail-content-action">
                    <div class="productDetail-content-action-quantity">
                        <input type="number" name="quantity" value="0">
                    </div>
                    <div class="productDetail-content-action-addToCart">
                        <button>
                            ADD TO CART <i class="fa-solid fa-cart-shopping"></i>
                        </button>
                    </div>
                </form>
                <div class="productDetail-content-description">
                    Dưới ánh bình minh mơ màng, làn sương mỏng trải dài như tấm thảm mịn màng trên cánh
                    đồng. Tiếng chim ríu rít làm cho không gian thêm phần huyền bí, như một bài hát của tự nhiên dành riêng
                    cho buổi sớm mai. Cảm giác bình yên và hạnh phúc như muốn ghi sâu vào lòng người, như một lời nhắc nhở
                    về vẻ đẹp tinh thần của cuộc sống. Ánh nắng ấm áp từ những tia mặt trời mới bắt đầu vươn lên, nhấp nhô
                    qua từng hàng cỏ xanh mướt, như muốn đánh thức mọi sinh linh trong tự nhiên. Cảm giác ấm áp lan tỏa từ
                    trái tim, đầy ắp niềm hy vọng và khát khao về một ngày mới tràn đầy năng lượng và thành công.</div>
            </div>
        </div>
        <div class="title">SIMILIAR PRODUCT</div>
        <div class="groupOfProduct">
            <div class="groupOfProduct-product" id="p1">
                <div class="groupOfProduct-product-header">
                    <div class="groupOfProduct-product-category">Category</div>
                    <div class="groupOfProduct-product-price">$50</div>
                </div>
                <div class="groupOfProduct-product-img"> <img src="" alt="category_food">
                </div>
                <div class="groupOfProduct-product-name">Product name</div>
                <button class="groupOfProduct-product-button-viewdetail">
                    VIEW DETAIL
                </button>
            </div>
        </div>
        <jsp:include page="../layout/footer.jsp"/>
    </body>

</html>