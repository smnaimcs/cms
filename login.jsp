<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login - Course Management System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(135deg, #6B73FF, #000DFF);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        .card {
            border-radius: 15px;
            padding: 30px;
            background-color: rgba(255, 255, 255, 0.95);
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.2);
        }
        h3 {
            font-weight: 700;
            color: #333;
        }
        .btn-primary {
            background-color: #6B73FF;
            border: none;
        }
        .btn-primary:hover {
            background-color: #4a54e1;
        }
        .btn-link {
            color: #6B73FF;
            text-decoration: none;
        }
        .btn-link:hover {
            text-decoration: underline;
        }
        .alert {
            font-size: 0.9rem;
        }
    </style>
</head>
<body>
<div class="card col-md-4">
    <h3 class="text-center mb-4">Login</h3>
    <form action="login" method="post">
        <div class="mb-3">
            <label class="form-label">Username</label>
            <input type="text" name="username" class="form-control" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Password</label>
            <input type="password" name="password" class="form-control" required>
        </div>
        <div class="form-check mb-3">
            <input class="form-check-input" type="checkbox" name="remember" id="remember">
            <label class="form-check-label" for="remember">Remember Me</label>
        </div>
        <button type="submit" class="btn btn-primary w-100">Login</button>
    </form>
    <div class="text-center mt-3">
        <a href="register.jsp" class="btn btn-link">Register</a>
    </div>
    <% if (request.getParameter("error") != null) { %>
        <div class="alert alert-danger mt-3 text-center">Invalid username or password!</div>
    <% } %>
</div>
</body>
</html>
