/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Business.Controller.User.HomeController;

import Data.Model.Account;
import Data.Model.Chat;
import Data.Repository.User.ChatRepository;
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
@WebServlet(name = "LoadmoreChat", urlPatterns = {"/home/chat/loadmore"})
public class ChatLoadmore extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("account");

        int index = Integer.parseInt(request.getParameter("index"));
        List<Chat> list = new ChatRepository().getListChat(index);

        String responseString = "";
        for (Chat chat : list) {
            String mainUserClass = (account != null && account.getId() == chat.getAccount().getId()) ? "main-user" : "";
            String profileImage = chat.getAccount().getProfile().getImage() == null
                    ? "/image/logo.jpg"
                    : "data:image/jpeg;base64, " + chat.getAccount().getProfile().getImgBase64();

            responseString += "<div class=\"chatbox-body-chat " + mainUserClass + "\">"
                    + "<div class=\"chatbox-body-chat-avatar\">"
                    + "<img src=\"" + profileImage + "\">"
                    + "</div>"
                    + "<div class=\"chatbox-body-chat-message\">"
                    + "<p class=\"chatbox-body-chat-message-username\">" + chat.getAccount().getProfile().getName() + "</p>"
                    + "<p class=\"chatbox-body-chat-message-content\">" + chat.getMessage() + "</p>"
                    + "</div>"
                    + "</div>";
        }

        PrintWriter out = response.getWriter();
        out.print(responseString);
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
