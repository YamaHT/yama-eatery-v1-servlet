/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Business.Controller.Admin.ResourceManagementController;

import Data.Model.ImageResource;
import Data.Repository.Admin.ResourceRepository;
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
@WebServlet(urlPatterns = {"/admin/management/resource/search"})
public class Search extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        List<ImageResource> list;
        if (name.equals("")) {
            list = new ResourceRepository().getAllResource();
        } else {
            list = new ResourceRepository().getAllResourceSearchByName(name);
        }
        String responseString = "";
        for (int i = 0; i < list.size(); i++) {
            responseString += " <tr class=\"resource-table-body\">\n"
                    + "                    <td>" + (i + 1) + "</td>\n"
                    + "                    <td>" + list.get(i).getName() + "</td>\n"
                    + "                    <td class=\"resource-table-body-image\"><img src=\"data:image/jpeg;base64, " + list.get(i).getImgBase64() + "\"></td>\n"
                    + "                    <td><i class=\"fa-solid fa-trash\"></i></td>\n"
                    + "                </tr>";
        }
        response.getWriter().print(responseString);
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
