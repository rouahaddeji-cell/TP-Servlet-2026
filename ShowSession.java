package ex1;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.temporal.ChronoUnit;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ShowSession extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        String fullName = (String) session.getAttribute("fullName");
        String birthday = (String) session.getAttribute("birthday");

        out.println("<!DOCTYPE html>");
        out.println("<html><head><meta charset='UTF-8'>");
        out.println("<title>Session Information</title></head><body>");
        out.println("<h2>Session Information</h2>");

        if (fullName == null || birthday == null) {
            out.println("<form method='post' action='ShowSession'>");
            out.println("Full name: <input name='fullName' required><br><br>");
            out.println("Birthday: <input type='date' name='birthday' required><br><br>");
            out.println("<button type='submit'>Submit</button>");
            out.println("</form>");
        } else {
            out.println("<p>Full name: " + escapeHtml(fullName) + "</p>");
            out.println("<p>Birthday: " + escapeHtml(birthday) + "</p>");

            try {
                LocalDate birthDate = LocalDate.parse(birthday);
                LocalDate today = LocalDate.now();

                MonthDay birthMonthDay = MonthDay.from(birthDate);
                MonthDay todayMonthDay = MonthDay.from(today);

                LocalDate nextBirthday = birthMonthDay.atYear(today.getYear());

                if (!nextBirthday.isAfter(today)) {
                    nextBirthday = birthMonthDay.atYear(today.getYear() + 1);
                }

                long days = ChronoUnit.DAYS.between(today, nextBirthday);

                out.println("<p>Days until next birthday: " + days + "</p>");
            } catch (Exception e) {
                out.println("<p>Invalid birthday.</p>");
            }

            out.println("<p><a href='ShowSession?reset=true'>Enter again</a></p>");
        }

        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        session.setAttribute("fullName", request.getParameter("fullName"));
        session.setAttribute("birthday", request.getParameter("birthday"));

        response.sendRedirect("ShowSession");
    }

    private String escapeHtml(String value) {
        if (value == null) return "";
        return value.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#39;");
    }
}
