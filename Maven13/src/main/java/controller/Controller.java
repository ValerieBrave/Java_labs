package controller;

import pages.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ControllerName", urlPatterns = {"/controller"})
public class Controller extends HttpServlet {
    private static final String COMMAND = "command";
    private static final String ERROR_MESSAGE = "error_message";
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String command = request.getParameter(COMMAND);
            if(command.equals("log_in")) request.getRequestDispatcher("/userservlet").forward(request,response);
            else if(command.equals("log_out")) request.getRequestDispatcher("/welcomeservlet").forward(request, response);
            else if(command.equals("add_wine")) request.getRequestDispatcher("/welcomeservlet").forward(request, response);
            else if(command.equals("register")) request.getRequestDispatcher("/registerservlet").forward(request, response);
        } catch (Exception e) {
            response.sendRedirect(Page.ERROR_PAGE.getPage());
        }
    }
}
