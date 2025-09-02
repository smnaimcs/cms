package com.example.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/testdb", "webapp_user", "My$trongP@ss123")) {

            // Check if username exists
            PreparedStatement check = con.prepareStatement("SELECT id FROM Users WHERE username=?");
            check.setString(1, username);
            ResultSet rs = check.executeQuery();
            if (rs.next()) {
                // Username already exists
                response.sendRedirect("register.jsp?error=exists");
                return;
            }

            // Insert user into DB
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Users (username, password, role) VALUES (?, ?, ?)");
            ps.setString(1, username);
            ps.setString(2, password); // ideally hash passwords
            ps.setString(3, role);
            ps.executeUpdate();

            // Redirect to login page after registration
            response.sendRedirect("login.jsp?register=success");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("register.jsp?error=db");
        }
    }
}

