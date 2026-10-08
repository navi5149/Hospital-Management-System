<%@ page import="java.util.List"%>
<%@ page import="kon.atha.HospitalManagement.Entity.LabTest"%>

<%
List<LabTest> tests = (List<LabTest>) request.getAttribute("tests");
%>

<%@ include file="header.jspf"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>Lab Tests | Pranavi Health Care</title>

<style>
body {
	margin: 0;
	font-family: Arial, Helvetica, sans-serif;
	background: #f6f3ff;
	color: #2d1b4e;
}

.page-container {
	padding: 35px 45px;
}

/* Header */
.page-header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 25px;
}

.title-section {
	display: flex;
	align-items: center;
	gap: 15px;
}

.title-icon {
	width: 48px;
	height: 48px;
	background: #ede9fe;
	border-radius: 12px;
	display: flex;
	align-items: center;
	justify-content: center;
}

/* Lab Icon */
.lab-icon {
	width: 15px;
	height: 25px;
	border: 3px solid #6d28d9;
	border-top: none;
	border-radius: 3px 3px 7px 7px;
	position: relative;
}

.lab-icon::before {
	content: "";
	position: absolute;
	width: 21px;
	height: 3px;
	background: #6d28d9;
	left: 50%;
	transform: translateX(-50%);
	top: -4px;
	border-radius: 2px;
}

.lab-icon::after {
	content: "";
	position: absolute;
	width: 8px;
	height: 5px;
	background: #c4b5fd;
	left: 50%;
	transform: translateX(-50%);
	bottom: 4px;
	border-radius: 3px;
}

h1 {
	margin: 0;
	color: #4c1d95;
	font-size: 28px;
}

.subtitle {
	margin: 5px 0 0;
	color: #777;
	font-size: 14px;
}

/* Add Button */
.add-btn {
	background: #6d28d9;
	color: white;
	padding: 12px 20px;
	border-radius: 8px;
	text-decoration: none;
	font-weight: bold;
	transition: 0.3s;
}

.add-btn:hover {
	background: #4c1d95;
}

/* Table */
.table-card {
	background: white;
	border-radius: 12px;
	padding: 20px;
	box-shadow: 0 5px 18px rgba(76, 29, 149, 0.12);
	overflow-x: auto;
}

table {
	width: 100%;
	border-collapse: collapse;
}

thead {
	background: #6d28d9;
	color: white;
}

th {
	padding: 14px;
	text-align: left;
	font-size: 14px;
}

td {
	padding: 13px 14px;
	border-bottom: 1px solid #e5e7eb;
	font-size: 14px;
}

tbody tr:nth-child(even) {
	background: #faf8ff;
}

tbody tr:hover {
	background: #f3e8ff;
}

.id {
	font-weight: bold;
	color: #6d28d9;
}

/* Buttons */
.action-btn {
	display: inline-block;
	padding: 7px 12px;
	border-radius: 6px;
	text-decoration: none;
	font-size: 13px;
	margin-right: 5px;
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

/* Empty Message */
.empty-message {
	text-align: center;
	padding: 30px;
	color: #777;
	font-size: 15px;
}

/* Responsive */
@media ( max-width : 800px) {
	.page-container {
		padding: 25px 15px;
	}
	.page-header {
		flex-direction: column;
		align-items: flex-start;
		gap: 18px;
	}
}
</style>

</head>

<body>

	<div class="page-container">

		<div class="page-header">

			<div class="title-section">

				<div class="title-icon">
					<span class="lab-icon"></span>
				</div>

				<div>

					<h1>Lab Test Management</h1>

					<p class="subtitle">Manage laboratory tests, prices and
						descriptions</p>

				</div>

			</div>


			<a href="add-lab-test" class="add-btn"> + Add New Lab Test </a>

		</div>


		<div class="table-card">

			<table>

				<thead>

					<tr>

						<th>Test ID</th>
						<th>Test Name</th>
						<th>Price</th>
						<th>Description</th>
						<th>Actions</th>

					</tr>

				</thead>


				<tbody>

					<%
					if (tests != null && !tests.isEmpty()) {

						for (LabTest test : tests) {
					%>

					<tr>

						<td class="id">#<%=test.getTestId()%>
						</td>

						<td><%=test.getTestName()%></td>

						<td>₹<%=test.getPrice()%>
						</td>

						<td><%=test.getDescription() != null ? test.getDescription() : "N/A"%></td>

						<td><a class="action-btn edit-btn"
							href="edit-lab-test?id=<%=test.getTestId()%>"> Edit </a> <a
							class="action-btn delete-btn"
							href="delete-lab-test?id=<%=test.getTestId()%>"
							onclick="return confirm('Are you sure you want to delete this lab test?');">
								Delete </a></td>

					</tr>

					<%
					}

					} else {
					%>

					<tr>

						<td colspan="5" class="empty-message">No lab tests found.</td>

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