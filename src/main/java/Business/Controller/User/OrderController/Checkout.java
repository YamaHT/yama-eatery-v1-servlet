/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Business.Controller.User.OrderController;

import Data.Model.Account;
import Data.Model.Delivery;
import Data.Model.Order;
import Data.Model.OrderDetail;
import Data.Model.Shipping;
import Data.Repository.User.OrderRepository;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "Checkout", urlPatterns = {"/order/checkout"})
public class Checkout extends HttpServlet {

    OrderRepository orderRepository = new OrderRepository();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        Account account = (Account) request.getSession().getAttribute("account");

        Order order = orderRepository.getOrderByAccount(account);
        List<OrderDetail> listOrderDetail = orderRepository.getAllOrderDetailByOrder(order);
        List<Delivery> listDelivery = orderRepository.getAllDelivery();
        request.setAttribute("listOrderDetail", listOrderDetail);
        request.setAttribute("order", order);
        request.setAttribute("listDelivery", listDelivery);
        request.getRequestDispatcher("/html/user/transaction.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Account account = (Account) request.getSession().getAttribute("account");
        if (account == null) {
            response.sendRedirect("/auth/login");
            return;
        }

        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        int deliveryId = Integer.parseInt(request.getParameter("delivery"));
        Order order = orderRepository.getOrderByAccount(account);
        List<OrderDetail> list = orderRepository.getAllOrderDetailByOrder(order);

        // Add new Shipping
        int shippingId = orderRepository.addShipping(name, phone, address, deliveryId);
        Shipping shipping = orderRepository.getShippingById(shippingId);

        // Checkout
        orderRepository.checkout(order, shipping);

        // Update Product Inventory and OrderDetail that have amount higher product inventory
        orderRepository.updateAllProductInventoryAfterCheckout(list);
        list = orderRepository.getAllOrderDetailByOrder(order);
        orderRepository.updateAllOrderAfterCheckout(list);
        String paymentPrice = "" + (order.getTotal() + shipping.getDelivery().getPrice()) * 24000;
        String description = "a" + account.getId() + "o" + order.getId();
        request.getRequestDispatcher("/payment?paymentPrice=" + paymentPrice + "&description=" + description).forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
