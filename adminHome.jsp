<%@ page session="true" contentType="text/html;charset=UTF-8" language="java" %>
<%
    String username = (String) session.getAttribute("username");
    if (session == null || !"admin".equalsIgnoreCase((String) session.getAttribute("role"))) {
        response.sendRedirect("login.jsp"); // or show 403 page
        return;
    }
%>
<%@ page import="java.util.*, java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
	<title>Admin Home</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
	<h2>Welcome Admin <%= username %></h2>
        <a href="addCourse.jsp" class="btn btn-success mb-3">Add New Course</a>
        <h4>All Courses</h4>
        <table class="table table-bordered">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Teacher</th>
            </tr>
            <%-- Display courses from DB here --%>
	    <%
            List<Map<String,Object>> courses = (List<Map<String,Object>>) session.getAttribute("courses");
            if (courses != null) {
                for (Map<String,Object> course : courses) {
        %>
        <tr>
            <td><%= course.get("id") %></td>
            <td><%= course.get("name") %></td>
            <td><%= course.get("teacher") %></td>
        </tr>
        <%
                }
            }
        %>

        </table>
        <a href="index.jsp" class="btn btn-secondary mt-3">Logout</a>
    </div>
</body>
</html>

