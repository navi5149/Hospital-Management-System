<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="kon.atha.HospitalManagement.Entity.Prescription"%>

<%
List<Prescription> prescriptions = (List<Prescription>) request.getAttribute("prescriptions");
%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>Add Prescription Medicine - Pranavi Health Care</title>

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

.form-group input, .form-group select {
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

.form-group input:focus, .form-group select:focus {
	border-color: #6d28d9;
	box-shadow: 0 0 0 3px rgba(109, 40, 217, 0.10);
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

				<h1>Add Prescription Medicine</h1>

				<p>Add a medicine to an existing prescription</p>

			</div>


			<form action="add-prescription-medicine" method="post">


				<!-- PRESCRIPTION -->

				<div class="form-group">

					<label for="prescriptionId"> Prescription </label> <select
						name="prescriptionId" id="prescriptionId" required>

						<option value="">Select Prescription</option>

						<%
						if (prescriptions != null && !prescriptions.isEmpty()) {

							for (Prescription p : prescriptions) {
						%>

						<option value="<%=p.getPrescriptionId()%>">Prescription
							ID:
							<%=p.getPrescriptionId()%>

							<%
							if (p.getAppointment() != null && p.getAppointment().getPatient() != null) {
							%> -
							<%=p.getAppointment().getPatient().getFirstName()%>
							<%=p.getAppointment().getPatient().getLastName()%>

							<%
							}
							%>

						</option>

						<%
						}

						} else {
						%>

						<option value="">No prescriptions found</option>

						<%
						}
						%>

					</select>

				</div>


				<!-- MEDICINE -->

				<div class="form-group">

					<label for="medicineName"> Medicine Name </label> <input
						type="text" id="medicineName" name="medicineName"
						placeholder="Example: Paracetamol" required>

				</div>


				<!-- DOSAGE -->

				<div class="form-group">

					<label for="dosage"> Dosage </label> <input type="text" id="dosage"
						name="dosage" placeholder="Example: 1 tablet twice a day" required>

				</div>


				<!-- DURATION -->

				<div class="form-group">

					<label for="duration"> Duration </label> <input type="text"
						id="duration" name="duration" placeholder="Example: 5 days"
						required>

				</div>


				<!-- BUTTONS -->

				<div class="button-section">

					<button type="submit" class="submit-btn">Save Medicine</button>

					<a href="prescription-medicines" class="back-btn"> Back to
						Medicines </a>

				</div>


			</form>

		</div>

	</div>

</body>

</html>