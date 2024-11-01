/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Business.Controller.Admin.ProductManagementController;

import Data.Model.Product;
import Data.Repository.Admin.ProductRepository;
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
@WebServlet(urlPatterns = {"/admin/management/product"})
public class Index extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProductRepository productRepository = new ProductRepository();
        int count = productRepository.getCountProduct(null, null);
        int endPage = (int) Math.ceil(count / 6.0);
        int page = (int) Math.min(
                Math.max(request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1, 1),
                Math.max(endPage, 1));
        List<Product> list = productRepository.getAllProduct(page);

        request.setAttribute("page", page);
        request.setAttribute("endPage", endPage);
        request.setAttribute("listProduct", list);
        request.getRequestDispatcher("/html/admin/managementComponent/productManagement.jsp").forward(request, response);
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
