<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Course Management System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(135deg, #6B73FF 0%, #000DFF 100%);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        .card {
            background-color: rgba(255, 255, 255, 0.95);
            border-radius: 15px;
            padding: 40px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);
            text-align: center;
        }
        .card h1 {
            font-weight: 700;
            margin-bottom: 30px;
            color: #333;
        }
        .btn-primary {
            background-color: #6B73FF;
            border: none;
        }
        .btn-primary:hover {
            background-color: #4a54e1;
        }
        .btn-secondary {
            background-color: #d3d3d3;
            color: #333;
            border: none;
        }
        .btn-secondary:hover {
            background-color: #bfbfbf;
        }
    </style>
</head>
<body>
    <div class="card">
        <h1>Welcome to Course Management System</h1>
        <div class="mt-4">
            <a href="login.jsp" class="btn btn-primary btn-lg mx-2">Login</a>
            <a href="register.jsp" class="btn btn-secondary btn-lg mx-2">Register</a>
        </div>
    </div>
</body>
</html>

