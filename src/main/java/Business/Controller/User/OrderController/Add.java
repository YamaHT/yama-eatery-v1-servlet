/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Business.Controller.User.OrderController;

import Data.Model.Account;
import Data.Model.Order;
import Data.Model.OrderDetail;
import Data.Model.Product;
import Data.Repository.User.OrderRepository;
import Data.Repository.User.ProductRepository;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
@WebServlet(urlPatterns = {"/order/add"})
public class Add extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("account");
        OrderRepository orderRepository = new OrderRepository();
        ProductRepository productRepo = new ProductRepository();

        int productId = Integer.parseInt(request.getParameter("productId"));

        Product product = productRepo.getProductById(productId);
        if (product == null) {
            response.sendRedirect("/product");
            return;
        }
        
        int amount = request.getParameter("amount") != null ? Integer.parseInt(request.getParameter("amount")) : 1;
        amount = Math.min(Math.max(amount, 1), product.getInventory());

        Order order = orderRepository.getOrderByAccount(account);
        if (order == null) {
            orderRepository.createOrder(account);
            order = orderRepository.getOrderByAccount(account);
        }

        OrderDetail orderDetail = orderRepository.getOrderDetailByOrderAndProduct(order, product);
        if (orderDetail == null) {
            orderRepository.addOrderDetail(order, product, amount);
        } else {
            amount = Math.min(product.getInventory(),
                    orderDetail.getAmount() + amount);
            orderRepository.updateOrderDetail(order, product, amount);
        }
        orderRepository.updateOrder(order, orderRepository.getAllOrderDetailByOrder(order));
        response.sendRedirect("/order");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
