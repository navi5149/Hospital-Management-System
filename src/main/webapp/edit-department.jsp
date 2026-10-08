<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="kon.atha.HospitalManagement.Entity.Department"%>

<%
Department department = (Department) request.getAttribute("department");
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Edit Department - Pranavi Health Care</title>

<style>
* {
	box-sizing: border-box;
}

body {
	margin: 0;
	font-family: Arial, Helvetica, sans-serif;
	background: #f6f3ff;
	color: #333;
}

/* Main Page */
.page-container {
	min-height: calc(100vh - 70px);
	display: flex;
	justify-content: center;
	align-items: center;
	padding: 40px 20px;
}

/* Form Card */
.form-card {
	width: 100%;
	max-width: 650px;
	background: white;
	border-radius: 16px;
	padding: 35px 40px 40px;
	box-shadow: 0 8px 25px rgba(80, 50, 130, 0.12);
	border-top: 5px solid #6d28d9;
}

/* Heading */
.form-title {
	text-align: center;
	margin: 0 0 8px;
	color: #4c1d95;
	font-size: 28px;
}

.form-subtitle {
	text-align: center;
	color: #777;
	margin: 0 0 30px;
	font-size: 14px;
}

/* Form */
.form-group {
	margin-bottom: 22px;
}

.form-group label {
	display: block;
	font-size: 14px;
	font-weight: 600;
	color: #4b5563;
	margin-bottom: 8px;
}

.form-group input {
	width: 100%;
	padding: 12px 14px;
	border: 1px solid #d8d0ea;
	border-radius: 8px;
	font-size: 14px;
	outline: none;
	transition: 0.2s;
}

.form-group input:focus {
	border-color: #7c3aed;
	box-shadow: 0 0 0 3px rgba(124, 58, 237, 0.10);
}

/* Buttons */
.button-area {
	display: flex;
	justify-content: center;
	gap: 15px;
	margin-top: 30px;
}

.btn {
	padding: 12px 28px;
	border-radius: 8px;
	border: none;
	font-size: 14px;
	font-weight: 600;
	cursor: pointer;
	text-decoration: none;
	display: inline-block;
	transition: 0.2s;
}

.btn-primary {
	background: #6d28d9;
	color: white;
}

.btn-primary:hover {
	background: #5b21b6;
}

.btn-secondary {
	background: #eee9f8;
	color: #4c1d95;
}

.btn-secondary:hover {
	background: #ddd5ef;
}

/* Responsive */
@media ( max-width : 600px) {
	.form-card {
		padding: 25px 20px 30px;
	}
	.button-area {
		flex-direction: column;
	}
	.btn {
		width: 100%;
		text-align: center;
	}
}
</style>

</head>

<body>

	<%@ include file="header.jspf"%>


	<div class="page-container">

		<div class="form-card">

			<h1 class="form-title">Edit Department</h1>

			<p class="form-subtitle">Update the department information</p>


			<form action="edit-department" method="post">


				<!-- Department ID -->

				<input type="hidden" name="departmentId"
					value="<%=department.getDepartmentId()%>">


				<div class="form-group">

					<label>Department Name</label> <input type="text"
						name="departmentName" value="<%=department.getDepartmentName()%>"
						required>

				</div>


				<div class="form-group">

					<label>Location</label> <input type="text" name="location"
						value="<%=department.getLocation()%>" required>

				</div>


				<div class="button-area">

					<button type="submit" class="btn btn-primary">Update
						Department</button>

					<a href="departments" class="btn btn-secondary"> Back to
						Departments </a>

				</div>


			</form>

		</div>

	</div>

</body>

</html>