package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FirstServletName", urlPatterns = {"/firstservlet"})
public class FirstServlet extends HttpServlet {
    public FirstServlet() { super(); }
    public void destroy() {
        super.destroy();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.getWriter().println("first servlet");
        response.getWriter().println(request.getParameter("call_first_servlet"));
        request.getRequestDispatcher("secondservlet").forward(request,response);
        //response.sendRedirect("secondservlet");

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("secondservlet").forward(request, response);
    }
}
