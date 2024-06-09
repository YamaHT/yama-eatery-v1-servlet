<%-- 
    Document   : orderManagement
    Created on : May 24, 2024, 4:02:34 PM
    Author     : ADMIN
--%>

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
            <th>Username</th>
            <th>Quantity</th>
            <th>Total</th>
            <th>OrderDate</th>
            <th></th>
            <th></th>
        </tr>
        <tr class="order-management-table-order">
            <td>1</td>
            <td>yasuo123</td>
            <td>2</td>
            <td>200</td>
            <td id="datetime">2024/01/02 00:00:00</td>
            <td class="order-management-table-order-dropdown" onclick="displayDropdown(this)" on>
                <div>
                    ▼
                </div>
                <div class="order-management-table-order-dropdown-info">
                    <p>Fullname</p>
                    <input type="text" value="das" readonly>
                    <p>Phone</p>
                    <input type="text" value="das" readonly>
                    <p>Address</p>
                    <textarea type="text" readonly>09 Nguyen van cu, ninh kieu, can tho09 Nguyen van cu, ninh kieu, can tho09 Nguyen van cu, ninh kieu, can tho09 Nguyen van cu, ninh kieu, can tho09 Nguyen van cu, ninh kieu, can tho09 Nguyen van cu, ninh kieu, can tho09 Nguyen van cu, ninh kieu, can tho09 Nguyen van cu, ninh kieu, can tho09 Nguyen van cu, ninh kieu, can tho
                    </textarea>
                </div>
            </td>
            <td>
                <div class="order-management-table-order-button">
                    <button class="order-management-table-order-button-accept">Accept</button>
                    <button class="order-management-table-order-button-reject">Reject</button>
                </div>
            </td>
        </tr>
    </table>
</div>

<div class="blur" onclick="cancelAction()"></div>
<div class="order-management-confirm-acceptAll">
    <p>ARE YOU SURE TO ACCEPT ALL THESE ORDER ?</p>
    <div class="order-management-confirm-button">
        <button class="order-management-confirm-button-acceptAll-yes" onclick="acceptAllOrders()">Yes</button>
        <button class="order-management-confirm-button-cancel" onclick="cancelAction()">Cancel</button>
    </div>

</div>
<div class="order-management-confirm-rejectAll">
    <p>ARE YOU SURE TO REJECT ALL THESE ORDER ?</p>
    <div class="order-management-confirm-button">
        <button class="order-management-confirm-button-rejectAll-yes" onclick="rejectAllOrders()">Yes</button>
        <button class="order-management-confirm-button-cancel" onclick="cancelAction()">Cancel</button>
    </div>
</div>

<script>
    const date = document.querySelectorAll('#datetime');
    date.forEach((d) => {
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
        document.querySelector('.order-management-confirm-acceptAll').style.display = 'block';
        document.querySelector('.blur').style.display = 'block';
    }

    function showRejectAll() {
        document.querySelector('.order-management-confirm-rejectAll').style.display = 'block';
        document.querySelector('.blur').style.display = 'block';
    }

    function cancelAction() {
        document.querySelector('.order-management-confirm-acceptAll').style.display = 'none';
        document.querySelector('.order-management-confirm-rejectAll').style.display = 'none';
        document.querySelector('.blur').style.display = 'none';
    }
</script>