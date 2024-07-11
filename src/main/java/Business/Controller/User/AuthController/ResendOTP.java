/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Business.Controller.User.AuthController;

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
@WebServlet(name = "ResendOTP", urlPatterns = {"/auth/resendOTP"})
public class ResendOTP extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean found = false;
        Cookie[] cookies = request.getCookies();
        String email = null;
        for (Cookie cooky : cookies) {
            if (cooky.getName().equals("email")) {
                email = cooky.getValue();
                cooky.setMaxAge(60 * 5);
                response.addCookie(cooky);
                found = true;
            } else if (cooky.getName().equals("password")) {
                cooky.setMaxAge(60 * 5);
                response.addCookie(cooky);
                found = true;
            }
        }
        if (!found) {
            request.getRequestDispatcher("/auth/login").forward(request, response);
            return;
        }
        String OTP = new SendMailUtils().sendMailOTP(email);
        HttpSession session = request.getSession();
        session.setAttribute("OTP", OTP);
        session.setMaxInactiveInterval(60 * 5);
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
