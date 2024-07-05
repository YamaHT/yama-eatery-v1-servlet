/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Business.Controller.Admin.OrderManagementController;

import Data.Model.Order;
import Data.Repository.Admin.OrderRepository;
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
@WebServlet(name = "Process", urlPatterns = {"/admin/management/order/process"})
public class Process extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        OrderRepository orderRepository = new OrderRepository();
        
        switch (String.valueOf(action)) {
            case "accept":
                orderRepository.acceptOrder(Integer.parseInt(request.getParameter("id")));
                break;
            case "refuse":
                orderRepository.refuseOrder(new Order(Integer.parseInt(request.getParameter("id"))));
                break;
            case "acceptAll":
                List<Order> listToAccept = orderRepository.getWaitingOrder();
                for (Order order : listToAccept) {
                    orderRepository.acceptOrder(order.getId());
                }
                break;
            case "refuseAll":
                List<Order> listToRefuse = orderRepository.getWaitingOrder();
                for (Order order : listToRefuse) {
                    orderRepository.refuseOrder(order);
                }
                break;
        }
        
        request.getRequestDispatcher("/admin/management/order").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
