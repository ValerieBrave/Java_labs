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
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "UserServletName", urlPatterns = {"/userservice"})
public class UserServlet extends HttpServlet {
    FormService foser = new FormService();
    public UserServlet() throws SQLException, ClassNotFoundException {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        //response.getWriter().println("help pls");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User u = new User();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        u.username = username;
        u.setPassword(foser.HashPassword(password));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        if(foser.isRegistrated(u)) {
            //response.getWriter().println(dtf.format(now)+u.role+": welcome back, "+ u.username);
            Cookie[] cookies = request.getCookies();
            Cookie cookie = null;
            Cookie name = null;
            if(cookies !=null) {
                for(Cookie c: cookies) {
                    if(c.getName().equals("last_visit")) {
                        cookie = c;
                        break;
                    }
                }
            }
            response.getWriter().println("last visit: "+cookie.getValue());
            cookie = new Cookie("last_visit", LocalDateTime.now().toString());
            name = new Cookie("user", u.username);
            response.addCookie(cookie);
            response.addCookie(name);
        } else response.getWriter().println(dtf.format(now)+" authentication failure");


    }



    public void destroy() {
        super.destroy();
    }
}
