<%@ page session="true" contentType="text/html;charset=UTF-8" language="java"  %>
<%
    String username = (String) session.getAttribute("username");
    if (session == null || !"teacher".equalsIgnoreCase((String) session.getAttribute("role"))) {
        response.sendRedirect("login.jsp"); // or show 403 page
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Teacher Dashboard</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2>Teacher Dashboard</h2>
        <h4>Your Courses</h4>
        <table class="table table-bordered">
            <tr>
                <th>ID</th>
                <th>Name</th>
            </tr>
            <%-- Display teacher's courses from DB here --%>
        </table>
        <a href="index.jsp" class="btn btn-secondary mt-3">Logout</a>
    </div>
</body>
</html>
