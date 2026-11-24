package ex1;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class LoggingFilter implements Filter {

    private String logPrefix;

    @Override
    public void init(FilterConfig config) throws ServletException {
        logPrefix = config.getInitParameter("logPrefix");

        System.out.println(logPrefix + " LoggingFilter initialise");
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        String url = req.getRequestURI();

        System.out.println(logPrefix + " Avant : " + url);

        long debut = System.nanoTime();

        chain.doFilter(request, response);

        long fin = System.nanoTime();

        System.out.println(logPrefix + " Apres : " + url);

        System.out.println(logPrefix + " Duree : " +
                (fin - debut) / 1_000_000.0 + " ms");
    }

    @Override
    public void destroy() {
        System.out.println(logPrefix + " LoggingFilter detruit");
    }
}
