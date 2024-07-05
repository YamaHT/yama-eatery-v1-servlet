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
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "Filter", urlPatterns = {"/admin/management/product/filter"})
public class Filter extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String searchName = request.getParameter("searchName");
        String categoryName = request.getParameter("categoryName");
        int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));

        ProductRepository productRepository = new ProductRepository();
        int count;
        List<Product> list;

        if (!searchName.isEmpty() && !categoryName.isEmpty()) {
            count = productRepository.getCountProductBySearchNameAndCaegoryName(searchName, categoryName);
            list = productRepository.getAllProductSearchBySearchNameAndCategoryName(searchName, categoryName, page);
        } else if (!searchName.isEmpty()) {
            count = productRepository.getCountProduct("search", searchName);
            list = productRepository.getAllProductSearchByName(searchName, page);
        } else if (!categoryName.isEmpty()) {
            count = productRepository.getCountProduct("category", categoryName);
            list = productRepository.getAllProductSearchByCategoryName(categoryName, page);
        } else {
            count = productRepository.getCountProduct(null, null);
            list = productRepository.getAllProduct(page);
        }

        int endPage = (int) Math.ceil(count / 6.0);

        String stringForPaging = "";
        for (int i = 1; i <= endPage; i++) {
            stringForPaging += "<button type=\"button\" class=\"paging-groupOfPage-page " + (page == i ? "active" : "") + "\" "
                    + "onclick=\"filterProduct(" + i + ")\" " + (page == i ? "disabled" : "") + ">"
                    + i
                    + "</button>\n";
        }

        String stringPrint = "";
        for (Product product : list) {
            stringPrint += " <tr class=\"table-of-product-body\">\n"
                    + "                    <td class=\"table-of-product-body-id\">" + product.getId() + "</td>\n"
                    + "                    <td><img src=\"data:image/jpeg;base64, " + product.getImgBase64() + "\"></td>\n"
                    + "                    <td class=\"table-of-product-body-name-price\">\n"
                    + "                        <h5>" + product.getName() + "</h5>\n"
                    + "                        <p>Price: $" + product.getPrice() + "</p>\n"
                    + "                    </td>\n"
                    + "                    <td class=\"table-of-product-body-category\">" + product.getCategory().getName() + "</td>\n"
                    + "                    <td class=\"table-of-product-body-inventory\">\n"
                    + "                        <p>" + product.getInventory() + "</p>\n"
                    + "                    </td>\n"
                    + "                    <td>\n"
                    + "                        <div class=\"table-of-product-body-button\">\n"
                    + "                            <button class=\"table-of-product-body-button-update\">Update</button>\n"
                    + "                            <button onclick=\"window.location.href = '/admin/management/product/delete?id=${product.id}'\" \n"
                    + "                                    class=\"table-of-product-body-button-delete\">Delete</button>\n"
                    + "                        </div>\n"
                    + "                    </td>\n"
                    + "                </tr>\n";
        }

        stringPrint += "#####\n"
                + "<button type=\"button\" class=\"paging-first\" onclick=\"filterProduct(1)\" "
                + (page <= 1 ? "disabled" : "") + ">\n"
                + "            <i class=\"fa-solid fa-angles-left\"></i>\n"
                + "        </button>\n"
                + "        <button type=\"button\" class=\"paging-before\" onclick=\"filterProduct(" + (page - 1) + ")\" "
                + (page <= 1 ? "disabled" : "") + ">\n"
                + "            <i class=\"fa-solid fa-angle-left\"></i>\n"
                + "        </button>\n"
                + "        <div class=\"paging-groupOfPage\">\n"
                + ""
                + stringForPaging
                + ""
                + "        </div>\n"
                + "        <button type=\"button\" class=\"paging-after\" onclick=\"filterProduct(" + (page + 1) + ")\" "
                + (page >= endPage ? "disabled" : "") + ">\n"
                + "            <i class=\"fa-solid fa-angle-right\"></i>\n"
                + "        </button>\n"
                + "        <button type=\"button\" class=\"paging-last\" onclick=\"filterProduct(" + endPage + ")\" "
                + (page >= endPage ? "disabled" : "") + ">\n"
                + "            <i class=\"fa-solid fa-angles-right\"></i>\n"
                + "        </button>";

        PrintWriter out = response.getWriter();
        out.print(stringPrint);
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
