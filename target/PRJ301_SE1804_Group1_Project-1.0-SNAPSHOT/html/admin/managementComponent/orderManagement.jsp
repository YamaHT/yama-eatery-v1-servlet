<%-- 
    Document   : orderManagement
    Created on : May 24, 2024, 4:02:34 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/css/admin/orderManagement.css">
<div class="order-management">
    <div class="order-management-button">
        <button class="order-management-button-accept-all" onclick="showAcceptAll()">Accept all</button>
        <button class="order-management-button-reject-all" onclick="showRejectAll()">Reject all</button>
    </div>
    <table class="order-management-table">
        <tr class="order-management-table-header">
            <th>ID</th>
            <th>Email</th>
            <th>Quantity</th>
            <th>Total</th>
            <th>OrderDate</th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach items="${listOrder}" var="order">
            <tr class="order-management-table-order">
                <td>${order.id}</td>
                <td>${order.account.email}</td>
                <td>${order.quantity}</td>
                <td>$${order.total}</td>
                <td id="datetime">${order.orderDate}</td>
                <td class="order-management-table-order-dropdown" onclick="displayDropdown(this)" on>
                    <div>
                        ▼
                    </div>
                    <div class="order-management-table-order-dropdown-info">
                        <p>Fullname</p>
                        <input type="text" value="${order.shipping.recipientName}" readonly>
                        <p>Phone</p>
                        <input type="text" value="${order.shipping.phone}" readonly>
                        <p>Address</p>
                        <textarea readonly>${order.shipping.address}</textarea>
                    </div>
                </td>
                <td>
                    <div class="order-management-table-order-button">
                        <button onclick="changeManagement('order/process?action=accept&id=${order.id}', 'order')" class="order-management-table-order-button-accept">Accept</button>
                        <button onclick="changeManagement('order/process?action=refuse&id=${order.id}', 'order')" class="order-management-table-order-button-reject">Reject</button>
                    </div>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<div class="blur" onclick="cancelAction()"></div>
<div class="order-management-confirm-container dialog-acceptAll">
    <p>ARE YOU SURE TO ACCEPT ALL THESE ORDER ?</p>
    <div class="order-management-confirm-button">
        <button type="button" class="order-management-confirm-button-acceptAll-yes" onclick="changeManagement('order/process?action=acceptAll', 'order')">Yes</button>
        <button type="button" class="order-management-confirm-button-cancel" onclick="cancelAction()">Cancel</button>
    </div>

</div>
<div class="order-management-confirm-container dialog-rejectAll">
    <p>ARE YOU SURE TO REJECT ALL THESE ORDER ?</p>
    <div class="order-management-confirm-button">
        <button type="button" class="order-management-confirm-button-rejectAll-yes" onclick="changeManagement('order/process?action=refuseAll', 'order')">Yes</button>
        <button type="button" class="order-management-confirm-button-cancel" onclick="cancelAction()">Cancel</button>
    </div>
</div>

<script>
    const datetime = document.querySelectorAll('#datetime');
    datetime.forEach((d) => {
        d.innerHTML = d.innerHTML.split(' ')[0] + '<br/>' + d.innerHTML.split(' ')[1];
    });

    document.addEventListener('click', function (e) {
        const listDropdown = document.querySelectorAll('.order-management-table-order-dropdown');
        listDropdown.forEach((d) => {
            if (!d.contains(e.target)) {
                d.classList.remove('active');
            }
        });
    });

    function displayDropdown(dropdown) {
        if (dropdown.classList.contains('active')) {
            dropdown.classList.remove('active');
        } else {
            const listDropdown = document.querySelectorAll('.order-management-table-order-dropdown');
            listDropdown.forEach((d) => d.classList.remove('active'));
            dropdown.classList.add('active');
        }
    }

    function showAcceptAll() {
        document.querySelector('.order-management-confirm-container.dialog-acceptAll').classList.add('open-dialog');
        document.querySelector('.blur').style.display = 'block';
    }

    function showRejectAll() {
        document.querySelector('.order-management-confirm-container.dialog-rejectAll').classList.add('open-dialog');
        document.querySelector('.blur').style.display = 'block';
    }

    function cancelAction() {
        document.querySelector('.order-management-confirm-container.dialog-acceptAll').classList.remove('open-dialog');
        document.querySelector('.order-management-confirm-container.dialog-rejectAll').classList.remove('open-dialog');
        document.querySelector('.blur').style.display = 'none';
    }
</script>