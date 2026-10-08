<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Doctors - Pranavi Health Care</title>

<style>
* {
	box-sizing: border-box;
}

body {
	margin: 0;
	font-family: Arial, Helvetica, sans-serif;
	background: #f7f5fc;
	color: #2d2340;
}

/* ================= PAGE CONTAINER ================= */
.page-container {
	max-width: 1450px;
	margin: 0 auto;
	padding: 35px 40px 50px;
}

/* ================= PAGE HEADER ================= */
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
	width: 52px;
	height: 52px;
	border-radius: 13px;
	background: #eee5ff;
	color: #6d28d9;
	display: flex;
	align-items: center;
	justify-content: center;
	font-size: 24px;
	box-shadow: 0 4px 10px rgba(109, 40, 217, 0.12);
}

.title-section h1 {
	margin: 0;
	color: #35156b;
	font-size: 30px;
}

.title-section p {
	margin: 5px 0 0;
	color: #777;
	font-size: 14px;
}

/* ================= ADD CARD ================= */
.add-card {
	background: white;
	border: 1px solid #e7ddf7;
	border-left: 5px solid #6d28d9;
	border-radius: 12px;
	padding: 18px 22px;
	display: flex;
	align-items: center;
	justify-content: space-between;
	margin-bottom: 25px;
	box-shadow: 0 5px 15px rgba(70, 30, 120, 0.08);
}

.add-card-text h3 {
	margin: 0;
	color: #35156b;
	font-size: 17px;
}

.add-card-text p {
	margin: 5px 0 0;
	color: #777;
	font-size: 13px;
}

.add-button {
	background: #6d28d9;
	color: white;
	padding: 11px 20px;
	border-radius: 8px;
	text-decoration: none;
	font-size: 14px;
	font-weight: bold;
	box-shadow: 0 4px 10px rgba(109, 40, 217, 0.25);
	transition: 0.2s;
}

.add-button:hover {
	background: #5521a8;
	transform: translateY(-1px);
}

/* ================= TABLE CARD ================= */
.table-card {
	background: white;
	border: 1px solid #d8c4f5;
	border-top: 4px solid #6d28d9;
	border-radius: 12px;
	overflow-x: auto;
	box-shadow: 0 6px 18px rgba(70, 30, 120, 0.08);
}

/* ================= TABLE ================= */
table {
	width: 100%;
	border-collapse: collapse;
	min-width: 1100px;
}

/* ================= HEADER ================= */
th {
	background: #6d28d9;
	color: white;
	padding: 14px 12px;
	text-align: left;
	font-size: 13px;
	font-weight: 600;
	border-right: 1px solid rgba(255, 255, 255, 0.25);
}

th:last-child {
	border-right: none;
}

/* ================= DATA ================= */
td {
	padding: 13px 12px;
	border-bottom: 1px solid #eee5f8;
	color: #4b4455;
	font-size: 13px;
	vertical-align: middle;
}

/* ================= ALTERNATE ROW ================= */
tbody tr:nth-child(even) {
	background: #faf8ff;
}

/* ================= HOVER ================= */
tbody tr:hover {
	background: #f0e8ff;
}

/* ================= ID ================= */
td:first-child {
	font-weight: bold;
	color: #6d28d9;
}

/* ================= EXPERIENCE ================= */
td:nth-child(8) {
	font-weight: bold;
	color: #6d28d9;
}

/* ================= ACTION ================= */
.action-buttons {
	display: flex;
	gap: 8px;
	align-items: center;
}

.edit-btn {
	background: #eee5ff;
	color: #6d28d9;
	padding: 7px 12px;
	border-radius: 6px;
	text-decoration: none;
	font-size: 12px;
	font-weight: bold;
}

.edit-btn:hover {
	background: #dfd0ff;
}

.delete-btn {
	background: #ffe7e7;
	color: #dc2626;
	padding: 7px 12px;
	border-radius: 6px;
	text-decoration: none;
	font-size: 12px;
	font-weight: bold;
}

.delete-btn:hover {
	background: #ffd2d2;
}

/* ================= RESPONSIVE ================= */
@media ( max-width : 800px) {
	.page-container {
		padding: 25px 18px;
	}
	.add-card {
		flex-direction: column;
		align-items: flex-start;
		gap: 15px;
	}
	.add-button {
		width: 100%;
		text-align: center;
	}
}

.person-icon {
	width: 16px;
	height: 16px;
	border: 3px solid #6d28d9;
	border-radius: 50%;
	position: relative;
	display: block;
	margin-top: -9px;
}

.person-icon::after {
	content: "";
	position: absolute;
	width: 24px;
	height: 12px;
	border: 3px solid #6d28d9;
	border-bottom: none;
	border-radius: 14px 14px 0 0;
	left: 50%;
	transform: translateX(-50%);
	top: 17px;
}
</style>

</head>


<body>


	<!-- NAVBAR -->

	<%@ include file="header.jspf"%>


	<div class="page-container">


		<!-- PAGE HEADER -->

		<div class="page-header">

			<div class="title-section">

				
				<div class="title-icon">
					<span class="person-icon"></span>
				</div>

				<div>

					<h1>Doctor Management</h1>

					<p>Manage and maintain hospital doctor records</p>

				</div>

			</div>

		</div>


		<!-- ADD DOCTOR -->

		<div class="add-card">

			<div class="add-card-text">

				<h3>Add New Doctor</h3>

				<p>Register a doctor and add their professional information.</p>

			</div>


			<a href="add-doctor" class="add-button"> + Add Doctor </a>

		</div>


		<!-- TABLE -->

		<div class="table-card">

			<table>

				<thead>

					<tr>

						<th>ID</th>

						<th>First Name</th>

						<th>Last Name</th>

						<th>Specialization</th>

						<th>Qualification</th>

						<th>Phone</th>

						<th>Email</th>

						<th>Experience</th>

						<th>Department</th>

						<th>Action</th>

					</tr>

				</thead>


				<tbody>

					<%
					java.util.List<kon.atha.HospitalManagement.Entity.Doctor> doctors = (java.util.List<kon.atha.HospitalManagement.Entity.Doctor>) request
							.getAttribute("doctors");

					for (kon.atha.HospitalManagement.Entity.Doctor d : doctors) {
					%>


					<tr>

						<td><%=d.getDoctorId()%></td>

						<td><%=d.getFirstName()%></td>

						<td><%=d.getLastName()%></td>

						<td><%=d.getSpecialization()%></td>

						<td><%=d.getQualification()%></td>

						<td><%=d.getPhone()%></td>

						<td><%=d.getEmail()%></td>

						<td><%=d.getExperience()%> years</td>

						<td><%=d.getDepartment() != null ? d.getDepartment().getDepartmentName() : "Not Assigned"%></td>

						<td>

							<div class="action-buttons">

								<a href="edit-doctor?id=<%=d.getDoctorId()%>" class="edit-btn">
									Edit </a> <a href="delete-doctor?id=<%=d.getDoctorId()%>"
									class="delete-btn"
									onclick="return confirm('Are you sure you want to delete this doctor?');">
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