<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="kon.atha.HospitalManagement.Entity.Patient"%>
<%@ page import="kon.atha.HospitalManagement.Entity.Bed"%>

<%
List<Patient> patients = (List<Patient>) request.getAttribute("patients");

List<Bed> beds = (List<Bed>) request.getAttribute("beds");
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Add Admission - Pranavi Health Care</title>

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
	max-width: 850px;
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
	font-weight: 700;
}

.form-title p {
	margin-top: 8px;
	color: #777;
	font-size: 14px;
}

/* FORM ROW */
.form-row {
	display: grid;
	grid-template-columns: 1fr 1fr;
	gap: 22px;
	margin-bottom: 20px;
}

/* FORM GROUP */
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

/* INPUTS */
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
	transition: 0.3s;
}

.back-btn:hover {
	background: #e5e5e5;
}

/* RESPONSIVE */
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

				<h1>Add New Admission</h1>

				<p>Admit a patient and assign an available hospital bed</p>

			</div>


			<form action="add-admission" method="post">


				<!-- PATIENT AND BED -->

				<div class="form-row">

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


					<div class="form-group">

						<label for="bedId"> Bed </label> <select name="bedId" id="bedId"
							required>

							<option value="">Select Available Bed</option>

							<%
							if (beds != null) {

								for (Bed bed : beds) {

									if ("Available".equalsIgnoreCase(bed.getStatus())) {
							%>

							<option value="<%=bed.getBedId()%>">

								<%=bed.getBedNumber()%> -
								<%=bed.getBedType()%> - ₹<%=bed.getChargePerDay()%>

							</option>

							<%
							}
							}
							}
							%>

						</select>

					</div>

				</div>


				<!-- ADMISSION DATE -->

				<div class="form-row">

					<div class="form-group">

						<label for="admissionDate"> Admission Date & Time </label> <input
							type="datetime-local" id="admissionDate" name="admissionDate"
							required>

					</div>


					<div class="form-group">

						<label> Bed Information </label>

						<div
							style="padding: 12px 13px; background: #f7f3ff; border: 1px solid #d8c7f5; border-radius: 7px; color: #6d28d9; font-size: 14px;">
							Only available beds are shown.</div>

					</div>

				</div>


				<!-- REASON -->

				<div class="form-row">

					<div class="form-group full-width">

						<label for="reasons"> Reason for Admission </label>

						<textarea id="reasons" name="reasons"
							placeholder="Enter reason for admission" required></textarea>

					</div>

				</div>


				<!-- BUTTONS -->

				<div class="button-section">

					<button type="submit" class="submit-btn">Admit Patient</button>

					<a href="admissions" class="back-btn"> Back to Admissions </a>

				</div>


			</form>

		</div>

	</div>

</body>

</html>
