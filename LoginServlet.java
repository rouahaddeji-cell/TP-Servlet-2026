package ex1;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");

        HttpSession session = request.getSession();

        session.setAttribute("user", username);

        response.setContentType("text/html;charset=UTF-8");

        response.getWriter().println("<h1>Bienvenue " +
                username + "</h1>");

        response.getWriter().println(
                "<a href='items.html'>Acceder aux articles</a>");
    }
}
