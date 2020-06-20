package servlets;

import classes.FormService;
import classes.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "UserServletName", urlPatterns = {"/userservlet"})
public class UserServlet extends HttpServlet {
    FormService foser = new FormService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("views/register.jsp");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  {
        try {
            if(request.getParameter("case").equals("Log in")) {
                User u = new User();
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                u.username = username;
                u.setPassword(foser.HashPassword(password));
                if(foser.isRegistrated(u, foser.GetAllUsers())) {
                    request.getSession().setAttribute("jsp11user", username+","+password);
                    response.sendRedirect("views/welcome.jsp");
                } else {
                    response.sendRedirect("views/login.jsp?message=bad_credentials");
                }
            } else {
                doGet(request, response);
            }


        } catch ( Exception e) {

        }





    }

}
