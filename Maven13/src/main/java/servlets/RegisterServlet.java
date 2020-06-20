package servlets;

import classes.FormService;
import classes.User;
import pages.Page;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "RegisterServletName", urlPatterns = {"/registerservlet"})
public class RegisterServlet extends HttpServlet {
    FormService foser = new FormService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {}
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            User u = new User();
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            u.username = username;
            u.setPassword(foser.HashPassword(password));
            if(!foser.isNameTaken(username)) {
                foser.Register(u);
                HttpSession session = request.getSession();
                session.setAttribute("jsp11user", username+","+password);
                response.sendRedirect(Page.WELCOME_PAGE.getPage());
            } else {
                response.sendRedirect(Page.REGISTER_PAGE.getPage()+"?message=name_taken");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
