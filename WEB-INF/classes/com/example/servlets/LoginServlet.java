package com.example.servlets;

import java.security.SecureRandom;

import java.io.*;
import java.sql.*;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;


public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Generate secure random token
    private String generateToken() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[32];
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
    

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            // Load JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "webapp_user", "My$trongP@ss123");

            // Query to validate user
            PreparedStatement ps = con.prepareStatement(
                    "SELECT id, role FROM Users WHERE username=? AND password=?");
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");
				int userId = rs.getInt ("id");

                // Redirect based on role
				HttpSession session = request.getSession ();
				session.setAttribute ("username", username);
			    session.setAttribute("role", role); // add this if not already set
	    
			    // Check if user clicked "Remember Me"
			    String remember = request.getParameter("remember");
			    if ("on".equals(remember)) {
			        String token = generateToken();
			    
			        // Store token in DB
			        PreparedStatement insertPs = con.prepareStatement(
			            "INSERT INTO remember_me_tokens (user_id, role, token, expiry) VALUES (?, ?, ?, NOW() + INTERVAL 7 DAY)");
			        insertPs.setInt(1, userId);      // fetch from Users table
			        insertPs.setString(2, role);     // fetch from Users table
			        insertPs.setString(3, token);
			        insertPs.executeUpdate();
			    
			        // Create cookie
			        Cookie cookie = new Cookie("rememberMe", token);
			        cookie.setMaxAge(7 * 24 * 60 * 60); // 7 days
			        cookie.setHttpOnly(true);
			        cookie.setPath("/"); // available to the whole app
			        response.addCookie(cookie);
			    }


				session.setAttribute("role", role);
                if ("student".equalsIgnoreCase(role)) {
					Statement stmt = null;
	       		    List<Map<String, Object>> courses = new ArrayList<>();
			   	    stmt = con.createStatement();
	            	String sql = "SELECT id, course_name, teacher_id FROM Courses";
	            	ResultSet rs1 = stmt.executeQuery(sql);

		            while (rs1.next()) {
		                Map<String, Object> course = new HashMap<>();
		                course.put("id", rs1.getInt("id"));
		                course.put("name", rs1.getString("course_name"));
		                course.put("teacher", rs1.getInt("teacher_id"));
		                courses.add(course);
		            }
		            rs1.close();
				    session.setAttribute ("registered_courses", courses);
                    response.sendRedirect("studentHome.jsp");
                } else if ("teacher".equalsIgnoreCase(role)) {
                    response.sendRedirect("teacherHome.jsp");
                } else if ("admin".equalsIgnoreCase(role)) {
                    Statement stmt = null;
	       		    List<Map<String, Object>> courses = new ArrayList<>();
			   	    stmt = con.createStatement();
	            	String sql = "SELECT id, course_name, teacher_id FROM Courses";
	            	ResultSet rs1 = stmt.executeQuery(sql);

		            while (rs1.next()) {
		                Map<String, Object> course = new HashMap<>();
		                course.put("id", rs1.getInt("id"));
		                course.put("name", rs1.getString("course_name"));
		                course.put("teacher", rs1.getInt("teacher_id"));
		                courses.add(course);
		            }
		            rs1.close();
				    session.setAttribute ("courses", courses);
                    response.sendRedirect("adminHome.jsp");
                } else {
                    out.println("<h3>Unknown role. Please contact admin.</h3>");
                }
            } else {
                out.println("<h3>Invalid username or password!</h3>");
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
}

