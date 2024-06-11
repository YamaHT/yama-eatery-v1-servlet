/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Business.Controller.User.ProductController;

import Data.Model.Account;
import Data.Model.Product;
import Data.Repository.User.OrderRepository;
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
@WebServlet(name = "ProductController", urlPatterns = {"/product"})
public class Index extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            ProductRepository productRepo = new ProductRepository();
            String filter = request.getParameter("filter");
            String lastMinPrice = request.getParameter("priceMin");
            String lastMaxPrice = request.getParameter("priceMax");

            int count = productRepo.getCountProduct(null, filter, lastMinPrice, lastMaxPrice);
            int endPage = (int) Math.ceil(count / 12.0);
            int page = (int) Math.min(
                    Math.max(request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1, 1),
                    Math.max(endPage, 1));
            List<Product> list = productRepo.getAllProduct(page, filter, lastMinPrice, lastMaxPrice);

            request.setAttribute("listProduct", list);
            request.setAttribute("filter", (filter == null || filter.equals("")) ? "Id-DESC" : filter.replace(" ", "-"));
            request.setAttribute("minPrice", productRepo.getMinPriceProduct(null, null));
            request.setAttribute("maxPrice", productRepo.getMaxPriceProduct(null, null));
            request.setAttribute("lastMinPrice", lastMinPrice);
            request.setAttribute("lastMaxPrice", lastMaxPrice);
            request.setAttribute("page", page);
            request.setAttribute("endPage", endPage);
            request.getRequestDispatcher("/html/user/product.jsp").forward(request, response);
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
