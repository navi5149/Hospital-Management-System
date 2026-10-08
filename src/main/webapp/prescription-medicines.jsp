<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page
	import="kon.atha.HospitalManagement.Entity.PrescriptionMedicine"%>

<%
List<PrescriptionMedicine> medicines = (List<PrescriptionMedicine>) request.getAttribute("medicines");
%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>Prescription Medicines - Pranavi Health Care</title>

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

.title-row {
	display: flex;
	align-items: center;
	gap: 14px;
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
.title-icon {
	width: 48px;
	height: 48px;
	background: #f0e8ff;
	border-radius: 12px;
	display: flex;
	justify-content: center;
	align-items: center;
}

/* MEDICINE ICON */
.medicine-icon {
	width: 27px;
	height: 13px;
	border: 3px solid #6d28d9;
	border-radius: 10px;
	transform: rotate(-45deg);
	position: relative;
}

.medicine-icon::after {
	content: "";
	position: absolute;
	width: 3px;
	height: 18px;
	background: #6d28d9;
	left: 10px;
	top: -5px;
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

/* PRESCRIPTION ID */
.prescription-id {
	color: #6d28d9;
	font-weight: 600;
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
					<span class="medicine-icon"></span>
				</div>

				<div class="title-section">

					<h1>Prescription Medicines</h1>

					<p>Manage medicines, dosage and treatment duration</p>

				</div>

			</div>


			<a href="add-prescription-medicine" class="add-btn"> + Add New
				Medicine </a>

		</div>


		<div class="table-card">

			<table>

				<thead>

					<tr>

						<th>ID</th>
						<th>Prescription ID</th>
						<th>Medicine Name</th>
						<th>Dosage</th>
						<th>Duration</th>
						<th>Actions</th>

					</tr>

				</thead>


				<tbody>

					<%
					if (medicines != null && !medicines.isEmpty()) {

						for (PrescriptionMedicine pm : medicines) {
					%>

					<tr>

						<td><strong> <%=pm.getId()%>
						</strong></td>


						<td>
							<%
							if (pm.getPrescription() != null) {
							%> <span class="prescription-id"> #<%=pm.getPrescription().getPrescriptionId()%>
						</span> <%
 } else {
 %> - <%
 }
 %>

						</td>


						<td><strong> <%=pm.getMedicineName()%>
						</strong></td>


						<td><%=pm.getDosage()%></td>


						<td><%=pm.getDuration()%></td>


						<td><a class="edit"
							href="edit-prescription-medicine?id=<%=pm.getId()%>"> Edit </a> <a
							class="delete"
							href="delete-prescription-medicine?id=<%=pm.getId()%>"
							onclick="return confirm('Are you sure you want to delete this medicine?');">
								Delete </a></td>

					</tr>

					<%
					}

					} else {
					%>

					<tr>

						<td colspan="6" class="empty-message">No prescription
							medicines found.</td>

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