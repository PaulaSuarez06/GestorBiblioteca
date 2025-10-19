package examen.biblioteca.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet("/idioma")
public class IdiomasServlet  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String language = request.getParameter("lang");
    if(language != null && (language.equals("es") || language.equals("en"))){
        Cookie cookie = new Cookie("lang",language);
        cookie.setPath("/");
        cookie.setMaxAge(60*60*24*365);
        response.addCookie(cookie);



    }
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}
