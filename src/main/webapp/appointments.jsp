<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="kon.atha.HospitalManagement.Entity.Appointment"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>Appointment Management - Pranavi Health Care</title>

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
	min-width: 900px;
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

		<!-- =========================
         PAGE HEADER
         ========================= -->

		<div class="page-header">

			<div class="title-section">

				<h1>Appointment Management</h1>

				<p>Manage patient appointments and doctor consultations</p>

			</div>


			<a href="add-appointment" class="add-btn"> + Add New Appointment
			</a>

		</div>


		<!-- =========================
         TABLE
         ========================= -->

		<div class="table-card">

			<%
			List<Appointment> appointments = (List<Appointment>) request.getAttribute("appointments");
			%>


			<%
			if (appointments != null && !appointments.isEmpty()) {
			%>

			<table>

				<thead>

					<tr>

						<th>Appointment ID</th>

						<th>Date & Time</th>

						<th>Patient</th>

						<th>Doctor</th>

						<th>Reason</th>

						<th>Notes</th>

						<th>Action</th>

					</tr>

				</thead>


				<tbody>

					<%
					for (Appointment appointment : appointments) {

						String appointmentDate = "";

						if (appointment.getAppointmenDate() != null) {

							appointmentDate = appointment.getAppointmenDate().toString().replace("T", " ");
						}
					%>

					<tr>

						<!-- Appointment ID -->

						<td><%=appointment.getAppointmentid()%></td>


						<!-- Date & Time -->

						<td><%=appointmentDate%></td>


						<!-- Patient -->

						<td>
							<%
							if (appointment.getPatient() != null) {
							%> <%=appointment.getPatient().getFirstName()%>
							<%=appointment.getPatient().getLastName()%> <%
 } else {
 %> Not Assigned <%
 }
 %>

						</td>


						<!-- Doctor -->

						<td>
							<%
							if (appointment.getDoctor() != null) {
							%> Dr. <%=appointment.getDoctor().getFirstName()%>
							<%=appointment.getDoctor().getLastName()%> <%
 } else {
 %> Not Assigned <%
 }
 %>

						</td>


						<!-- Reason -->

						<td><%=appointment.getReason() != null ? appointment.getReason() : "-"%></td>


						<!-- Notes -->

						<td><%=appointment.getNotes() != null && !appointment.getNotes().isEmpty() ? appointment.getNotes() : "-"%></td>


						<!-- Actions -->

						<td>

							<div class="action-buttons">

								<a
									href="edit-appointment?id=<%=appointment.getAppointmentid()%>"
									class="edit-btn"> Edit </a> <a
									href="delete-appointment?id=<%=appointment.getAppointmentid()%>"
									class="delete-btn"
									onclick="return confirm('Are you sure you want to delete this appointment?');">
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

			<div class="empty-message">No appointments found.</div>

			<%
			}
			%>

		</div>

	</div>

</body>
</html>