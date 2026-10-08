<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="kon.atha.HospitalManagement.Entity.Payment"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>Payment Management - Pranavi Health Care</title>

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
	padding: 40px;
}

.page-header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 25px;
}

.page-title {
	display: flex;
	align-items: center;
	gap: 14px;
}

.page-title h1 {
	margin: 0;
	color: #4c1d95;
	font-size: 30px;
}

.page-title p {
	margin: 6px 0 0;
	color: #777;
	font-size: 14px;
}

.title-icon {
	width: 48px;
	height: 48px;
	background: #ede9fe;
	border-radius: 12px;
	display: flex;
	justify-content: center;
	align-items: center;
}

.payment-icon {
	width: 25px;
	height: 18px;
	border: 3px solid #6d28d9;
	border-radius: 3px;
	position: relative;
}

.payment-icon::before {
	content: "";
	position: absolute;
	width: 19px;
	height: 3px;
	background: #6d28d9;
	top: 4px;
	left: 0;
}

.payment-icon::after {
	content: "";
	position: absolute;
	width: 6px;
	height: 3px;
	background: #6d28d9;
	bottom: 3px;
	right: 3px;
}

.add-button {
	background: #6d28d9;
	color: white;
	text-decoration: none;
	padding: 12px 20px;
	border-radius: 8px;
	font-size: 14px;
	font-weight: bold;
}

.add-button:hover {
	background: #5521a8;
}

.table-card {
	background: white;
	border-radius: 14px;
	padding: 22px;
	box-shadow: 0 5px 20px rgba(80, 40, 130, 0.10);
	overflow-x: auto;
}

table {
	width: 100%;
	border-collapse: collapse;
	min-width: 950px;
}

thead {
	background: #6d28d9;
	color: white;
}

th {
	padding: 15px 12px;
	text-align: left;
	font-size: 13px;
}

td {
	padding: 14px 12px;
	border-bottom: 1px solid #eee;
	font-size: 14px;
}

tbody tr:nth-child(even) {
	background: #faf8ff;
}

tbody tr:hover {
	background: #f3edff;
}

.amount {
	font-weight: bold;
	color: #4c1d95;
}

.action-buttons {
	display: flex;
	gap: 8px;
}

.edit-btn, .delete-btn {
	text-decoration: none;
	padding: 7px 12px;
	border-radius: 6px;
	font-size: 12px;
	font-weight: bold;
}

.edit-btn {
	background: #ede9fe;
	color: #5b21b6;
}

.edit-btn:hover {
	background: #ddd6fe;
}

.delete-btn {
	background: #fee2e2;
	color: #b91c1c;
}

.delete-btn:hover {
	background: #fecaca;
}

.empty-message {
	text-align: center;
	padding: 35px;
	color: #777;
}

@media ( max-width : 768px) {
	.main-content {
		padding: 20px;
	}
	.page-header {
		flex-direction: column;
		align-items: flex-start;
		gap: 20px;
	}
}
</style>

</head>

<body>

	<%@ include file="header.jspf"%>

	<div class="main-content">

		<div class="page-header">

			<div class="page-title">

				<div class="title-icon">
					<span class="payment-icon"></span>
				</div>

				<div>

					<h1>Payment Management</h1>

					<p>Manage patient payments and transactions</p>

				</div>

			</div>

			<a href="add-payment" class="add-button"> + Add New Payment </a>

		</div>


		<div class="table-card">

			<table>

				<thead>

					<tr>
						<th>Payment ID</th>
						<th>Payment Date</th>
						<th>Bill ID</th>
						<th>Patient</th>
						<th>Amount</th>
						<th>Payment Mode</th>
						<th>Transaction ID</th>
						<th>Actions</th>
					</tr>

				</thead>

				<tbody>

					<%
					List<Payment> payments = (List<Payment>) request.getAttribute("payments");

					if (payments != null && !payments.isEmpty()) {

						for (Payment p : payments) {
					%>

					<tr>

						<td><%=p.getPaymentId()%></td>

						<td><%=p.getPaymentDate() != null ? p.getPaymentDate().toString().replace("T", " ") : "-"%></td>

						<td><%=p.getBill() != null ? p.getBill().getBillId() : "-"%></td>

						<td><%=p.getBill() != null && p.getBill().getPatient() != null
		? p.getBill().getPatient().getFirstName() + " " + p.getBill().getPatient().getLastName()
		: "Not Assigned"%></td>

						<td class="amount">&#8377;<%=p.getAmount() != null ? p.getAmount() : 0.0%>

						</td>

						<td><%=p.getPaymentMode() != null ? p.getPaymentMode() : "-"%></td>

						<td><%=p.getTransactionId() != null ? p.getTransactionId() : "-"%></td>

						<td>

							<div class="action-buttons">

								<a href="edit-payment?id=<%=p.getPaymentId()%>" class="edit-btn">
									Edit </a> <a href="delete-payment?id=<%=p.getPaymentId()%>"
									class="delete-btn"
									onclick="return confirm('Are you sure you want to delete this payment?');">
									Delete </a>

							</div>

						</td>

					</tr>

					<%
					}

					} else {
					%>

					<tr>

						<td colspan="8" class="empty-message">No payments found.</td>

					</tr>

					<%
					}
					%>

				</tbody>

			</table>

		</div>

	</div>

</body>
</html>