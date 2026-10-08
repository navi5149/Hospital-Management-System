<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="kon.atha.HospitalManagement.Entity.TestOrder"%>
<%@ page import="kon.atha.HospitalManagement.Entity.Patient"%>
<%@ page import="kon.atha.HospitalManagement.Entity.LabTest"%>

<%
TestOrder testOrder = (TestOrder) request.getAttribute("testOrder");

List<Patient> patients = (List<Patient>) request.getAttribute("patients");

List<LabTest> labTests = (List<LabTest>) request.getAttribute("labTests");
%>

<%@ include file="header.jspf"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Edit Test Order | Pranavi Health Care</title>

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

/* Form Card */
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

/* Header */
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

/* Icon */
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

/* Form */
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

/* ID */
.order-id {
	background: #f5f3ff;
	color: #6d28d9;
	font-weight: bold;
}

/* Buttons */
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

/* Responsive */
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

				<h1>Edit Test Order</h1>

				<p>Update patient laboratory test order details</p>

			</div>


			<form action="edit-test-order" method="post">


				<!-- Hidden ID -->

				<input type="hidden" name="testOrderId"
					value="<%=testOrder.getTestOrderId()%>">


				<!-- Test Order ID -->

				<div class="form-group">

					<label>Test Order ID</label> <input type="text" class="order-id"
						value="<%=testOrder.getTestOrderId()%>" readonly>

				</div>


				<!-- Patient -->

				<div class="form-group">

					<label>Patient</label> <select name="patientId" required>

						<%
						if (patients != null && !patients.isEmpty()) {

							for (Patient p : patients) {

								boolean selected = testOrder.getPatient() != null && p.getPatientId() == testOrder.getPatient().getPatientId();
						%>

						<option value="<%=p.getPatientId()%>"
							<%=selected ? "selected" : ""%>>

							<%=p.getFirstName()%>
							<%=p.getLastName()%>

						</option>

						<%
						}

						}
						%>

					</select>

				</div>


				<!-- Lab Test -->

				<div class="form-group">

					<label>Lab Test</label> <select name="testId" required>

						<%
						if (labTests != null && !labTests.isEmpty()) {

							for (LabTest l : labTests) {

								boolean selected = testOrder.getLabTest() != null && l.getTestId() == testOrder.getLabTest().getTestId();
						%>

						<option value="<%=l.getTestId()%>" <%=selected ? "selected" : ""%>>

							<%=l.getTestName()%> - ₹<%=l.getPrice()%>

						</option>

						<%
						}

						}
						%>

					</select>

				</div>


				<!-- Order Date -->

				<div class="form-group">

					<label>Order Date</label> <input type="datetime-local"
						name="orderDate"
						value="<%=testOrder.getOrderDate() != null ? testOrder.getOrderDate().toString().substring(0, 16) : ""%>"
						required>

				</div>


				<!-- Remarks -->

				<div class="form-group">

					<label>Remarks</label>

					<textarea name="remarks" placeholder="Enter remarks"><%=testOrder.getRemarks() != null ? testOrder.getRemarks() : ""%></textarea>

				</div>


				<!-- Buttons -->

				<div class="button-row">

					<button type="submit">Update Test Order</button>

					<a href="test-orders" class="back-btn"> Back to Test Orders </a>

				</div>

			</form>

		</div>

	</div>

</body>

</html>