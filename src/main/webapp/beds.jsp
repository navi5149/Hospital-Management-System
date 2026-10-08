<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="kon.atha.HospitalManagement.Entity.Bed"%>

<%
List<Bed> beds = (List<Bed>) request.getAttribute("beds");
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Bed Management - Pranavi Health Care</title>

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
	padding: 40px;
}

/* HEADER */
.page-header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 25px;
}

.title-section h1 {
	margin: 0;
	color: #5b21b6;
	font-size: 28px;
}

.title-section p {
	margin: 7px 0 0;
	color: #777;
	font-size: 14px;
}

/* TITLE ICON */
.title-row {
	display: flex;
	align-items: center;
	gap: 14px;
}

.title-icon {
	width: 48px;
	height: 48px;
	background: #f0e8ff;
	border-radius: 12px;
	display: flex;
	justify-content: center;
	align-items: center;
}

/* BED ICON */
.bed-icon {
	width: 26px;
	height: 14px;
	border: 3px solid #6d28d9;
	border-radius: 3px;
	position: relative;
}

.bed-icon::before {
	content: "";
	position: absolute;
	width: 8px;
	height: 7px;
	border: 2px solid #6d28d9;
	left: 2px;
	top: -10px;
	border-radius: 2px;
}

.bed-icon::after {
	content: "";
	position: absolute;
	width: 3px;
	height: 7px;
	background: #6d28d9;
	left: 3px;
	bottom: -9px;
	box-shadow: 17px 0 #6d28d9;
}

/* ADD BUTTON */
.add-btn {
	text-decoration: none;
	background: #6d28d9;
	color: white;
	padding: 12px 20px;
	border-radius: 7px;
	font-size: 14px;
	font-weight: 600;
	box-shadow: 0 4px 10px rgba(109, 40, 217, 0.22);
	transition: 0.3s;
}

.add-btn:hover {
	background: #5b21b6;
	transform: translateY(-1px);
}

/* TABLE CARD */
.table-card {
	background: white;
	border-radius: 12px;
	padding: 10px;
	box-shadow: 0 5px 20px rgba(0, 0, 0, 0.07);
	overflow-x: auto;
}

/* TABLE */
table {
	width: 100%;
	border-collapse: collapse;
}

th {
	background: #6d28d9;
	color: white;
	padding: 14px 12px;
	font-size: 14px;
	text-align: center;
	font-weight: 600;
}

td {
	padding: 13px 12px;
	border-bottom: 1px solid #eee;
	text-align: center;
	font-size: 14px;
}

tbody tr:nth-child(even) {
	background: #faf8ff;
}

tbody tr:hover {
	background: #f3edff;
}

/* STATUS */
.status {
	display: inline-block;
	padding: 5px 12px;
	border-radius: 20px;
	font-size: 12px;
	font-weight: 600;
}

.status-available {
	background: #e8f8ef;
	color: #16834b;
}

.status-occupied {
	background: #ffe9e9;
	color: #c62828;
}

/* ACTIONS */
.edit {
	display: inline-block;
	text-decoration: none;
	background: #f0e8ff;
	color: #6d28d9;
	padding: 7px 12px;
	border-radius: 5px;
	font-size: 12px;
	font-weight: 600;
	margin-right: 5px;
}

.edit:hover {
	background: #e2d2ff;
}

.delete {
	display: inline-block;
	text-decoration: none;
	background: #fff0f0;
	color: #d32f2f;
	padding: 7px 12px;
	border-radius: 5px;
	font-size: 12px;
	font-weight: 600;
}

.delete:hover {
	background: #ffe0e0;
}

/* EMPTY */
.empty-message {
	text-align: center;
	padding: 30px;
	color: #888;
}

/* RESPONSIVE */
@media ( max-width : 800px) {
	.page-container {
		padding: 25px 15px;
	}
	.page-header {
		flex-direction: column;
		align-items: flex-start;
		gap: 20px;
	}
	.add-btn {
		width: 100%;
		text-align: center;
	}
}
</style>

</head>

<body>

	<%@ include file="header.jspf"%>


	<div class="page-container">

		<div class="page-header">

			<div class="title-row">

				<div class="title-icon">
					<span class="bed-icon"></span>
				</div>

				<div class="title-section">

					<h1>Bed Management</h1>

					<p>Manage hospital beds, charges and availability</p>

				</div>

			</div>


			<a href="add-bed" class="add-btn"> + Add New Bed </a>

		</div>


		<div class="table-card">

			<table>

				<thead>

					<tr>
						<th>Bed ID</th>
						<th>Bed Number</th>
						<th>Bed Type</th>
						<th>Charge Per Day</th>
						<th>Status</th>
						<th>Action</th>
					</tr>

				</thead>

				<tbody>

					<%
					if (beds != null && !beds.isEmpty()) {

						for (Bed bed : beds) {
					%>

					<tr>

						<td><%=bed.getBedId()%></td>

						<td><strong> <%=bed.getBedNumber()%>
						</strong></td>

						<td><%=bed.getBedType()%></td>

						<td>₹<%=bed.getChargePerDay()%>
						</td>

						<td>
							<%
							if ("Available".equalsIgnoreCase(bed.getStatus())) {
							%> <span class="status status-available">
								Available </span> <%
 } else {
 %> <span class="status status-occupied">
								Occupied </span> <%
 }
 %>

						</td>

						<td><a class="edit" href="edit-bed?id=<%=bed.getBedId()%>">
								Edit </a> <a class="delete" href="delete-bed?id=<%=bed.getBedId()%>"
							onclick="return confirm('Are you sure you want to delete this bed?');">
								Delete </a></td>

					</tr>

					<%
					}

					} else {
					%>

					<tr>

						<td colspan="6" class="empty-message">No beds found.</td>

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