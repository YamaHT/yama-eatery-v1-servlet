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
@WebServlet(name = "Register", urlPatterns = {"/auth/register"})
public class Register extends HttpServlet {

    AccountRepository accountRepo = new AccountRepository();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String[] names = {"username", "email", "password"};
            String[] values = {username, email, password};

            if (accountRepo.getAccountByUsernameOrEmail(username) != null 
                    || accountRepo.getAccountByUsernameOrEmail(email) != null) {
                request.setAttribute("error", "Username or email existed");
                request.getRequestDispatcher("/auth/login").forward(request, response);
                return;
            }

            String OTP = new SendMailUtils().sendMailOTP(email);
            if (OTP == null) {
                request.setAttribute("error", "Email does not existed to send OTP");
                request.getRequestDispatcher("/auth/login").forward(request, response);
                return;
            }

            for (int i = 0; i < names.length; i++) {
                Cookie cookie = new Cookie(names[i], values[i]);
                cookie.setMaxAge(60 * 5);
                response.addCookie(cookie);
            }

            HttpSession session = request.getSession();
            session.setAttribute("OTP", OTP);
            session.setMaxInactiveInterval(60 * 5);
            request.getRequestDispatcher("/html/user/registerOTP.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("/auth/login");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String OTP = (String) request.getSession().getAttribute("OTP");
            if (OTP == null) {
                request.setAttribute("error", "OTP is expired. Please register again!");
                request.getRequestDispatcher("/auth/login").forward(request, response);
                return;
            }

            String userOTP = String.join("", request.getParameterValues("otp"));
            if (!userOTP.equals(OTP)) {
                request.setAttribute("error", "Your OTP input does not match with the sended OTP");
                request.getRequestDispatcher("/html/user/registerOTP.jsp").forward(request, response);
                return;
            }

            Cookie[] cookies = request.getCookies();
            String username = null;
            String email = null;
            String password = null;
            for (Cookie cooky : cookies) {
                switch (cooky.getName()) {
                    case "username":
                        username = cooky.getValue();
                        break;
                    case "email":
                        email = cooky.getValue();
                        break;
                    case "password":
                        password = cooky.getValue();
                        break;
                }
            }

            int profileId = accountRepo.addProfile(username);
            accountRepo.register(username, email, password, profileId);
            request.setAttribute("success", "Register successfully! You can login now");
            request.getRequestDispatcher("/auth/login").forward(request, response);
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
