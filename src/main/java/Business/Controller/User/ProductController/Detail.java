/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Business.Controller.User.ProductController;

import Data.Model.Product;
import Data.Repository.User.ProductRepository;
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
@WebServlet(name = "Detail", urlPatterns = {"/product/detail"})
public class Detail extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ProductRepository productRepository = new ProductRepository();

            int id = Integer.parseInt(request.getParameter("id"));
            Product product = productRepository.getProductById(id);
            if (product == null) {
                response.sendRedirect("/product");
                return;
            }

            request.setAttribute("product", product);

            List<Product> list = productRepository.getSimilarProduct(product);
            request.setAttribute("listProduct", list);

            request.getRequestDispatcher("/html/user/productDetail.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("/product");
        }
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
