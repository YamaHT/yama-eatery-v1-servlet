/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Business.Controller.User.HomeController;

import Data.Model.Product;
import Data.Repository.User.ProductRepository;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "Index", urlPatterns = {"/home"})
public class Index extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");

        ProductRepository productRepository = new ProductRepository();

        switch (String.valueOf(action)) {
            case "null":
                List<Product> listMostSold = productRepository.getTop5MostSoldProduct();
                List<Product> listNewProduct = productRepository.getTop5NewProduct();

                request.setAttribute("listMostSold", listMostSold);
                request.setAttribute("listNewProduct", listNewProduct);

                request.getRequestDispatcher("/html/user/home.jsp").forward(request, response);
                break;
            case "mostSold":
                List<Product> loadListMostSold = productRepository.getTop10After5MostSoldProduct();
                PrintWriter out = response.getWriter();
                for (Product product : loadListMostSold) {
                    out.println("<div class=\"mostSoldProduct-groupOfProduct-detail\" onclick=\"window.location.href='/product/detail?id=" + product.getId() + "'\">\n"
                            + "     <img src=\"data:image/jpeg;base64, " + product.getImgBase64() + "\" >\n"
                            + "     <p>" + product.getName() + "</p>\n"
                            + "     <p class=\"mostSoldProduct-groupOfProduct-detail-price\">$" + product.getPrice() + "</p>\n"
                            + "</div>");
                }
                break;
            case "newProduct":
                List<Product> loadListNewProduct = productRepository.getTop10After5NewProduct();
                out = response.getWriter();
                for (Product product : loadListNewProduct) {
                    out.println("<div class=\"newProduct-groupOfProduct-detail\" onclick=\"window.location.href='/product/detail?id=" + product.getId() + "'\">\n"
                            + "     <img src=\"data:image/jpeg;base64, " + product.getImgBase64() + "\" >\n"
                            + "     <p>" + product.getName() + "</p>\n"
                            + "     <p class=\"newProduct-groupOfProduct-detail-price\">$" + product.getPrice() + "</p>\n"
                            + "</div>");
                }
                break;
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
