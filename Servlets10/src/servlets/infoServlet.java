package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import classes.GetInfo;

@WebServlet(name = "infoServletName", urlPatterns = {"/infoServletTest"})
public class infoServlet extends HttpServlet {
    public infoServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        GetInfo.FillPage(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.getWriter().print("This is " + this.getClass().getName() +  ",  using  the  POST  method");
    }



    public void destroy() {
        super.destroy();
    }

}
