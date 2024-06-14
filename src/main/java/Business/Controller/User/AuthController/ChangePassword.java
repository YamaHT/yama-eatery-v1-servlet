/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Business.Controller.User.AuthController;

import Data.Model.Account;
import Data.Repository.User.AccountRepository;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ChangePassword", urlPatterns = {"/auth/changePassword"})
public class ChangePassword extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("account");
        if (account == null) {
            response.sendRedirect("/auth/login");
            return;
        }
        request.getRequestDispatcher("/html/user/changePassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Account account = (Account) request.getSession().getAttribute("account");
            if (account == null) {
                response.sendRedirect("/auth/login");
                return;
            }

            String oldPassword = request.getParameter("oldPass");
            String newPassword = request.getParameter("newPass");
            String confirmPassword = request.getParameter("conPass");

            String error = null;
            if (!oldPassword.equals(account.getPassword())) {
                error = "Your old password is incorrect.";
            } else if (!confirmPassword.equals(newPassword)) {
                error = "Confirm password does not match new password.";
            } else if (newPassword.equals(oldPassword)) {
                error = "New password must be different from old password.";
            }

            if (error != null) {
                request.setAttribute("error", error);
                request.getRequestDispatcher("/html/user/changePassword.jsp").forward(request, response);
                return;
            }

            new AccountRepository().changePassword(account.getEmail(), newPassword);
            request.getRequestDispatcher("/product").forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("/auth/changePassword");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
