package lab.mvc.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

import lab.mvc.model.Person;

@WebServlet("/Controller")
public class Controller extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String birthDay = request.getParameter("birthDay");
        String sex = request.getParameter("sex");

        Person person = new Person(
            firstName,
            lastName,
            birthDay,
            sex
        );

        request.setAttribute("person", person);

        int year = Integer.parseInt(birthDay.substring(0, 4));

        String page;

        if (year < 1990) {
            page = "/old.jsp";
        } else {
            page = "/young.jsp";
        }

        RequestDispatcher dispatcher =
            getServletContext().getRequestDispatcher(page);

        dispatcher.forward(request, response);
    }
}
