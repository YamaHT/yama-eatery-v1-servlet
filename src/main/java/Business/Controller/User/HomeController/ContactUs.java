/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Business.Controller.User.HomeController;

import Utils.SendMailUtils;
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
@WebServlet(name = "ContactUs", urlPatterns = {"/home/contactUs"})
public class ContactUs extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/html/user/contactUs.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String message = request.getParameter("message");
            new SendMailUtils().sendMailNormal("DuyLPCE181153@fpt.edu.vn",
                    "Mail from " + email,
                    "Hello, My name is: <span style='font-weight: bold'>" + name + "</span>"
                    + "<br/><br/> I am contacting you to communicate the following message: "
                    + "<br/><span style='font-weight: bold'>" + message + "</span>"
                    + "<br/><br/> Best regard,"
                    + "<br/><span style='font-weight:bold'>" + name + "</span>");
            request.setAttribute("success", "Successfully sended a contact");
        } catch (Exception e) {
        }
        request.getRequestDispatcher("/html/user/contactUs.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
