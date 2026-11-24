package ex1;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/TestAttributes")
public class AttributeTestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext context = getServletContext();

        HttpSession session = request.getSession();

        response.setContentType("text/html;charset=UTF-8");

        // APPLICATION ATTRIBUTES
        context.setAttribute("testApp", "First value");
        context.setAttribute("testApp", "Second value");
        context.removeAttribute("testApp");

        // SESSION ATTRIBUTES
        session.setAttribute("testSession", "First value");
        session.setAttribute("testSession", "Second value");
        session.removeAttribute("testSession");

        // REQUEST ATTRIBUTES
        request.setAttribute("testRequest", "First value");
        request.setAttribute("testRequest", "Second value");
        request.removeAttribute("testRequest");

        response.getWriter().println("<html><body>");
        response.getWriter().println("<h1>Attribute tests completed!</h1>");
        response.getWriter().println("<p>Check Tomcat console.</p>");
        response.getWriter().println("</body></html>");
    }
}

