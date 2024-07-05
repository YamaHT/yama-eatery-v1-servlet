/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Business.Controller.Admin.OverviewManagementController;

import Data.Model.Product;
import Data.Repository.Admin.OverviewRepository;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 *
 * @author ADMIN
 */
@WebServlet(urlPatterns = {"/admin/management/overview"})
public class Index extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        OverviewRepository overviewRepository = new OverviewRepository();

        int productYear = request.getParameter("productYear") != null ? Integer.parseInt(request.getParameter("productYear")) : overviewRepository.thisMonthYear;
        int revenueYear = request.getParameter("revenueYear") != null ? Integer.parseInt(request.getParameter("revenueYear")) : overviewRepository.thisMonthYear;

        Map<Product, Integer> map = overviewRepository.getMostSoldProduct();
        Map.Entry<Product, Integer> entry = map.entrySet().iterator().next();
        Product mostSoldProduct = entry.getKey();
        Integer quantitySold = entry.getValue();

        request.setAttribute("productYear", productYear);
        request.setAttribute("revenueYear", revenueYear);
        request.setAttribute("thisYear", overviewRepository.thisMonthYear);
        request.setAttribute("lastYear", overviewRepository.thisMonthYear - 1);

        request.setAttribute("countUser", overviewRepository.getCountAccount());
        request.setAttribute("countUserLastMonth", overviewRepository.getCountAccountLastMonth());

        request.setAttribute("productSold", overviewRepository.getProductSold());
        request.setAttribute("productSoldLastMonth", overviewRepository.getProductSoldLastMonth());
        request.setAttribute("productSoldThisMonth", overviewRepository.getProductSoldThisMonth());

        request.setAttribute("revenue", overviewRepository.getRevenue());
        request.setAttribute("revenueLastMonth", overviewRepository.getRevenueLastMonth());
        request.setAttribute("revenueThisMonth", overviewRepository.getRevenueThisMonth());

        request.setAttribute("chartProduct", overviewRepository.getChartProduct(productYear).toString());
        request.setAttribute("chartRevenue", overviewRepository.getChartRevenue(revenueYear).toString());

        request.setAttribute("mostSoldProduct", mostSoldProduct);
        request.setAttribute("quantitySold", quantitySold);

        request.setAttribute("date", overviewRepository.thisMonth + "/" + overviewRepository.thisMonthYear);

        request.getRequestDispatcher("/html/admin/managementComponent/overviewManagement.jsp").forward(request, response);
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
