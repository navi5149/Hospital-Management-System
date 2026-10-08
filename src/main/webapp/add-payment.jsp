<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="kon.atha.HospitalManagement.Entity.Bill"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>Add Payment - Pranavi Health Care</title>

<style>
* {
	box-sizing: border-box;
}

body {
	margin: 0;
	font-family: Arial, Helvetica, sans-serif;
	background: #f6f3fb;
	color: #2d2140;
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
	font-size: 14px;
}

.form-group {
	margin-bottom: 20px;
}

.form-group label {
	display: block;
	margin-bottom: 8px;
	font-weight: bold;
	color: #4b3869;
	font-size: 14px;
}

.form-group input, .form-group select {
	width: 100%;
	padding: 12px 13px;
	border: 1px solid #d8cfea;
	border-radius: 7px;
	font-size: 14px;
	outline: none;
}

.form-group input:focus, .form-group select:focus {
	border-color: #6d28d9;
	box-shadow: 0 0 0 3px #ede9fe;
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

.submit-btn:hover {
	background: #5521a8;
}

.back-btn {
	text-decoration: none;
	background: #eee9f8;
	color: #4c1d95;
	padding: 12px 22px;
	border-radius: 7px;
	font-weight: bold;
}

.back-btn:hover {
	background: #ddd6fe;
}

@media ( max-width : 768px) {
	.main-content {
		padding: 20px;
	}
	.form-card {
		width: 100%;
		padding: 25px;
	}
}
</style>

</head>

<body>

	<%@ include file="header.jspf"%>

	<div class="main-content">

		<div class="form-card">

			<div class="form-title">

				<h1>Add New Payment</h1>

				<p>Record a payment for a patient bill</p>

			</div>

			<%
			List<Bill> bills = (List<Bill>) request.getAttribute("bills");
			%>

			<form action="add-payment" method="post">

				<div class="form-group">

					<label>Bill</label> <select name="billId" required>

						<option value="">Select Bill</option>

						<%
						if (bills != null && !bills.isEmpty()) {

							for (Bill b : bills) {
						%>

						<option value="<%=b.getBillId()%>">Bill #<%=b.getBillId()%>
							-
							<%=b.getPatient() != null ? b.getPatient().getFirstName() + " " + b.getPatient().getLastName()
		: "Unknown Patient"%> - &#8377;<%=b.getNetAmount()%>

						</option>

						<%
						}

						}
						%>

					</select>

				</div>


				<div class="form-group">

					<label>Payment Amount</label> <input type="number" name="amount"
						step="0.01" min="0.01" placeholder="Enter payment amount" required>

				</div>


				<div class="form-group">

					<label>Payment Mode</label> <select name="paymentMode" required>

						<option value="">Select Payment Mode</option>

						<option value="Cash">Cash</option>

						<option value="UPI">UPI</option>

						<option value="Card">Card</option>

						<option value="Net Banking">Net Banking</option>

					</select>

				</div>


				<div class="form-group">

					<label>Transaction ID</label> <input type="text"
						name="transactionId" placeholder="Enter transaction ID">

				</div>


				<div class="button-row">

					<button type="submit" class="submit-btn">Add Payment</button>

					<a href="payments" class="back-btn"> Back to Payments </a>

				</div>

			</form>

		</div>

	</div>

</body>
</html>