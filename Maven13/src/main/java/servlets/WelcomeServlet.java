package servlets;

import pages.Page;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@WebServlet(name = "WelcomeServletName", urlPatterns = {"/welcomeservlet"})
public class WelcomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("jsp11user");
        response.sendRedirect(Page.LOGIN_PAGE.getPage());
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //Wine w = new Wine();
        if(request.getParameter("command").equals("add_wine")) {
            String wine = request.getParameter("wine");
            String year = request.getParameter("year");
            String type = request.getParameter("type");
            try {
                Class.forName("org.sqlite.JDBC");
                Connection cnn = DriverManager.getConnection("jdbc:sqlite:D://JavaLabs/JSP11/finewine.sqlite3");
                Statement st = cnn.createStatement();
                st.execute("insert into Winecart (Wine, Year, Type) values ('"+wine +"', "+Integer.parseInt(year)+", "+"'"+type+"')");
                st.close();
                cnn.close();
                response.sendRedirect(Page.WELCOME_PAGE.getPage()+"?");
            }
            catch (Exception e) {
                response.sendRedirect(Page.ERROR_PAGE.getPage());
            }
        } else {
            doGet(request, response);
        }

    }
}
