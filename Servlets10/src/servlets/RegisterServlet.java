package servlets;
import classes.FormService;
import classes.GetInfo;
import classes.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;

@WebServlet(name = "RegisterServletName", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
    FormService foser = new FormService();
    public RegisterServlet() throws SQLException, ClassNotFoundException {super();}
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  {

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User u = new User();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        u.username = username;
        u.setPassword(foser.HashPassword(password));
        if(foser.isNameTaken(u.username)) response.getWriter().println("username is already taken, choose another");
        else {
            try {
                foser.Register(u);
                Cookie cookie1 = new Cookie("user", u.username);
                Cookie cookie2 = new Cookie("last_visit", LocalDateTime.now().toString());
                response.addCookie(cookie1);
                response.addCookie(cookie2);
                response.getWriter().println("registration is fine, cookie is set");
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }



    public void destroy() {
        super.destroy();
    }
}
