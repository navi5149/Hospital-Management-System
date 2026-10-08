<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Edit Patient - Pranavi Health Care</title>

<style>
* {
	box-sizing: border-box;
}

body {
	margin: 0;
	font-family: Arial, Helvetica, sans-serif;
	background: #f7f5fc;
	color: #2d2340;
}

/* ================= PAGE ================= */
.page-container {
	min-height: calc(100vh - 70px);
	display: flex;
	justify-content: center;
	align-items: center;
	padding: 40px 20px;
}

/* ================= FORM CARD ================= */
.form-card {
	width: 100%;
	max-width: 800px;
	background: white;
	padding: 35px 40px;
	border-radius: 15px;
	border: 1px solid #dfd2f3;
	border-top: 5px solid #6d28d9;
	box-shadow: 0 10px 30px rgba(70, 30, 120, 0.12);
}

/* ================= HEADER ================= */
.form-header {
	text-align: center;
	margin-bottom: 30px;
}

.form-header h1 {
	margin: 0;
	color: #35156b;
	font-size: 30px;
}

.form-header p {
	margin-top: 8px;
	color: #777;
	font-size: 14px;
}

/* ================= FORM ================= */
form {
	width: 100%;
}

/* ================= FORM ROW ================= */
.form-row {
	display: grid;
	grid-template-columns: 1fr 1fr;
	gap: 20px;
	margin-bottom: 20px;
}

/* ================= FORM GROUP ================= */
.form-group {
	display: flex;
	flex-direction: column;
}

.form-group.full-width {
	grid-column: 1/-1;
}

/* ================= LABEL ================= */
label {
	margin-bottom: 7px;
	font-size: 14px;
	font-weight: 600;
	color: #4b4455;
}

/* ================= INPUTS ================= */
input, select, textarea {
	width: 100%;
	padding: 11px 13px;
	border: 1px solid #d8cce8;
	border-radius: 8px;
	font-size: 14px;
	font-family: Arial, Helvetica, sans-serif;
	outline: none;
	background: #fff;
	transition: 0.2s;
}

input:focus, select:focus, textarea:focus {
	border-color: #6d28d9;
	box-shadow: 0 0 0 3px rgba(109, 40, 217, 0.10);
}

textarea {
	height: 90px;
	resize: vertical;
}

/* ================= BUTTON AREA ================= */
.button-area {
	display: flex;
	justify-content: center;
	gap: 15px;
	margin-top: 30px;
	padding-top: 25px;
	border-top: 1px solid #eee5f8;
}

/* ================= BUTTONS ================= */
.btn {
	min-width: 150px;
	padding: 12px 20px;
	border-radius: 8px;
	text-align: center;
	text-decoration: none;
	font-size: 14px;
	font-weight: 600;
	cursor: pointer;
	transition: 0.2s;
}

/* UPDATE BUTTON */
.update-btn {
	background: #6d28d9;
	color: white;
	border: none;
	box-shadow: 0 5px 12px rgba(109, 40, 217, 0.25);
}

.update-btn:hover {
	background: #5521a8;
	transform: translateY(-1px);
}

/* BACK BUTTON */
.back-btn {
	background: #f1edf7;
	color: #5c5368;
	border: 1px solid #d9cde8;
}

.back-btn:hover {
	background: #e6ddf0;
	color: #35156b;
}

/* ================= RESPONSIVE ================= */
@media ( max-width : 700px) {
	.page-container {
		padding: 25px 15px;
	}
	.form-card {
		padding: 25px 20px;
	}
	.form-row {
		grid-template-columns: 1fr;
	}
	.form-group.full-width {
		grid-column: auto;
	}
	.button-area {
		flex-direction: column;
	}
	.btn {
		width: 100%;
	}
}
</style>

</head>


<body>


	<!-- NAVBAR -->

	<%@ include file="header.jspf"%>


	<%
	kon.atha.HospitalManagement.Entity.Patient patient = (kon.atha.HospitalManagement.Entity.Patient) request
			.getAttribute("patient");
	%>


	<!-- PAGE -->

	<div class="page-container">


		<!-- FORM CARD -->

		<div class="form-card">


			<!-- HEADER -->

			<div class="form-header">

				<h1>Edit Patient</h1>

				<p>Update the patient's information below</p>

			</div>


			<!-- FORM -->

			<form action="edit-patient" method="post">


				<!-- HIDDEN PATIENT ID -->

				<input type="hidden" name="patientId"
					value="<%=patient.getPatientId()%>">


				<!-- FIRST ROW -->

				<div class="form-row">


					<div class="form-group">

						<label>First Name</label> <input type="text" name="firstName"
							value="<%=patient.getFirstName()%>" required>

					</div>


					<div class="form-group">

						<label>Last Name</label> <input type="text" name="lastName"
							value="<%=patient.getLastName()%>" required>

					</div>


				</div>


				<!-- SECOND ROW -->

				<div class="form-row">


					<div class="form-group">

						<label>Date of Birth</label> <input type="date" name="dateOfBirth"
							value="<%=patient.getDateOfBirth() != null ? patient.getDateOfBirth() : ""%>">

					</div>


					<div class="form-group">

						<label>Gender</label> <select name="gender">

							<option value="Male"
								<%="Male".equals(patient.getGender()) ? "selected" : ""%>>
								Male</option>

							<option value="Female"
								<%="Female".equals(patient.getGender()) ? "selected" : ""%>>
								Female</option>

							<option value="Other"
								<%="Other".equals(patient.getGender()) ? "selected" : ""%>>
								Other</option>

						</select>

					</div>


				</div>


				<!-- THIRD ROW -->

				<div class="form-row">


					<div class="form-group">

						<label>Phone</label> <input type="text" name="phone"
							value="<%=patient.getPhone()%>">

					</div>


					<div class="form-group">

						<label>Email</label> <input type="email" name="email"
							value="<%=patient.getEmail()%>">

					</div>


				</div>


				<!-- ADDRESS -->

				<div class="form-row">

					<div class="form-group full-width">

						<label>Address</label>

						<textarea name="address"><%=patient.getAddress()%></textarea>

					</div>

				</div>


				<!-- BLOOD GROUP -->

				<div class="form-row">


					<div class="form-group">

						<label>Blood Group</label> <select name="bloodGroup">

							<option value="">Select</option>

							<option value="A+"
								<%="A+".equals(patient.getBloodGroup()) ? "selected" : ""%>>
								A+</option>

							<option value="A-"
								<%="A-".equals(patient.getBloodGroup()) ? "selected" : ""%>>
								A-</option>

							<option value="B+"
								<%="B+".equals(patient.getBloodGroup()) ? "selected" : ""%>>
								B+</option>

							<option value="B-"
								<%="B-".equals(patient.getBloodGroup()) ? "selected" : ""%>>
								B-</option>

							<option value="AB+"
								<%="AB+".equals(patient.getBloodGroup()) ? "selected" : ""%>>
								AB+</option>

							<option value="AB-"
								<%="AB-".equals(patient.getBloodGroup()) ? "selected" : ""%>>
								AB-</option>

							<option value="O+"
								<%="O+".equals(patient.getBloodGroup()) ? "selected" : ""%>>
								O+</option>

							<option value="O-"
								<%="O-".equals(patient.getBloodGroup()) ? "selected" : ""%>>
								O-</option>

						</select>

					</div>


				</div>


				<!-- BUTTONS -->

				<div class="button-area">


					<button type="submit" class="btn update-btn">Update
						Patient</button>


					<a href="patients" class="btn back-btn"> Back to Patients </a>


				</div>


			</form>


		</div>

	</div>


</body>

</html>