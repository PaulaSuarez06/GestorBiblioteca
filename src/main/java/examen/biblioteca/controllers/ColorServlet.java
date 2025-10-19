package examen.biblioteca.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/color")
public class ColorServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String color = request.getParameter("color");

    if (color != null && (color.equals("light") || color.equals("dark") || color.equals("primary") || color.equals("success") )) {
        Cookie cookie = new Cookie("color", color);
        cookie.setPath("/");
        cookie.setMaxAge(60*60*24*365);
        response.addCookie(cookie);
    }
        response.sendRedirect(request.getContextPath() + "/autores/list");

    }
}
