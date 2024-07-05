<%-- 
    Document   : thankyou
    Created on : Jun 19, 2024, 3:02:56 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles.css">
        <title>Thank You Page</title>
        <style>
            .page {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                background-color: #f8f9fa;
            }

            .page-content {
                max-width: 400px;
                width: 100%;
            }

            .page-border {
                border: 3px solid #28a745;
            }

            .card {
                background-color: #fff;
                box-shadow: 0 0.125rem 0.25rem rgba(0, 0, 0, 0.075);
                padding: 20px;
                border-radius: 0.25rem;
            }

            .card-icon-container {
                display: flex;
                justify-content: center;
                margin-bottom: 20px;
            }

            .card-icon {
                color: #28a745;
            }

            .card-text-container {
                text-align: center;
                
                & img {
                    width: 100%;
                }
            }

            .card-title {
                margin: 0;
                font-size: 24px;
            }

            .card-text {
                margin: 10px 0;
            }

            .card-button {
                display: inline-block;
                padding: 10px 20px;
                margin-top: 20px;
                border: 2px solid #28a745;
                background-color: transparent;
                color: #28a745;
                border-radius: 0.25rem;
                cursor: pointer;
                text-decoration: none;
                transition: background-color 0.3s, color 0.3s;
            }

            .card-button:hover {
                background-color: #28a745;
                color: #fff;
            }

        </style>
    </head>
    <body>
        <jsp:include page="../layout/header_loggedIn.jsp"></jsp:include>
            <div class="page">
                <div class="page-content">
                    <div class="page-border"></div>
                    <div class="card">
                        <div class="card-icon-container">
                            <svg xmlns="http://www.w3.org/2000/svg" class="card-icon" width="75" height="75"
                                 fill="currentColor" viewBox="0 0 16 16">
                            <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />
                            <path d="M10.97 4.97a.235.235 0 0 0-.02.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-1.071-1.05z" />
                            </svg>
                        </div>
                        <div class="card-text-container">
                            <h1 class="card-title">Thank You For Purchasing!</h1>
                            <p class="card-text">Please pay your order to continue</p>
                            <img src="https://img.vietqr.io/image/TPB-00000052424-compact.png?amount=${paymentPrice}&addInfo=${description}"/>
                            <button onclick="location.href = '/home'" class="card-button">Back Home</button>
                        </div>
                    </div>
                </div>
            </div>
        <jsp:include page="../layout/footer.jsp"></jsp:include>
    </body>
</html>

