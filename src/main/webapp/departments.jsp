<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="kon.atha.HospitalManagement.Entity.Department"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Departments - Pranavi Health Care</title>

<style>
* {
	box-sizing: border-box;
}

body {
	margin: 0;
	font-family: Arial, Helvetica, sans-serif;
	background: #f6f3ff;
	color: #333;
}

/* Main Container */
.page-container {
	width: 92%;
	max-width: 1250px;
	margin: 0 auto;
	padding: 35px 0 50px;
}

/* Heading */
.page-header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 25px;
}

.title-section h1 {
	margin: 0;
	color: #4c1d95;
	font-size: 28px;
}

.title-section p {
	margin: 7px 0 0;
	color: #777;
	font-size: 14px;
}

/* Add Button */
.add-button {
	display: inline-block;
	background: #6d28d9;
	color: white;
	padding: 12px 20px;
	border-radius: 8px;
	text-decoration: none;
	font-size: 14px;
	font-weight: 600;
	transition: 0.2s;
}

.add-button:hover {
	background: #5b21b6;
}

/* Table Card */
.table-card {
	background: white;
	border-radius: 14px;
	padding: 20px;
	box-shadow: 0 8px 25px rgba(80, 50, 130, 0.10);
	border-top: 4px solid #6d28d9;
}

/* Table */
table {
	width: 100%;
	border-collapse: collapse;
	overflow: hidden;
}

th {
	background: #6d28d9;
	color: white;
	padding: 14px 12px;
	text-align: center;
	font-size: 14px;
	font-weight: 600;
}

td {
	padding: 13px 12px;
	text-align: center;
	border-bottom: 1px solid #eee7f8;
	font-size: 14px;
	color: #444;
}

tbody tr:nth-child(even) {
	background: #faf8ff;
}

tbody tr:hover {
	background: #f1ebff;
}

/* ID */
.id-cell {
	color: #6d28d9;
	font-weight: 600;
}

/* Action Buttons */
.action-buttons {
	display: flex;
	justify-content: center;
	gap: 8px;
}

.edit-button, .delete-button {
	padding: 7px 13px;
	border-radius: 6px;
	text-decoration: none;
	font-size: 13px;
	font-weight: 600;
	transition: 0.2s;
}

.edit-button {
	color: #5b21b6;
	background: #eee9f8;
}

.edit-button:hover {
	background: #ddd5ef;
}

.delete-button {
	color: #b91c1c;
	background: #fee2e2;
}

.delete-button:hover {
	background: #fecaca;
}

/* Responsive */
@media ( max-width : 700px) {
	.page-container {
		width: 95%;
	}
	.page-header {
		flex-direction: column;
		align-items: flex-start;
		gap: 15px;
	}
	.table-card {
		overflow-x: auto;
	}
	table {
		min-width: 650px;
	}
}
</style>

</head>

<body>

	<%@ include file="header.jspf"%>

	<div class="page-container">

		<div class="page-header">

			<div class="title-section">

				<h1>Department Management</h1>

				<p>Manage hospital departments and their locations</p>

			</div>

			<a class="add-button" href="add-department"> + Add New Department
			</a>

		</div>


		<div class="table-card">

			<table>

				<thead>

					<tr>
						<th>Department ID</th>
						<th>Department Name</th>
						<th>Location</th>
						<th>Action</th>
					</tr>

				</thead>

				<tbody>

					<%
					List<Department> departments = (List<Department>) request.getAttribute("departments");

					for (Department department : departments) {
					%>

					<tr>

						<td class="id-cell"><%=department.getDepartmentId()%></td>

						<td><%=department.getDepartmentName()%></td>

						<td><%=department.getLocation()%></td>

						<td>

							<div class="action-buttons">

								<a class="edit-button"
									href="edit-department?id=<%=department.getDepartmentId()%>">
									Edit </a> <a class="delete-button"
									href="delete-department?id=<%=department.getDepartmentId()%>"
									onclick="return confirm('Are you sure you want to delete this department?');">
									Delete </a>

							</div>

						</td>

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