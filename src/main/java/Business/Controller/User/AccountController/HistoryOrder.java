/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Business.Controller.User.AccountController;

import Data.Model.Account;
import Data.Model.Order;
import Data.Model.OrderDetail;
import Data.Repository.User.OrderRepository;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="HistoryOrder", urlPatterns={"/account/historyOrder"})
public class HistoryOrder extends HttpServlet {
   
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("account");
        
        OrderRepository orderRepo = new OrderRepository();
        List<List<OrderDetail>> listOrderDetailInOrder = new ArrayList<>();
        List<Order> listOrder = orderRepo.getAllOrderByAccount(account);
        for (Order order : listOrder) {
            List<OrderDetail> listOrderDetail = orderRepo.getAllOrderDetailByOrder(order);
            listOrderDetailInOrder.add(listOrderDetail);
        }
        request.setAttribute("listOrder", listOrder);
        request.setAttribute("listOrderDetailInOrder", listOrderDetailInOrder);
        request.getRequestDispatcher("/html/user/historyOrder.jsp").forward(request, response);
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
