package com.example.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

public class AddCourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String DB_USER = "webapp_user";
    private static final String DB_PASS = "My$trongP@ss123";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8"); // handle special chars

        String name = request.getParameter("name");
        String teacherIdStr = request.getParameter("teacher_id");

        if (name == null || name.trim().isEmpty() || teacherIdStr == null || teacherIdStr.trim().isEmpty()) {
            response.sendRedirect("addCourse.jsp?error=Missing+fields");
            return;
        }
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
	            int teacherId = Integer.parseInt(teacherIdStr);

	            String sql = "INSERT INTO Courses (course_name, teacher_id) VALUES (?, ?)";
	            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	                stmt.setString(1, name);
	                stmt.setInt(2, teacherId);

	                int rows = stmt.executeUpdate();

	                if (rows > 0) {
	                    response.sendRedirect("addCourse.jsp?success=1");
	                } else {
	                    response.sendRedirect("addCourse.jsp?error=Insert+failed");
	                }
	            }

	        } catch (NumberFormatException e) {
	            e.printStackTrace();
	            response.sendRedirect("addCourse.jsp?error=Invalid+teacher+ID");
	        } catch (SQLException e) {
	            e.printStackTrace();
	            response.sendRedirect("addCourse.jsp?error=Database+error");
	        }
        } catch (Exception e) {
                    e.printStackTrace();
                    response.sendRedirect("addCourse.jsp?error=1");
        }
    }
}
