/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Business.Controller.User.AuthController;

import Data.Repository.User.AccountRepository;
import Utils.SendMailUtils;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ForgotPassword", urlPatterns = {"/auth/forgotPassword"})
public class ForgotPassword extends HttpServlet {

    AccountRepository accountRepo = new AccountRepository();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/html/user/forgotPassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            SendMailUtils sendMailUtils = new SendMailUtils();
            if (action.equals("send")) {
                String email = request.getParameter("email");
                if (accountRepo.checkAccountExisted(email) == null) {
                    request.setAttribute("error", "Email does not existed to send OTP");
                    request.getRequestDispatcher("/html/user/forgotPassword.jsp").forward(request, response);
                    return;
                }
                
                String OTP = sendMailUtils.sendMailOTP(email);
                HttpSession session = request.getSession();
                session.setAttribute("OTP", OTP);
                session.setMaxInactiveInterval(60 * 5);
                Cookie cookie = new Cookie("email", email);
                cookie.setMaxAge(60 * 5);
                response.addCookie(cookie);
            } else if (action.equals("verify")) {
                HttpSession session = request.getSession();
                String OTP = (String) session.getAttribute("OTP");
                String userOTP = String.join("", request.getParameterValues("otp"));

                if (!userOTP.equals(OTP)) {
                    session.removeAttribute("OTP");
                    request.setAttribute("error", "Your OTP input does not match with the sended OTP");
                    request.getRequestDispatcher("/html/user/forgotPassword.jsp").forward(request, response);
                    return;
                }

                Cookie[] cookies = request.getCookies();
                String email = null;
                for (Cookie cooky : cookies) {
                    if (cooky.getName().equals("email")) {
                        email = cooky.getValue();
                    }
                }
                String password = sendMailUtils.sendMailPass(email);
                accountRepo.changePassword(email, password);
                request.setAttribute("success", "Change password successfully. Please check your new email");
                request.getRequestDispatcher("/auth/login").forward(request, response);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            response.sendRedirect("/auth/login");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
