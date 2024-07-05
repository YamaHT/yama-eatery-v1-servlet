/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Business.Controller.Admin.ProductManagementController;

import Data.Model.Category;
import Data.Repository.Admin.ProductRepository;
import Utils.ImageUtils;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author ADMIN
 */
@MultipartConfig
@WebServlet(urlPatterns = {"/admin/management/product/add"})
public class Add extends HttpServlet {

    ProductRepository productRepository = new ProductRepository();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Category> list = productRepository.getAllCategory();
        request.setAttribute("listCategory", list);
        request.getRequestDispatcher("/html/admin/addProduct.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            byte[] image = ImageUtils.compressImageFromWebsite(request.getPart("image").getInputStream());
            double price = Double.parseDouble(request.getParameter("price"));
            String description = request.getParameter("description");
            int inventory = Integer.parseInt(request.getParameter("inventory"));
            int categoryId = Integer.parseInt(request.getParameter("category"));
            productRepository.addProduct(name, image, price, description, inventory, categoryId);
        } catch (Exception e) {
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
