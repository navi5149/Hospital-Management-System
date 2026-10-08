<%@ page import="java.util.List"%>
<%@ page import="kon.atha.HospitalManagement.Entity.Prescription"%>

<%
List<Prescription> prescriptions = (List<Prescription>) request.getAttribute("prescriptions");
%>

<%@ include file="header.jspf"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>Prescriptions | Pranavi Health Care</title>

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
	width: 45px;
	height: 45px;
	background: #ede9fe;
	border-radius: 12px;
	display: flex;
	align-items: center;
	justify-content: center;
}

/* Prescription icon */
.prescription-icon {
	width: 20px;
	height: 26px;
	border: 3px solid #6d28d9;
	border-radius: 3px;
	position: relative;
}

.prescription-icon::before {
	content: "";
	position: absolute;
	width: 12px;
	height: 3px;
	background: #6d28d9;
	left: 2px;
	top: 6px;
}

.prescription-icon::after {
	content: "";
	position: absolute;
	width: 12px;
	height: 3px;
	background: #6d28d9;
	left: 2px;
	top: 14px;
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

/* Table Card */
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

.empty-message {
	text-align: center;
	padding: 30px;
	color: #777;
	font-size: 15px;
}
</style>

</head>

<body>

	<div class="page-container">

		<div class="page-header">

			<div class="title-section">

				<div class="title-icon">
					<span class="prescription-icon"></span>
				</div>

				<div>
					<h1>Prescription Management</h1>
					<p class="subtitle">Manage patient prescriptions and treatment
						details</p>
				</div>

			</div>

			<a href="add-prescription" class="add-btn"> + Add New
				Prescription </a>

		</div>


		<div class="table-card">

			<table>

				<thead>

					<tr>
						<th>Prescription ID</th>
						<th>Prescription Date</th>
						<th>Appointment ID</th>
						<th>Patient</th>
						<th>Doctor</th>
						<th>Notes</th>
						<th>Actions</th>
					</tr>

				</thead>

				<tbody>

					<%
					if (prescriptions != null && !prescriptions.isEmpty()) {

						for (Prescription prescription : prescriptions) {

							String prescriptionDate = "";

							if (prescription.getPrescriptionDate() != null) {
						prescriptionDate = prescription.getPrescriptionDate().toString().replace("T", " ");
							}
					%>

					<tr>

						<td class="id">#<%=prescription.getPrescriptionId()%>
						</td>

						<td><%=prescriptionDate%></td>

						<td>
							<%
							if (prescription.getAppointment() != null) {
							%> #<%=prescription.getAppointment().getAppointmentid()%>
							<%
							} else {
							%> N/A <%
							}
							%>
						</td>

						<td>
							<%
							if (prescription.getAppointment() != null && prescription.getAppointment().getPatient() != null) {

								out.print(prescription.getAppointment().getPatient().getFirstName() + " "
								+ prescription.getAppointment().getPatient().getLastName());

							} else {
								out.print("N/A");
							}
							%>
						</td>

						<td>
							<%
							if (prescription.getAppointment() != null && prescription.getAppointment().getDoctor() != null) {

								out.print(prescription.getAppointment().getDoctor().getFirstName() + " "
								+ prescription.getAppointment().getDoctor().getLastName());

							} else {
								out.print("N/A");
							}
							%>
						</td>

						<td><%=prescription.getNotes() != null ? prescription.getNotes() : "N/A"%></td>

						<td><a
							href="edit-prescription?id=<%=prescription.getPrescriptionId()%>"
							class="action-btn edit-btn"> Edit </a> <a
							href="delete-prescription?id=<%=prescription.getPrescriptionId()%>"
							class="action-btn delete-btn"
							onclick="return confirm('Are you sure you want to delete this prescription?');">
								Delete </a></td>

					</tr>

					<%
					}

					} else {
					%>

					<tr>
						<td colspan="7" class="empty-message">No prescriptions found.
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