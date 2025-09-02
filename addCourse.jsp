<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Course</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow-lg border-0 rounded-3">
                <div class="card-header bg-primary text-white text-center">
                    <h4>Add New Course</h4>
                </div>
                <div class="card-body">
                    <form action="addCourse" method="post">
                        <div class="mb-3">
                            <label for="courseName" class="form-label">Course Name</label>
                            <input type="text" class="form-control" id="courseName" name="name" required placeholder="Enter course name">
                        </div>
                        <div class="mb-3">
                            <label for="teacherId" class="form-label">Teacher ID</label>
                            <input type="number" class="form-control" id="teacherId" name="teacher_id" required placeholder="Enter teacher ID">
                        </div>
                        <button type="submit" class="btn btn-success w-100">Add Course</button>
                    </form>
                </div>
                <div class="card-footer text-center">
                    <a href="adminHome.jsp" class="btn btn-link">Back to Dashboard</a>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
