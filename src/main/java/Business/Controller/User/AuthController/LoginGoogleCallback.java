/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Business.Controller.User.AuthController;

import Data.Model.Account;
import Data.Repository.User.AccountRepository;
import Utils.GoogleUtils;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang.RandomStringUtils;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "LoginGoogleCallback", urlPatterns = {"/auth/loginGoogleCallback"})
public class LoginGoogleCallback extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String code = request.getParameter("code");
            String email = new GoogleUtils().getEmailFromCode(code);

            AccountRepository accountRepo = new AccountRepository();
            Account account = accountRepo.getAccountByUsernameOrEmail(email);

            if (account == null) {
                int profileId = accountRepo.addProfile(email);
                accountRepo.register(email, email, RandomStringUtils.randomAlphanumeric(14), profileId);
                account = accountRepo.getAccountByUsernameOrEmail(email);
            }

            request.getSession().setAttribute("account", account);
            response.sendRedirect("/home");
        } catch (Exception e) {
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
