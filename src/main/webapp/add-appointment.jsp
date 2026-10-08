<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="kon.atha.HospitalManagement.Entity.Patient"%>
<%@ page import="kon.atha.HospitalManagement.Entity.Doctor"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>Add Appointment - Pranavi Health Care</title>

<style>

/* =========================
   GENERAL
   ========================= */
* {
	box-sizing: border-box;
}

body {
	margin: 0;
	font-family: Arial, Helvetica, sans-serif;
	background: #f7f5fc;
	color: #333;
}

/* =========================
   PAGE CONTAINER
   ========================= */
.page-container {
	min-height: calc(100vh - 70px);
	display: flex;
	justify-content: center;
	align-items: flex-start;
	padding: 40px 20px;
}

/* =========================
   FORM CARD
   ========================= */
.form-card {
	width: 100%;
	max-width: 850px;
	background: white;
	border-radius: 14px;
	padding: 35px 40px;
	box-shadow: 0 6px 25px rgba(0, 0, 0, 0.08);
	border-top: 5px solid #6d28d9;
}

/* =========================
   TITLE
   ========================= */
.form-title {
	text-align: center;
	margin-bottom: 30px;
}

.form-title h1 {
	margin: 0;
	color: #5b21b6;
	font-size: 28px;
	font-weight: 700;
}

.form-title p {
	margin-top: 8px;
	color: #777;
	font-size: 14px;
}

/* =========================
   FORM ROW
   ========================= */
.form-row {
	display: grid;
	grid-template-columns: 1fr 1fr;
	gap: 22px;
	margin-bottom: 20px;
}

/* =========================
   FORM GROUP
   ========================= */
.form-group {
	display: flex;
	flex-direction: column;
}

.form-group.full-width {
	grid-column: span 2;
}

.form-group label {
	margin-bottom: 8px;
	color: #4c1d95;
	font-size: 14px;
	font-weight: 600;
}

/* =========================
   INPUTS
   ========================= */
.form-group input, .form-group select, .form-group textarea {
	width: 100%;
	padding: 12px 13px;
	border: 1px solid #d8c7f5;
	border-radius: 7px;
	background: #fff;
	color: #333;
	font-size: 14px;
	outline: none;
	transition: 0.2s;
}

.form-group input:focus, .form-group select:focus, .form-group textarea:focus
	{
	border-color: #6d28d9;
	box-shadow: 0 0 0 3px rgba(109, 40, 217, 0.10);
}

.form-group textarea {
	min-height: 110px;
	resize: vertical;
}

/* =========================
   BUTTONS
   ========================= */
.button-section {
	display: flex;
	justify-content: center;
	gap: 15px;
	margin-top: 30px;
}

.add-btn {
	border: none;
	background: #6d28d9;
	color: white;
	padding: 12px 25px;
	border-radius: 7px;
	font-size: 14px;
	font-weight: 600;
	cursor: pointer;
	box-shadow: 0 4px 10px rgba(109, 40, 217, 0.25);
	transition: 0.3s;
}

.add-btn:hover {
	background: #5b21b6;
	transform: translateY(-1px);
}

.back-btn {
	text-decoration: none;
	background: #f1f1f1;
	color: #555;
	padding: 12px 25px;
	border-radius: 7px;
	font-size: 14px;
	font-weight: 600;
	transition: 0.3s;
}

.back-btn:hover {
	background: #e5e5e5;
}

/* =========================
   RESPONSIVE
   ========================= */
@media ( max-width : 700px) {
	.form-card {
		padding: 25px 20px;
	}
	.form-row {
		grid-template-columns: 1fr;
		gap: 18px;
	}
	.form-group.full-width {
		grid-column: span 1;
	}
	.button-section {
		flex-direction: column;
	}
	.add-btn, .back-btn {
		text-align: center;
		width: 100%;
	}
}
</style>

</head>

<body>


	<!-- NAVBAR -->

	<%@ include file="header.jspf"%>


	<div class="page-container">

		<div class="form-card">


			<!-- TITLE -->

			<div class="form-title">

				<h1>Add New Appointment</h1>

				<p>Schedule an appointment between a patient and doctor</p>

			</div>


			<!-- =========================
             GET PATIENTS AND DOCTORS
             ========================= -->

			<%
			List<Patient> patients = (List<Patient>) request.getAttribute("patients");

			List<Doctor> doctors = (List<Doctor>) request.getAttribute("doctors");
			%>


			<!-- =========================
             APPOINTMENT FORM
             ========================= -->

			<form action="add-appointment" method="post">


				<!-- PATIENT + DOCTOR -->

				<div class="form-row">


					<!-- PATIENT -->

					<div class="form-group">

						<label for="patientId"> Patient </label> <select name="patientId"
							id="patientId" required>

							<option value="">Select Patient</option>

							<%
							if (patients != null) {

								for (Patient patient : patients) {
							%>

							<option value="<%=patient.getPatientId()%>">

								<%=patient.getFirstName()%>
								<%=patient.getLastName()%>

							</option>

							<%
							}
							}
							%>

						</select>

					</div>


					<!-- DOCTOR -->

					<div class="form-group">

						<label for="doctorId"> Doctor </label> <select name="doctorId"
							id="doctorId" required>

							<option value="">Select Doctor</option>

							<%
							if (doctors != null) {

								for (Doctor doctor : doctors) {
							%>

							<option value="<%=doctor.getDoctorId()%>">Dr.
								<%=doctor.getFirstName()%>
								<%=doctor.getLastName()%> -
								<%=doctor.getSpecialization()%>

							</option>

							<%
							}
							}
							%>

						</select>

					</div>

				</div>


				<!-- DATE + TIME -->

				<div class="form-row">


					<div class="form-group">

						<label for="appointmentDate"> Appointment Date & Time </label> <input
							type="datetime-local" id="appointmentDate" name="appointmentDate"
							required>

					</div>


					<!-- REASON -->

					<div class="form-group">

						<label for="reason"> Reason </label> <input type="text"
							id="reason" name="reason" placeholder="Enter appointment reason"
							required>

					</div>

				</div>


				<!-- NOTES -->

				<div class="form-row">

					<div class="form-group full-width">

						<label for="notes"> Notes </label>

						<textarea id="notes" name="notes"
							placeholder="Enter additional notes"></textarea>

					</div>

				</div>


				<!-- BUTTONS -->

				<div class="button-section">

					<button type="submit" class="add-btn">Add Appointment</button>


					<a href="appointments" class="back-btn"> Back to Appointments </a>

				</div>


			</form>

		</div>

	</div>


</body>
</html>