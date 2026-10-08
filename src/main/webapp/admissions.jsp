<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="kon.atha.HospitalManagement.Entity.Admission"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Admission Management - Pranavi Health Care</title>

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

/* =========================
   PAGE CONTAINER
   ========================= */
.page-container {
	padding: 35px 45px;
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

.title-section h1 {
	margin: 0;
	color: #5b21b6;
	font-size: 30px;
	font-weight: 700;
}

.title-section p {
	margin-top: 8px;
	color: #777;
	font-size: 14px;
}

/* =========================
   ADD BUTTON
   ========================= */
.add-btn {
	text-decoration: none;
	background: #6d28d9;
	color: white;
	padding: 12px 20px;
	border-radius: 8px;
	font-size: 14px;
	font-weight: 600;
	box-shadow: 0 4px 10px rgba(109, 40, 217, 0.25);
	transition: 0.3s;
}

.add-btn:hover {
	background: #5b21b6;
	transform: translateY(-1px);
}

/* =========================
   TABLE CARD
   ========================= */
.table-card {
	background: white;
	border-radius: 12px;
	padding: 25px;
	box-shadow: 0 5px 20px rgba(0, 0, 0, 0.08);
	border-top: 4px solid #6d28d9;
	overflow-x: auto;
}

/* =========================
   TABLE
   ========================= */
table {
	width: 100%;
	border-collapse: collapse;
	min-width: 950px;
}

thead {
	background: #f1eafd;
}

th {
	padding: 15px 12px;
	text-align: left;
	color: #5b21b6;
	font-size: 14px;
	font-weight: 700;
	border-bottom: 2px solid #d8c7f5;
}

td {
	padding: 14px 12px;
	font-size: 14px;
	border-bottom: 1px solid #eee;
	color: #444;
}

tbody tr:nth-child(even) {
	background: #faf9fd;
}

tbody tr:hover {
	background: #f3edff;
}

/* =========================
   ACTION BUTTONS
   ========================= */
.action-buttons {
	display: flex;
	gap: 8px;
}

.edit-btn {
	text-decoration: none;
	background: #ede9fe;
	color: #5b21b6;
	padding: 7px 12px;
	border-radius: 6px;
	font-size: 13px;
	font-weight: 600;
}

.edit-btn:hover {
	background: #ddd6fe;
}

.delete-btn {
	text-decoration: none;
	background: #fee2e2;
	color: #dc2626;
	padding: 7px 12px;
	border-radius: 6px;
	font-size: 13px;
	font-weight: 600;
}

.delete-btn:hover {
	background: #fecaca;
}

/* =========================
   EMPTY MESSAGE
   ========================= */
.empty-message {
	text-align: center;
	padding: 30px;
	color: #777;
	font-size: 15px;
}

/* =========================
   RESPONSIVE
   ========================= */
@media ( max-width : 768px) {
	.page-container {
		padding: 25px 15px;
	}
	.page-header {
		flex-direction: column;
		align-items: flex-start;
		gap: 15px;
	}
	.title-section h1 {
		font-size: 25px;
	}
	.table-card {
		padding: 15px;
	}
}
</style>

</head>

<body>

	<%@ include file="header.jspf"%>


	<div class="page-container">

		<!-- PAGE HEADER -->

		<div class="page-header">

			<div class="title-section">

				<h1>Admission Management</h1>

				<p>Manage patient admissions, beds and discharge details</p>

			</div>


			<a href="add-admission" class="add-btn"> + Add New Admission </a>

		</div>


		<!-- TABLE -->

		<div class="table-card">

			<%
			List<Admission> admissions = (List<Admission>) request.getAttribute("admissions");
			%>


			<%
			if (admissions != null && !admissions.isEmpty()) {
			%>

			<table>

				<thead>

					<tr>

						<th>Admission ID</th>

						<th>Patient</th>

						<th>Bed</th>

						<th>Admission Date</th>

						<th>Discharge Date</th>

						<th>Reason</th>

						<th>Action</th>

					</tr>

				</thead>


				<tbody>

					<%
					for (Admission admission : admissions) {
					%>

					<tr>

						<!-- ID -->

						<td><%=admission.getAdmissionid()%></td>


						<!-- PATIENT -->

						<td>
							<%
							if (admission.getPatient() != null) {
							%> <%=admission.getPatient().getFirstName()%> <%=admission.getPatient().getLastName()%>

							<%
							} else {
							%> - <%
							}
							%>

						</td>


						<!-- BED -->

						<td>
							<%
							if (admission.getBed() != null) {
							%> <%=admission.getBed().getBedNumber()%> <%
 } else {
 %> - <%
 }
 %>

						</td>


						<!-- ADMISSION DATE -->

						<td><%=admission.getAdmissiondate() != null ? admission.getAdmissiondate().toString().replace("T", " ") : "-"%></td>


						<!-- DISCHARGE DATE -->

						<td><%=admission.getDischargedate() == null ? "-" : admission.getDischargedate().toString().replace("T", " ")%></td>


						<!-- REASON -->

						<td><%=admission.getReasons() != null ? admission.getReasons() : "-"%></td>


						<!-- ACTION -->

						<td>

							<div class="action-buttons">

								<a class="edit-btn"
									href="edit-admission?id=<%=admission.getAdmissionid()%>">
									Edit </a> <a class="delete-btn"
									href="delete-admission?id=<%=admission.getAdmissionid()%>"
									onclick="return confirm('Are you sure you want to delete this admission?');">
									Delete </a>

							</div>

						</td>

					</tr>

					<%
					}
					%>

				</tbody>

			</table>


			<%
			} else {
			%>

			<div class="empty-message">No admissions found.</div>

			<%
			}
			%>

		</div>

	</div>

</body>

</html>