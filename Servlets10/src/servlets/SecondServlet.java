package servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SecondServletName", urlPatterns = {"/secondservlet"})
public class SecondServlet extends HttpServlet {
    public SecondServlet() { super(); }
    public void destroy() {
        super.destroy();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //response.getWriter().println("second servlet got GET request");
        String param = request.getParameter("call_first_servlet");
        param += " modified by second servlet";
        response.getWriter().println(param);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("register.jsp");
    }
}
