<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>Add Bed - Pranavi Health Care</title>

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
	min-height: calc(100vh - 70px);
	display: flex;
	justify-content: center;
	align-items: flex-start;
	padding: 40px 20px;
}

/* FORM CARD */
.form-card {
	width: 100%;
	max-width: 700px;
	background: white;
	border-radius: 14px;
	padding: 35px 40px;
	box-shadow: 0 6px 25px rgba(0, 0, 0, 0.08);
	border-top: 5px solid #6d28d9;
}

/* TITLE */
.form-title {
	text-align: center;
	margin-bottom: 30px;
}

.form-title h1 {
	margin: 0;
	color: #5b21b6;
	font-size: 28px;
}

.form-title p {
	margin-top: 8px;
	color: #777;
	font-size: 14px;
}

/* FORM */
.form-row {
	display: grid;
	grid-template-columns: 1fr 1fr;
	gap: 22px;
	margin-bottom: 20px;
}

.form-group {
	display: flex;
	flex-direction: column;
}

.form-group label {
	margin-bottom: 8px;
	color: #4c1d95;
	font-size: 14px;
	font-weight: 600;
}

.form-group input, .form-group select {
	width: 100%;
	padding: 12px 13px;
	border: 1px solid #d8c7f5;
	border-radius: 7px;
	background: white;
	color: #333;
	font-size: 14px;
	outline: none;
	transition: 0.2s;
}

.form-group input:focus, .form-group select:focus {
	border-color: #6d28d9;
	box-shadow: 0 0 0 3px rgba(109, 40, 217, 0.10);
}

/* BUTTONS */
.button-section {
	display: flex;
	justify-content: center;
	gap: 15px;
	margin-top: 30px;
}

.submit-btn {
	border: none;
	background: #6d28d9;
	color: white;
	padding: 12px 25px;
	border-radius: 7px;
	font-size: 14px;
	font-weight: 600;
	cursor: pointer;
	box-shadow: 0 4px 10px rgba(109, 40, 217, 0.25);
	transition: 0.3s;
}

.submit-btn:hover {
	background: #5b21b6;
}

.back-btn {
	text-decoration: none;
	background: #f1f1f1;
	color: #555;
	padding: 12px 25px;
	border-radius: 7px;
	font-size: 14px;
	font-weight: 600;
}

.back-btn:hover {
	background: #e5e5e5;
}

/* RESPONSIVE */
@media ( max-width : 700px) {
	.form-card {
		padding: 25px 20px;
	}
	.form-row {
		grid-template-columns: 1fr;
		gap: 18px;
	}
	.button-section {
		flex-direction: column;
	}
	.submit-btn, .back-btn {
		width: 100%;
		text-align: center;
	}
}
</style>

</head>

<body>

	<%@ include file="header.jspf"%>


	<div class="page-container">

		<div class="form-card">

			<div class="form-title">

				<h1>Add New Bed</h1>

				<p>Add a hospital bed and configure its availability</p>

			</div>


			<form action="add-bed" method="post">


				<div class="form-row">

					<div class="form-group">

						<label for="bedNumber"> Bed Number </label> <input type="text"
							id="bedNumber" name="bedNumber" placeholder="Example: BED-A101"
							required>

					</div>


					<div class="form-group">

						<label for="bedType"> Bed Type </label> <input type="text"
							id="bedType" name="bedType" placeholder="Example: General"
							required>

					</div>

				</div>


				<div class="form-row">

					<div class="form-group">

						<label for="chargePerDay"> Charge Per Day </label> <input
							type="number" id="chargePerDay" name="chargePerDay" step="0.01"
							min="0" placeholder="Enter daily charge" required>

					</div>


					<div class="form-group">

						<label for="status"> Status </label> <select id="status"
							name="status" required>

							<option value="">Select Status</option>

							<option value="Available">Available</option>

							<option value="Occupied">Occupied</option>

						</select>

					</div>

				</div>


				<div class="button-section">

					<button type="submit" class="submit-btn">Add Bed</button>

					<a href="beds" class="back-btn"> Back to Beds </a>

				</div>


			</form>

		</div>

	</div>

</body>

</html>