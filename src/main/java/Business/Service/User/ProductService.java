/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Service.User;

import Data.Model.Product;
import Data.Repository.User.ProductRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ProductService {

    ProductRepository productRepo = new ProductRepository();

    public void index(HttpServletRequest request, HttpServletResponse response) {
        try {
            int count = productRepo.getCountProduct();
            int page = (int) Math.min(
                    Math.max(request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1, 1),
                    Math.max(Math.ceil(count / 6), 1));
            List<Product> list = productRepo.getAllProduct(page, null, null);
            request.setAttribute("listProduct", list);
            request.getRequestDispatcher("/html/user/product.jsp").forward(request, response);
        } catch (Exception e) {
            try {
                request.getRequestDispatcher("/html/error/404.jsp").forward(request, response);
            } catch (Exception ex) {
            }
        }
    }
}
