/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Business.Controller.User.OrderController;

import Data.Model.Account;
import Data.Model.Order;
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
@WebServlet(name = "Update", urlPatterns = {"/order/update"})
public class Update extends HttpServlet {

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

        OrderRepository orderRepo = new OrderRepository();
        ProductRepository productRepo = new ProductRepository();

        Order order = orderRepo.getOrderByAccount(account);
        String[] productId = request.getParameterValues("productId");
        String[] amount = request.getParameterValues("amount");
        for (int i = 0; i < amount.length; i++) {
            Product product = productRepo.getProductById(Integer.parseInt(productId[i]));
            int filterAmount = Math.min(Math.max(Integer.parseInt(amount[i]), 1), product.getInventory());
            orderRepo.updateOrderDetail(order, product, filterAmount);
        }
        orderRepo.updateOrder(order, orderRepo.getAllOrderDetailByOrder(order));
        response.sendRedirect("/order");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
