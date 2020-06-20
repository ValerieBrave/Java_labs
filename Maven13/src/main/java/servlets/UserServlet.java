package servlets;

import classes.FormService;
import classes.User;
import jms.MySender;
import pages.Page;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(name = "UserServletName", urlPatterns = {"/userservlet"})
public class UserServlet extends HttpServlet {

    final static Logger logger = Logger.getLogger(UserServlet.class.getName());
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(Page.REGISTER_PAGE.getPage());
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  {
        try {
            FormService foser = new FormService();
            if(request.getParameter("case").equals("Log in")) {
                User u = new User();
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                u.username = username;
                u.setPassword(foser.HashPassword(password));
                if(foser.isRegistrated(u)) {
                    request.getSession().setAttribute("jsp11user", username+","+password);
                    //MySender sender = new MySender();
                    //sender.Send(username);
                    response.sendRedirect(Page.WELCOME_PAGE.getPage());
                } else {
                    response.sendRedirect(Page.LOGIN_PAGE.getPage()+"?message=bad_credentials");
                }
            } else {
                doGet(request, response);
            }
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }

}
