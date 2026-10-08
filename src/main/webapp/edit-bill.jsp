<%@ page import="java.util.List"%>
<%@ page import="kon.atha.HospitalManagement.Entity.Bill"%>
<%@ page import="kon.atha.HospitalManagement.Entity.Patient"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Bill - Pranavi Health Care</title>

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
}

.form-title p {
	color: #777;
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

.readonly {
	background: #f5f3f8;
}

.net-amount {
	background: #f3edff;
	border: 1px solid #ddd6fe;
	border-radius: 8px;
	padding: 15px;
	margin-bottom: 20px;
}

.net-amount strong {
	color: #4c1d95;
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

	<%
	Bill bill = (Bill) request.getAttribute("bill");

	List<Patient> patients = (List<Patient>) request.getAttribute("patients");
	%>

	<div class="main-content">

		<div class="form-card">

			<%
			if (bill != null) {
			%>

			<div class="form-title">
				<h1>Edit Bill</h1>
				<p>Update billing information</p>
			</div>

			<form action="edit-bill" method="post">

				<input type="hidden" name="billId" value="<%=bill.getBillId()%>">

				<div class="form-group">

					<label>Patient</label> <select name="patientId" required>

						<%
						if (patients != null) {

							for (Patient patient : patients) {

								boolean selected = bill.getPatient() != null && bill.getPatient().getPatientId() == patient.getPatientId();
						%>

						<option value="<%=patient.getPatientId()%>"
							<%=selected ? "selected" : ""%>>

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

					<label>Bill Date</label> <input type="text" class="readonly"
						value="<%=bill.getBillDate() != null ? bill.getBillDate().toString().replace("T", " ") : "-"%>"
						readonly>

				</div>

				<div class="form-group">

					<label>Total Amount</label> <input type="number" name="totalAmount"
						step="0.01" min="0"
						value="<%=bill.getTotalAmount() != null ? bill.getTotalAmount() : 0.0%>"
						required>

				</div>

				<div class="form-group">

					<label>Discount</label> <input type="number" name="discount"
						step="0.01" min="0"
						value="<%=bill.getDiscount() != null ? bill.getDiscount() : 0.0%>"
						required>

				</div>

				<div class="net-amount">

					Current Net Amount: <strong> ₹<%=bill.getNetAmount() != null ? bill.getNetAmount() : 0.0%>
					</strong>

				</div>

				<div class="button-row">

					<button type="submit" class="submit-btn">Update Bill</button>

					<a href="bill-list" class="back-btn"> Back to Bills </a>

				</div>

			</form>

			<%
			} else {
			%>

			<div class="form-title">
				<h1>Bill Not Found</h1>
				<p>The requested bill could not be found.</p>
			</div>

			<a href="bill-list" class="back-btn"> Back to Bills </a>

			<%
			}
			%>

		</div>
	</div>

</body>
</html>