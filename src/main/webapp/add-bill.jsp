<%@ page import="java.util.List"%>
<%@ page import="kon.atha.HospitalManagement.Entity.Patient"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Bill - Pranavi Health Care</title>

<style>
* {
	box-sizing: border-box;
}

body {
	margin: 0;
	font-family: Arial, Helvetica, sans-serif;
	background: #f6f3fb;
}

.main-content {
	padding: 45px;
	display: flex;
	justify-content: center;
}

.form-card {
	width: 700px;
	background: white;
	border-radius: 14px;
	padding: 35px;
	box-shadow: 0 5px 20px rgba(80, 40, 130, 0.12);
	border-top: 5px solid #6d28d9;
}

.form-title {
	margin-bottom: 28px;
}

.form-title h1 {
	margin: 0;
	color: #4c1d95;
	font-size: 28px;
}

.form-title p {
	color: #777;
	margin-top: 7px;
}

.form-group {
	margin-bottom: 20px;
}

.form-group label {
	display: block;
	margin-bottom: 8px;
	font-weight: bold;
	color: #4b3869;
}

.form-group input, .form-group select {
	width: 100%;
	padding: 12px;
	border: 1px solid #d8cfea;
	border-radius: 7px;
	font-size: 14px;
}

.form-group input:focus, .form-group select:focus {
	border-color: #6d28d9;
	outline: none;
}

.button-row {
	display: flex;
	gap: 12px;
	margin-top: 25px;
}

.submit-btn {
	border: none;
	background: #6d28d9;
	color: white;
	padding: 12px 22px;
	border-radius: 7px;
	cursor: pointer;
	font-weight: bold;
}

.back-btn {
	text-decoration: none;
	background: #eee9f8;
	color: #4c1d95;
	padding: 12px 22px;
	border-radius: 7px;
	font-weight: bold;
}
</style>
</head>

<body>

	<%@ include file="header.jspf"%>

	<div class="main-content">

		<div class="form-card">

			<div class="form-title">
				<h1>Add New Bill</h1>
				<p>Create a new bill for a patient</p>
			</div>

			<%
			List<Patient> patients = (List<Patient>) request.getAttribute("patients");
			%>

			<form action="add-bill" method="post">

				<div class="form-group">

					<label>Patient</label> <select name="patientId" required>

						<option value="">Select Patient</option>

						<%
						if (patients != null) {
							for (Patient patient : patients) {
						%>

						<option value="<%=patient.getPatientId()%>">
							<%=patient.getPatientId()%> -
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

					<label>Total Amount</label> <input type="number" name="totalAmount"
						step="0.01" min="0" required>

				</div>

				<div class="form-group">

					<label>Discount</label> <input type="number" name="discount"
						step="0.01" min="0" value="0">

				</div>

				<div class="button-row">

					<button type="submit" class="submit-btn">Add Bill</button>

					<a href="bill-list" class="back-btn"> Back to Bills </a>

				</div>

			</form>

		</div>
	</div>

</body>
</html>