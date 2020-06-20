package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(servletNames = {"UserServletName"})
public class RegisterFilter implements Filter {

    FilterConfig filterconfig;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        filterconfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //servletResponse.getWriter().println("filter works");
        //ServletContext ctx = filterconfig.getServletContext();
        //RequestDispatcher dispatcher = ctx.getRequestDispatcher("/login.jsp");
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        Cookie[] cookies = httpRequest.getCookies();
        Cookie cookie = null;
        if(cookies !=null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("last_visit")) {
                    cookie = c;
                    break;
                }
            }
        }
        if(cookie ==null) httpResponse.sendRedirect("/war/register.jsp");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        filterconfig = null;
    }
}
