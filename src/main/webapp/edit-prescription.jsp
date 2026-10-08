<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="kon.atha.HospitalManagement.Entity.Prescription"%>
<%@ page import="kon.atha.HospitalManagement.Entity.Appointment"%>

<%
Prescription prescription = (Prescription) request.getAttribute("prescription");

List<Appointment> appointments = (List<Appointment>) request.getAttribute("appointments");

String prescriptionDate = "";

if (prescription.getPrescriptionDate() != null) {

	prescriptionDate = prescription.getPrescriptionDate().toString().substring(0, 16);
}
%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>Edit Prescription - Pranavi Health Care</title>

<style>
* {
	box-sizing: border-box;
}

body {
	margin: 0;
	font-family: Arial, Helvetica, sans-serif;
	background: #f7f5fc;
	color: #333;
}

/* PAGE */
.page-container {
	min-height: calc(100vh - 70px);
	display: flex;
	justify-content: center;
	align-items: flex-start;
	padding: 40px 20px;
}

/* FORM CARD */
.form-card {
	width: 100%;
	max-width: 750px;
	background: white;
	border-radius: 14px;
	padding: 35px 40px;
	box-shadow: 0 6px 25px rgba(0, 0, 0, 0.08);
	border-top: 5px solid #6d28d9;
}

/* TITLE */
.form-title {
	text-align: center;
	margin-bottom: 30px;
}

.form-title h1 {
	margin: 0;
	color: #5b21b6;
	font-size: 28px;
}

.form-title p {
	margin-top: 8px;
	color: #777;
	font-size: 14px;
}

/* FORM */
.form-group {
	display: flex;
	flex-direction: column;
	margin-bottom: 22px;
}

.form-group label {
	margin-bottom: 8px;
	color: #4c1d95;
	font-size: 14px;
	font-weight: 600;
}

.form-group select, .form-group input, .form-group textarea {
	width: 100%;
	padding: 12px 13px;
	border: 1px solid #d8c7f5;
	border-radius: 7px;
	background: white;
	color: #333;
	font-size: 14px;
	outline: none;
	transition: 0.2s;
}

.form-group select:focus, .form-group input:focus, .form-group textarea:focus
	{
	border-color: #6d28d9;
	box-shadow: 0 0 0 3px rgba(109, 40, 217, 0.10);
}

.form-group textarea {
	min-height: 120px;
	resize: vertical;
}

/* PRESCRIPTION ID */
.id-box {
	padding: 12px 13px;
	background: #f7f3ff;
	border: 1px solid #d8c7f5;
	border-radius: 7px;
	color: #6d28d9;
	font-size: 14px;
	font-weight: 600;
}

/* BUTTONS */
.button-section {
	display: flex;
	justify-content: center;
	gap: 15px;
	margin-top: 30px;
}

.submit-btn {
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

.submit-btn:hover {
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
}

.back-btn:hover {
	background: #e5e5e5;
}

/* RESPONSIVE */
@media ( max-width : 700px) {
	.form-card {
		padding: 25px 20px;
	}
	.button-section {
		flex-direction: column;
	}
	.submit-btn, .back-btn {
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

			<div class="form-title">

				<h1>Edit Prescription</h1>

				<p>Update prescription and appointment information</p>

			</div>


			<form action="edit-prescription" method="post">


				<!-- PRESCRIPTION ID -->

				<div class="form-group">

					<label> Prescription ID </label>

					<div class="id-box">
						<%=prescription.getPrescriptionId()%>
					</div>

				</div>


				<input type="hidden" name="prescriptionId"
					value="<%=prescription.getPrescriptionId()%>">


				<!-- APPOINTMENT -->

				<div class="form-group">

					<label for="appointmentId"> Appointment </label> <select
						name="appointmentId" id="appointmentId" required>

						<%
						if (appointments != null) {

							for (Appointment appointment : appointments) {

								boolean selected = prescription.getAppointment() != null
								&& prescription.getAppointment().getAppointmentid() == appointment.getAppointmentid();
						%>

						<option value="<%=appointment.getAppointmentid()%>"
							<%=selected ? "selected" : ""%>>Appointment #<%=appointment.getAppointmentid()%>

							<%
							if (appointment.getPatient() != null) {
							%> -
							<%=appointment.getPatient().getFirstName()%>
							<%=appointment.getPatient().getLastName()%>

							<%
							}

							if (appointment.getDoctor() != null) {
							%> - Dr.
							<%=appointment.getDoctor().getFirstName()%>
							<%=appointment.getDoctor().getLastName()%>

							<%
							}
							%>

						</option>

						<%
						}
						}
						%>

					</select>

				</div>


				<!-- DATE -->

				<div class="form-group">

					<label for="prescriptionDate"> Prescription Date & Time </label> <input
						type="datetime-local" id="prescriptionDate"
						name="prescriptionDate" value="<%=prescriptionDate%>" required>

				</div>


				<!-- NOTES -->

				<div class="form-group">

					<label for="notes"> Notes </label>

					<textarea id="notes" name="notes"><%=prescription.getNotes() == null ? "" : prescription.getNotes()%></textarea>

				</div>


				<!-- BUTTONS -->

				<div class="button-section">

					<button type="submit" class="submit-btn">Update
						Prescription</button>

					<a href="prescriptions" class="back-btn"> Back to Prescriptions
					</a>

				</div>


			</form>

		</div>

	</div>

</body>

</html>