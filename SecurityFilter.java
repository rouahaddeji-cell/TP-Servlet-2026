package ex1;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {
        System.out.println("SecurityFilter initialise");
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);

        if (session != null && session.getAttribute("user") != null) {

            System.out.println("Utilisateur autorise");

            chain.doFilter(request, response);

        } else {

            System.out.println("Utilisateur non autorise");

            resp.sendRedirect(req.getContextPath() + "/login.html");
        }
    }

    @Override
    public void destroy() {
        System.out.println("SecurityFilter detruit");
    }
}
