package ex1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ListItems")
public class ListItems extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String newItem = request.getParameter("newItem");
        HttpSession session = request.getSession();

        ArrayList<String> items =
                (ArrayList<String>) session.getAttribute("items");

        if (items == null) {
            items = new ArrayList<>();
            session.setAttribute("items", items);
        }

        Map<String, Integer> itemCounts =
                (Map<String, Integer>) session.getAttribute("itemCounts");

        if (itemCounts == null) {
            itemCounts = new HashMap<>();
            session.setAttribute("itemCounts", itemCounts);
        }

        if (newItem != null && !newItem.trim().isEmpty()) {
            newItem = newItem.trim();
            items.add(newItem);
            itemCounts.put(newItem,
                    itemCounts.getOrDefault(newItem, 0) + 1);
        }

        displayItems(response, items, itemCounts);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        ArrayList<String> items =
                (ArrayList<String>) session.getAttribute("items");

        Map<String, Integer> itemCounts =
                (Map<String, Integer>) session.getAttribute("itemCounts");

        if (items == null) {
            items = new ArrayList<>();
        }

        if (itemCounts == null) {
            itemCounts = new HashMap<>();
        }

        displayItems(response, items, itemCounts);
    }

    private void displayItems(HttpServletResponse response,
                              ArrayList<String> items,
                              Map<String, Integer> itemCounts)
            throws IOException {

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>Liste des éléments</title>");
        out.println("</head>");
        out.println("<body>");

        out.println("<h2>Liste des éléments</h2>");

        if (items.isEmpty()) {

            out.println("<p>Aucun élément enregistré.</p>");

        } else {

            out.println("<table border='1' cellpadding='8'>");
            out.println("<tr><th>Élément</th><th>Nombre de saisies</th></tr>");

            for (Map.Entry<String, Integer> entry : itemCounts.entrySet()) {

                out.println("<tr><td>"
                        + escapeHtml(entry.getKey())
                        + "</td><td>"
                        + entry.getValue()
                        + "</td></tr>");
            }

            out.println("</table>");

            out.println("<h3>Historique des saisies</h3>");
            out.println("<ul>");

            for (String item : items) {
                out.println("<li>" + escapeHtml(item) + "</li>");
            }

            out.println("</ul>");
        }

        out.println("<p><a href='items.html'>Ajouter un autre élément</a></p>");

        out.println("</body>");
        out.println("</html>");
    }

    private String escapeHtml(String value) {

        return value.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#39;");
    }
}
