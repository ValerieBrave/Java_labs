package classes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.PrintWriter;
import java.util.Enumeration;
public class GetInfo {
    public static void FillPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        response.getWriter().println("Time: "+dtf.format(now));
        response.getWriter().println("HTTP version: "+request.getProtocol());
        response.getWriter().println("IP: "+request.getRemoteAddr());
        response.getWriter().println("Method: "+request.getMethod());
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            out.write(headerName);
            out.write("===");
            Enumeration<String> headers = request.getHeaders(headerName);
            while (headers.hasMoreElements()) {
                String headerValue = headers.nextElement();
                out.write("" + headerValue);
                out.write("<br/>");
            }
        }
        out.close();
    }
}
