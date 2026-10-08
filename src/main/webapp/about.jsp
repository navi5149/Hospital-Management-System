<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>About - Pranavi Health Care</title>

<style>
* {
	box-sizing: border-box;
}

body {
	margin: 0;
	font-family: Arial, Helvetica, sans-serif;
	background: #f8f6ff;
	color: #34205f;
}

.about-container {
	max-width: 1150px;
	margin: 50px auto;
	padding: 20px;
}

.about-card {
	background: white;
	border-radius: 18px;
	padding: 45px;
	box-shadow: 0 10px 35px rgba(52, 32, 95, 0.10);
	border: 1px solid #eee8fa;
}

.about-title {
	text-align: center;
	margin-bottom: 10px;
	color: #34205f;
	font-size: 34px;
}

.about-subtitle {
	text-align: center;
	color: #7657d9;
	font-size: 14px;
	letter-spacing: 1px;
	margin-bottom: 35px;
}

.about-content {
	display: grid;
	grid-template-columns: 1fr 1fr;
	gap: 35px;
	align-items: center;
}

.about-text h2 {
	color: #7657d9;
	margin-bottom: 15px;
}

.about-text p {
	color: #716b80;
	line-height: 1.8;
	font-size: 15px;
}

.about-image {
	width: 100%;
	height: 300px;
	overflow: hidden;
	border-radius: 15px;
}

.about-image img {
	width: 100%;
	height: 100%;
	object-fit: cover;
}

.info-boxes {
	margin-top: 35px;
	display: grid;
	grid-template-columns: repeat(3, 1fr);
	gap: 20px;
}

.info-box {
	background: #f8f6ff;
	border: 1px solid #e5ddfa;
	border-radius: 12px;
	padding: 25px;
	text-align: center;
}

.info-box h3 {
	color: #7657d9;
	margin-bottom: 10px;
}

.info-box p {
	color: #716b80;
	font-size: 13px;
	line-height: 1.6;
}

@media ( max-width : 800px) {
	.about-content {
		grid-template-columns: 1fr;
	}
	.info-boxes {
		grid-template-columns: 1fr;
	}
	.about-card {
		padding: 25px;
	}
}
</style>

</head>

<body>

	<%@ include file="header.jspf"%>

	<div class="about-container">

		<div class="about-card">

			<h1 class="about-title">About Pranavi Health Care</h1>

			<div class="about-subtitle">QUALITY CARE • ADVANCED TECHNOLOGY
				• PATIENT FIRST</div>

			<div class="about-content">

				<div class="about-text">

					<h2>Compassionate Healthcare</h2>

					<p>Pranavi Health Care is designed to provide reliable,
						efficient and patient-focused healthcare services.</p>

					<p>Our hospital management system helps manage patients,
						doctors, departments, appointments, admissions, beds,
						prescriptions, laboratory tests, billing and payments in an
						organized manner.</p>

					<p>We believe in combining compassionate care with modern
						technology to provide a better healthcare experience.</p>

				</div>

				<div class="about-image">

					<img
						src="${pageContext.request.contextPath}/images/pranavi-hospital.jpeg"
						alt="Pranavi Health Care">

				</div>

			</div>


			<div class="info-boxes">

				<div class="info-box">

					<h3>Expert Doctors</h3>

					<p>Experienced medical professionals providing quality
						healthcare.</p>

				</div>


				<div class="info-box">

					<h3>Advanced Care</h3>

					<p>Modern facilities and technology for better treatment.</p>

				</div>


				<div class="info-box">

					<h3>Patient First</h3>

					<p>Every decision is focused on patient comfort and care.</p>

				</div>

			</div>

		</div>

	</div>

</body>
</html>