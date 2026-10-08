<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="kon.atha.HospitalManagement.Entity.Patient"%>
<%@ page import="kon.atha.HospitalManagement.Entity.LabTest"%>

<%@ include file="header.jspf"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Add Test Order | Pranavi Health Care</title>

<style>
body {
	margin: 0;
	font-family: Arial, Helvetica, sans-serif;
	background: #f6f3ff;
	color: #2d1b4e;
}

.page-container {
	padding: 40px 20px;
}

/* FORM CARD */
.form-card {
	width: 560px;
	max-width: 100%;
	margin: 20px auto;
	background: white;
	border-radius: 14px;
	padding: 30px 35px;
	box-sizing: border-box;
	box-shadow: 0 6px 22px rgba(76, 29, 149, 0.12);
	border-top: 5px solid #6d28d9;
}

/* HEADER */
.form-header {
	text-align: center;
	margin-bottom: 25px;
}

.form-header h1 {
	margin: 0;
	color: #4c1d95;
	font-size: 27px;
}

.form-header p {
	margin-top: 7px;
	color: #777;
	font-size: 14px;
}

/* ICON */
.title-icon {
	width: 52px;
	height: 52px;
	background: #ede9fe;
	border-radius: 14px;
	margin: 0 auto 15px;
	display: flex;
	align-items: center;
	justify-content: center;
}

.test-icon {
	width: 22px;
	height: 26px;
	border: 3px solid #6d28d9;
	border-radius: 4px;
	position: relative;
	box-sizing: border-box;
}

.test-icon::before {
	content: "";
	position: absolute;
	width: 12px;
	height: 3px;
	background: #6d28d9;
	left: 50%;
	transform: translateX(-50%);
	top: 6px;
	border-radius: 2px;
}

.test-icon::after {
	content: "";
	position: absolute;
	width: 12px;
	height: 3px;
	background: #c4b5fd;
	left: 50%;
	transform: translateX(-50%);
	top: 14px;
	border-radius: 2px;
}

/* FORM */
.form-group {
	margin-bottom: 18px;
}

label {
	display: block;
	margin-bottom: 7px;
	font-weight: bold;
	color: #4c1d95;
	font-size: 14px;
}

input, select, textarea {
	width: 100%;
	padding: 11px 12px;
	border: 1px solid #d8ccf3;
	border-radius: 7px;
	box-sizing: border-box;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 14px;
	outline: none;
	background: white;
	transition: 0.2s;
}

input:focus, select:focus, textarea:focus {
	border-color: #6d28d9;
	box-shadow: 0 0 0 3px #ede9fe;
}

textarea {
	height: 110px;
	resize: vertical;
}

/* BUTTONS */
.button-row {
	display: flex;
	gap: 12px;
	margin-top: 25px;
}

button {
	flex: 1;
	padding: 12px;
	border: none;
	border-radius: 7px;
	background: #6d28d9;
	color: white;
	font-weight: bold;
	cursor: pointer;
	font-size: 14px;
	transition: 0.3s;
}

button:hover {
	background: #4c1d95;
}

.back-btn {
	flex: 1;
	padding: 12px;
	border-radius: 7px;
	background: #ede9fe;
	color: #5b21b6;
	text-decoration: none;
	text-align: center;
	font-weight: bold;
	box-sizing: border-box;
	font-size: 14px;
}

.back-btn:hover {
	background: #ddd6fe;
}

/* RESPONSIVE */
@media ( max-width : 600px) {
	.page-container {
		padding: 25px 15px;
	}
	.form-card {
		padding: 25px 20px;
	}
	.button-row {
		flex-direction: column;
	}
}
</style>

</head>

<body>

	<div class="page-container">

		<div class="form-card">

			<div class="form-header">

				<div class="title-icon">
					<span class="test-icon"></span>
				</div>

				<h1>Add Test Order</h1>

				<p>Create a new laboratory test order for a patient</p>

			</div>


			<!-- IMPORTANT: ADD URL -->
			<form action="add-test-order" method="post">


				<!-- PATIENT -->

				<div class="form-group">

					<label>Patient</label> <select name="patientId" required>

						<option value="">-- Select Patient --</option>

						<%
						List<Patient> patients = (List<Patient>) request.getAttribute("patients");

						if (patients != null && !patients.isEmpty()) {

							for (Patient p : patients) {
						%>

						<option value="<%=p.getPatientId()%>">

							<%=p.getFirstName()%>
							<%=p.getLastName()%>

						</option>

						<%
						}

						}
						%>

					</select>

				</div>


				<!-- LAB TEST -->

				<div class="form-group">

					<label>Lab Test</label> <select name="testId" required>

						<option value="">-- Select Lab Test --</option>

						<%
						List<LabTest> labTests = (List<LabTest>) request.getAttribute("labTests");

						if (labTests != null && !labTests.isEmpty()) {

							for (LabTest l : labTests) {
						%>

						<option value="<%=l.getTestId()%>">

							<%=l.getTestName()%> - ₹<%=l.getPrice()%>

						</option>

						<%
						}

						}
						%>

					</select>

				</div>


				<!-- ORDER DATE -->

				<div class="form-group">

					<label>Order Date</label> <input type="datetime-local"
						name="orderDate" required>

				</div>


				<!-- REMARKS -->

				<div class="form-group">

					<label>Remarks</label>

					<textarea name="remarks" placeholder="Enter remarks"></textarea>

				</div>


				<!-- BUTTONS -->

				<div class="button-row">

					<button type="submit">Add Test Order</button>

					<a href="test-orders" class="back-btn"> Back to Test Orders </a>

				</div>

			</form>

		</div>

	</div>

</body>

</html>