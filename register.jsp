<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register - Course Management System</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
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
            width: 100%;
            max-width: 400px;
        }
        h2 {
            font-weight: 700;
            text-align: center;
            margin-bottom: 25px;
            color: #333;
        }
        .btn-success {
            background-color: #28a745;
            border: none;
        }
        .btn-success:hover {
            background-color: #218838;
        }
        .btn-link {
            color: #6B73FF;
            text-decoration: none;
        }
        .btn-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="card">
        <h2>Register</h2>
        <form action="register" method="post">
            <div class="mb-3">
                <label class="form-label">Username:</label>
                <input type="text" class="form-control" name="username" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Password:</label>
                <input type="password" class="form-control" name="password" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Role:</label>
                <select class="form-control" name="role" required>
                    <option value="student">Student</option>
                    <option value="teacher">Teacher</option>
                    <option value="admin">Admin</option>
                </select>
            </div>
            <button type="submit" class="btn btn-success w-100">Register</button>
        </form>
        <div class="text-center mt-3">
            <a href="login.jsp" class="btn btn-link">Login</a>
        </div>
    </div>
</body>
</html>
