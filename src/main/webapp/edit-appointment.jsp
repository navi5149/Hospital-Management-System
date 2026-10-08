<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="kon.atha.HospitalManagement.Entity.Appointment"%>
<%@ page import="kon.atha.HospitalManagement.Entity.Patient"%>
<%@ page import="kon.atha.HospitalManagement.Entity.Doctor"%>

<%
Appointment appointment = (Appointment) request.getAttribute("appointment");

List<Patient> patients = (List<Patient>) request.getAttribute("patients");

List<Doctor> doctors = (List<Doctor>) request.getAttribute("doctors");

String appointmentDate = "";

if (appointment.getAppointmenDate() != null) {

	appointmentDate = appointment.getAppointmenDate().toString().substring(0, 16);

}
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Edit Appointment - Pranavi Health Care</title>

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
	max-width: 800px;
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

/* Rows */
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
.form-group input, .form-group select, .form-group textarea {
	width: 100%;
	padding: 12px 14px;
	border: 1px solid #d8d0ea;
	border-radius: 8px;
	font-size: 14px;
	outline: none;
	background: white;
	transition: 0.2s;
	font-family: Arial, Helvetica, sans-serif;
}

.form-group textarea {
	height: 100px;
	resize: vertical;
}

.form-group input:focus, .form-group select:focus, .form-group textarea:focus
	{
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

	<div class="page-container">

		<div class="form-card">

			<h1 class="form-title">Edit Appointment</h1>

			<p class="form-subtitle">Update the appointment details</p>


			<form action="edit-appointment" method="post">


				<!-- Appointment ID -->

				<input type="hidden" name="appointmentId"
					value="<%=appointment.getAppointmentid()%>">


				<!-- Patient + Doctor -->

				<div class="form-row">

					<div class="form-group">

						<label>Patient</label> <select name="patientId" required>

							<%
							for (Patient patient : patients) {

								boolean selected = appointment.getPatient() != null
								&& appointment.getPatient().getPatientId() == patient.getPatientId();
							%>

							<option value="<%=patient.getPatientId()%>"
								<%=selected ? "selected" : ""%>>

								<%=patient.getFirstName()%>
								<%=patient.getLastName()%>

							</option>

							<%
							}
							%>

						</select>

					</div>


					<div class="form-group">

						<label>Doctor</label> <select name="doctorId" required>

							<%
							for (Doctor doctor : doctors) {

								boolean selected = appointment.getDoctor() != null && appointment.getDoctor().getDoctorId() == doctor.getDoctorId();
							%>

							<option value="<%=doctor.getDoctorId()%>"
								<%=selected ? "selected" : ""%>>Dr.
								<%=doctor.getFirstName()%>
								<%=doctor.getLastName()%> -
								<%=doctor.getSpecialization()%>

							</option>

							<%
							}
							%>

						</select>

					</div>

				</div>


				<!-- Date + Reason -->

				<div class="form-row">

					<div class="form-group">

						<label>Appointment Date &amp; Time</label> <input
							type="datetime-local" name="appointmentDate"
							value="<%=appointmentDate%>" required>

					</div>


					<div class="form-group">

						<label>Reason</label> <input type="text" name="reason"
							value="<%=appointment.getReason()%>" required>

					</div>

				</div>


				<!-- Notes -->

				<div class="form-group">

					<label>Notes</label>

					<textarea name="notes"><%=appointment.getNotes() == null ? "" : appointment.getNotes()%></textarea>

				</div>


				<!-- Buttons -->

				<div class="button-area">

					<button type="submit" class="btn btn-primary">Update
						Appointment</button>

					<a href="appointments" class="btn btn-secondary"> Back to
						Appointments </a>

				</div>

			</form>

		</div>

	</div>

</body>

</html>