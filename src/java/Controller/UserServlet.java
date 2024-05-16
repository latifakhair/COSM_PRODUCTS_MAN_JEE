/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dao.UserDAO;
import Dao.UserDAOImp;
import Model.User;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author utilisateur
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class UserServlet extends HttpServlet {

   
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAOImp();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = new User(username, password);

        if (userDAO.validateUser(user)) {
            // User authentication successful
            response.sendRedirect("DashboardServlet"); // Redirect to welcome page for user
        } else {
             // User authentication failed
            request.setAttribute("errorMessage", "Invalid username or password."); // Set error message
            request.getRequestDispatcher("/index.jsp").forward(request, response); // Forward to login page with error message
                 
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        // Process any additional logic here if needed
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
