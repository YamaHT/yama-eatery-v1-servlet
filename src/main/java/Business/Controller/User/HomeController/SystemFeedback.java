/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Business.Controller.User.HomeController;

import Data.Model.Account;
import Data.Model.Feedback;
import Data.Repository.User.FeedbackRepository;
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
@WebServlet(name = "Feedback", urlPatterns = {"/home/feedback"})
public class SystemFeedback extends HttpServlet {

    FeedbackRepository feedbackRepository = new FeedbackRepository();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int count = feedbackRepository.getCountFeedback();
            int endPage = (int) Math.ceil(count / 10.0);
            int page = (int) Math.min(
                    Math.max(request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1, 1),
                    Math.max(endPage, 1));

            List<Feedback> list = feedbackRepository.getAllFeedback(page);
            request.setAttribute("listFeedback", list);
            request.setAttribute("page", page);
            request.setAttribute("endPage", endPage);
            request.getRequestDispatcher("/html/user/feedback.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("/home");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("account");
        if (account == null) {
            response.sendRedirect("/auth/login");
            return;
        }
        String feedback = request.getParameter("feedback");
        feedbackRepository.sendFeedback(account, feedback);
        response.sendRedirect("/home/feedback");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
