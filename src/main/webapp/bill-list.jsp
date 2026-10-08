<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="kon.atha.HospitalManagement.Entity.Bill"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>Bill Management - Pranavi Health Care</title>

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

/* =========================
   MAIN CONTENT
========================= */
.main-content {
	padding: 40px;
}

/* =========================
   PAGE HEADER
========================= */
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

/* =========================
   BILL ICON
========================= */
.title-icon {
	width: 48px;
	height: 48px;
	background: #ede9fe;
	border-radius: 12px;
	display: flex;
	justify-content: center;
	align-items: center;
}

.bill-icon {
	width: 22px;
	height: 27px;
	border: 3px solid #6d28d9;
	border-radius: 3px;
	position: relative;
}

.bill-icon::before {
	content: "";
	position: absolute;
	width: 10px;
	height: 3px;
	background: #6d28d9;
	top: 6px;
	left: 3px;
}

.bill-icon::after {
	content: "";
	position: absolute;
	width: 10px;
	height: 3px;
	background: #6d28d9;
	top: 13px;
	left: 3px;
}

/* =========================
   ADD BUTTON
========================= */
.add-button {
	background: #6d28d9;
	color: white;
	text-decoration: none;
	padding: 12px 20px;
	border-radius: 8px;
	font-size: 14px;
	font-weight: bold;
	transition: 0.2s;
}

.add-button:hover {
	background: #5521a8;
}

/* =========================
   TABLE CARD
========================= */
.table-card {
	background: white;
	border-radius: 14px;
	padding: 22px;
	box-shadow: 0 5px 20px rgba(80, 40, 130, 0.10);
	overflow-x: auto;
}

/* =========================
   TABLE
========================= */
table {
	width: 100%;
	border-collapse: collapse;
	min-width: 850px;
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

/* =========================
   AMOUNTS
========================= */
.amount {
	font-weight: bold;
	color: #4c1d95;
}

/* =========================
   ACTION BUTTONS
========================= */
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

/* =========================
   EMPTY MESSAGE
========================= */
.empty-message {
	text-align: center;
	padding: 35px;
	color: #777;
}

/* =========================
   RESPONSIVE
========================= */
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

		<!-- PAGE HEADER -->

		<div class="page-header">

			<div class="page-title">

				<div class="title-icon">
					<span class="bill-icon"></span>
				</div>

				<div>

					<h1>Bill Management</h1>

					<p>Manage patient bills and billing information</p>

				</div>

			</div>


			<a href="add-bill" class="add-button"> + Add New Bill </a>

		</div>


		<!-- TABLE -->

		<div class="table-card">

			<table>

				<thead>

					<tr>

						<th>Bill ID</th>

						<th>Bill Date</th>

						<th>Patient</th>

						<th>Total Amount</th>

						<th>Discount</th>

						<th>Net Amount</th>

						<th>Actions</th>

					</tr>

				</thead>


				<tbody>

					<%
					List<Bill> bills = (List<Bill>) request.getAttribute("bills");

					if (bills != null && !bills.isEmpty()) {

						for (Bill bill : bills) {
					%>


					<tr>

						<!-- BILL ID -->

						<td><%=bill.getBillId()%></td>


						<!-- BILL DATE -->

						<td>
							<%
							if (bill.getBillDate() != null) {
							%> <%=bill.getBillDate().toString().replace("T", " ")%> <%
 } else {
 %> - <%
 }
 %>

						</td>


						<!-- PATIENT -->

						<td>
							<%
							if (bill.getPatient() != null) {
							%> <%=bill.getPatient().getFirstName()%> <%=bill.getPatient().getLastName()%>

							<%
							} else {
							%> Not Assigned <%
							}
							%>

						</td>


						<!-- TOTAL AMOUNT -->

						<td class="amount">₹<%=bill.getTotalAmount() != null ? bill.getTotalAmount() : 0.0%>

						</td>


						<!-- DISCOUNT -->

						<td>₹<%=bill.getDiscount() != null ? bill.getDiscount() : 0.0%>

						</td>


						<!-- NET AMOUNT -->

						<td class="amount">₹<%=bill.getNetAmount() != null ? bill.getNetAmount() : 0.0%>

						</td>


						<!-- ACTIONS -->

						<td>

							<div class="action-buttons">

								<a href="edit-bill?id=<%=bill.getBillId()%>" class="edit-btn">
									Edit </a> <a href="delete-bill?id=<%=bill.getBillId()%>"
									class="delete-btn"
									onclick="return confirm('Are you sure you want to delete this bill?');">
									Delete </a>

							</div>

						</td>

					</tr>


					<%
					}

					} else {
					%>


					<tr>

						<td colspan="7" class="empty-message">No bills found.</td>

					</tr>


					<%
					}
					%>

				</tbody>

			</table>

		</div>

	</div>

</body>

</html>s