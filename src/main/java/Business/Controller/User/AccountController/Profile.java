/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Business.Controller.User.AccountController;

import Data.Model.Account;
import Data.Repository.User.AccountRepository;
import Utils.ImageUtils;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.text.SimpleDateFormat;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "Profile", urlPatterns = {"/account/profile"})
@MultipartConfig
public class Profile extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("account");

        String birthday = new SimpleDateFormat("dd-MM-yyyy").format(account.getProfile().getBirthday());
        request.setAttribute("birthday", birthday);
        request.getRequestDispatcher("/html/user/profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        AccountRepository accountRepo = new AccountRepository();
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String birthday = request.getParameter("birthday");
        Part part = request.getPart("image");
        byte[] image = null;
        if (part != null && part.getSize() > 0) {
            image = ImageUtils.compressImageFromWebsite(part.getInputStream());
        }
        accountRepo.updateProfile(account, image, birthday, name, phone, address);
        account = accountRepo.getAccountById(account.getId());
        session.setAttribute("account", account);
        response.sendRedirect("/account/profile");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
