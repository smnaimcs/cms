package com.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/cms/*") // mapped to the base path of your app
public class HomeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("role") != null) {
            String role = (String) session.getAttribute("role");

            // Redirect user based on role
            switch (role.toLowerCase()) {
                case "student":
                    response.sendRedirect("student.jsp");
                    break;
                case "teacher":
                    response.sendRedirect("teacher.jsp");
                    break;
                case "admin":
                    response.sendRedirect("admin.jsp");
                    break;
                default:
                    response.sendRedirect("login.jsp"); // unknown role fallback
            }
        } else {
            // No session → redirect to login page
            response.sendRedirect("login.jsp");
        }
    }
}

