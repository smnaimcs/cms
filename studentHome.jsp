<%@ page session="true" contentType="text/html;charset=UTF-8" language="java" %>
<%
    String username = (String) session.getAttribute("username");
    if (session == null || !"student".equalsIgnoreCase((String) session.getAttribute("role"))) {
        response.sendRedirect("login.jsp"); // or show 403 page
        return;
    }
%>
<%@ page import="java.util.*, java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student Dashboard</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
	    <h2>Welcome student <%= username %></h2>
        <h4>Available Courses</h4>
        <table class="table table-bordered">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Action</th>
            </tr>
            <%-- Display available courses from DB here --%>
		    <%
	            List<Map<String,Object>> courses = (List<Map<String,Object>>) session.getAttribute("registered_courses");
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
        <h4 class="mt-4">Your Registered Courses</h4>
        <table class="table table-bordered">
            <tr>
                <th>ID</th>
                <th>Name</th>
            </tr>
            <%-- Display registered courses from DB here --%>
        </table>
        <a href="index.jsp" class="btn btn-secondary mt-3">Logout</a>
    </div>
</body>
</html>
