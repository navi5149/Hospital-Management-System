<%@ include file="header.jspf"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>Add Lab Test | Pranavi Health Care</title>

<style>
body {
	margin: 0;
	font-family: Arial, Helvetica, sans-serif;
	background: #f6f3ff;
	color: #2d1b4e;
}

/* Page */
.page-container {
	padding: 40px 20px;
}

/* Form Card */
.form-card {
	width: 520px;
	max-width: 100%;
	margin: 20px auto;
	background: white;
	border-radius: 14px;
	padding: 30px 35px;
	box-sizing: border-box;
	box-shadow: 0 6px 22px rgba(76, 29, 149, 0.12);
	border-top: 5px solid #6d28d9;
}

/* Header */
.form-header {
	text-align: center;
	margin-bottom: 25px;
}

.form-header h1 {
	margin: 0;
	color: #4c1d95;
	font-size: 27px;
}

.form-header p {
	margin-top: 7px;
	color: #777;
	font-size: 14px;
}

/* Lab Test Icon */
.title-icon {
	width: 52px;
	height: 52px;
	background: #ede9fe;
	border-radius: 14px;
	margin: 0 auto 15px;
	display: flex;
	align-items: center;
	justify-content: center;
}

.lab-icon {
	width: 16px;
	height: 25px;
	border: 3px solid #6d28d9;
	border-top: none;
	border-radius: 3px 3px 7px 7px;
	position: relative;
}

.lab-icon::before {
	content: "";
	position: absolute;
	width: 22px;
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

/* Form */
.form-group {
	margin-bottom: 18px;
}

label {
	display: block;
	margin-bottom: 7px;
	font-weight: bold;
	color: #4c1d95;
	font-size: 14px;
}

input, textarea {
	width: 100%;
	padding: 11px 12px;
	border: 1px solid #d8ccf3;
	border-radius: 7px;
	box-sizing: border-box;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 14px;
	outline: none;
	transition: 0.2s;
}

input:focus, textarea:focus {
	border-color: #6d28d9;
	box-shadow: 0 0 0 3px #ede9fe;
}

textarea {
	height: 110px;
	resize: vertical;
}

/* Buttons */
.button-row {
	display: flex;
	gap: 12px;
	margin-top: 25px;
}

button {
	flex: 1;
	padding: 12px;
	border: none;
	border-radius: 7px;
	background: #6d28d9;
	color: white;
	font-weight: bold;
	cursor: pointer;
	font-size: 14px;
	transition: 0.3s;
}

button:hover {
	background: #4c1d95;
}

.back-btn {
	flex: 1;
	padding: 12px;
	border-radius: 7px;
	background: #ede9fe;
	color: #5b21b6;
	text-decoration: none;
	text-align: center;
	font-weight: bold;
	box-sizing: border-box;
	font-size: 14px;
}

.back-btn:hover {
	background: #ddd6fe;
}

/* Responsive */
@media ( max-width : 600px) {
	.page-container {
		padding: 25px 15px;
	}
	.form-card {
		padding: 25px 20px;
	}
	.button-row {
		flex-direction: column;
	}
}
</style>

</head>

<body>

	<div class="page-container">

		<div class="form-card">

			<div class="form-header">

				<div class="title-icon">
					<span class="lab-icon"></span>
				</div>

				<h1>Add Lab Test</h1>

				<p>Add a new laboratory test to the hospital system</p>

			</div>


			<form action="add-lab-test" method="post">

				<div class="form-group">

					<label>Test Name</label> <input type="text" name="testName"
						placeholder="Enter test name" required>

				</div>


				<div class="form-group">

					<label>Price</label> <input type="number" name="price" step="0.01"
						min="0" placeholder="Enter test price" required>

				</div>


				<div class="form-group">

					<label>Description</label>

					<textarea name="description" placeholder="Enter test description"></textarea>

				</div>


				<div class="button-row">

					<button type="submit">Save Lab Test</button>

					<a href="lab-tests" class="back-btn"> Back to Lab Tests </a>

				</div>

			</form>

		</div>

	</div>

</body>
</html>