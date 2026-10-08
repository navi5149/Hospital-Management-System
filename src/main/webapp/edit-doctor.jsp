<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Edit Doctor - Pranavi Health Care</title>

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
	max-width: 850px;
	background: white;
	border-radius: 16px;
	padding: 35px 40px 40px;
	box-shadow: 0 8px 25px rgba(80, 50, 130, 0.12);
	border-top: 5px solid #6d28d9;
}

/* Heading */
.form-title {
	text-align: center;
	margin-bottom: 8px;
	color: #4c1d95;
	font-size: 28px;
}

.form-subtitle {
	text-align: center;
	color: #777;
	margin-bottom: 30px;
	font-size: 14px;
}

/* Form */
.doctor-form {
	width: 100%;
}

.form-row {
	display: flex;
	gap: 25px;
	margin-bottom: 20px;
}

.form-group {
	flex: 1;
}

/* Labels */
.form-group label {
	display: block;
	font-size: 14px;
	font-weight: 600;
	color: #4b5563;
	margin-bottom: 8px;
}

/* Inputs */
.form-group input, .form-group select {
	width: 100%;
	padding: 12px 14px;
	border: 1px solid #d8d0ea;
	border-radius: 8px;
	font-size: 14px;
	outline: none;
	background: #fff;
	transition: 0.2s;
}

.form-group input:focus, .form-group select:focus {
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
@media ( max-width : 700px) {
	.form-card {
		padding: 25px 20px 30px;
	}
	.form-row {
		flex-direction: column;
		gap: 20px;
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

	<%
	kon.atha.HospitalManagement.Entity.Doctor doctor = (kon.atha.HospitalManagement.Entity.Doctor) request
			.getAttribute("doctor");

	java.util.List<kon.atha.HospitalManagement.Entity.Department> departments = (java.util.List<kon.atha.HospitalManagement.Entity.Department>) request
			.getAttribute("departments");
	%>


	<div class="page-container">

		<div class="form-card">

			<h1 class="form-title">Edit Doctor</h1>

			<p class="form-subtitle">Update the doctor information and
				department</p>


			<form action="edit-doctor" method="post" class="doctor-form">


				<!-- Doctor ID -->
				<input type="hidden" name="doctorId"
					value="<%=doctor.getDoctorId()%>">


				<!-- Row 1 -->
				<div class="form-row">

					<div class="form-group">

						<label>First Name</label> <input type="text" name="firstName"
							value="<%=doctor.getFirstName()%>" required>

					</div>


					<div class="form-group">

						<label>Last Name</label> <input type="text" name="lastName"
							value="<%=doctor.getLastName()%>" required>

					</div>

				</div>


				<!-- Row 2 -->
				<div class="form-row">

					<div class="form-group">

						<label>Specialization</label> <input type="text"
							name="specialization" value="<%=doctor.getSpecialization()%>"
							required>

					</div>


					<div class="form-group">

						<label>Qualification</label> <input type="text"
							name="qualification" value="<%=doctor.getQualification()%>"
							required>

					</div>

				</div>


				<!-- Row 3 -->
				<div class="form-row">

					<div class="form-group">

						<label>Phone</label> <input type="text" name="phone"
							value="<%=doctor.getPhone()%>">

					</div>


					<div class="form-group">

						<label>Email</label> <input type="email" name="email"
							value="<%=doctor.getEmail()%>">

					</div>

				</div>


				<!-- Row 4 -->
				<div class="form-row">

					<div class="form-group">

						<label>Experience (Years)</label> <input type="number"
							name="experience" min="0" value="<%=doctor.getExperience()%>"
							required>

					</div>


					<div class="form-group">

						<label>Department</label> <select name="departmentId" required>

							<option value="">Select Department</option>


							<%
							for (kon.atha.HospitalManagement.Entity.Department dept : departments) {

								boolean selected = doctor.getDepartment() != null
								&& doctor.getDepartment().getDepartmentId() == dept.getDepartmentId();
							%>


							<option value="<%=dept.getDepartmentId()%>"
								<%=selected ? "selected" : ""%>>

								<%=dept.getDepartmentName()%>

							</option>


							<%
							}
							%>

						</select>

					</div>

				</div>


				<!-- Buttons -->
				<div class="button-area">

					<button type="submit" class="btn btn-primary">Update
						Doctor</button>


					<a href="doctors" class="btn btn-secondary"> Back to Doctors </a>

				</div>


			</form>

		</div>

	</div>

</body>

</html>