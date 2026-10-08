<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>Pranavi Health Care</title>

<style>

/* =========================
   GENERAL
========================= */
* {
	box-sizing: border-box;
}

html, body {
	margin: 0;
	padding: 0;
	width: 100%;
	min-height: 100%;
}

body {
	font-family: Arial, Helvetica, sans-serif;
	background: #ffffff;
	color: #30254d;
}

/* =========================
   HERO
========================= */
.hero {
	width: 100%;
	min-height: 500px;
	display: grid;
	grid-template-columns: 45% 55%;
	background: #f8f6ff;
	overflow: hidden;
	position: relative;
}

/* =========================
   HERO CONTENT
========================= */
.hero-content {
	display: flex;
	flex-direction: column;
	justify-content: center;
	padding: 55px 40px 55px 70px;
	position: relative;
	z-index: 2;
}

.welcome-text {
	color: #7657d9;
	font-size: 14px;
	font-weight: 700;
	letter-spacing: 1.5px;
	margin-bottom: 15px;
}

.hero-title {
	margin: 0;
	font-size: 52px;
	line-height: 1.08;
	font-weight: 700;
	color: #34205f;
}

.hero-title span {
	color: #7657d9;
}

.hero-description {
	margin-top: 20px;
	max-width: 480px;
	color: #716b80;
	font-size: 15px;
	line-height: 1.7;
}

/* =========================
   BUTTONS
========================= */
.hero-buttons {
	display: flex;
	gap: 15px;
	margin-top: 28px;
}

.primary-btn {
	display: inline-block;
	text-decoration: none;
	background: #7657d9;
	color: white;
	padding: 14px 23px;
	border-radius: 7px;
	font-size: 13px;
	font-weight: 600;
	transition: 0.3s;
}

.primary-btn:hover {
	background: #5c40b5;
	transform: translateY(-2px);
}

.secondary-btn {
	display: inline-block;
	text-decoration: none;
	color: #7657d9;
	background: white;
	border: 1px solid #d8cff2;
	padding: 14px 23px;
	border-radius: 7px;
	font-size: 13px;
	font-weight: 600;
	transition: 0.3s;
}

.secondary-btn:hover {
	background: #f1edff;
}

/* =========================
   HOSPITAL IMAGE
========================= */
.hero-image {
	width: 100%;
	min-height: 500px;
	position: relative;
	overflow: hidden;
}

.hero-image img {
	width: 100%;
	height: 100%;
	min-height: 500px;
	object-fit: cover;
	display: block;
}

/* LIGHT PURPLE IMAGE OVERLAY */
.hero-image::after {
	content: "";
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background: rgba(248, 246, 255, 0.25);
	pointer-events: none;
}

/* LEFT IMAGE BLEND */
.hero-image::before {
	content: "";
	position: absolute;
	left: 0;
	top: 0;
	width: 130px;
	height: 100%;
	background: linear-gradient(to right, #f8f6ff, rgba(248, 246, 255, 0));
	z-index: 2;
	pointer-events: none;
}

/* =========================
   SERVICE STRIP
========================= */
.service-strip {
	width: calc(100% - 100px);
	margin: -50px auto 0;
	position: relative;
	z-index: 5;
	background: white;
	border-radius: 14px;
	box-shadow: 0 8px 30px rgba(60, 40, 100, 0.12);
	display: grid;
	grid-template-columns: repeat(4, 1fr);
	overflow: hidden;
}

.service-box {
	padding: 22px;
	display: flex;
	align-items: center;
	gap: 14px;
	border-right: 1px solid #eee8fa;
}

.service-box:last-child {
	border-right: none;
}

.service-icon {
	width: 48px;
	height: 48px;
	flex-shrink: 0;
	border-radius: 50%;
	background: #f0ecff;
	color: #7657d9;
	display: flex;
	align-items: center;
	justify-content: center;
	font-size: 18px;
	font-weight: bold;
}

.service-box h4 {
	margin: 0 0 5px;
	font-size: 13px;
	color: #3f2a78;
}

.service-box p {
	margin: 0;
	color: #858091;
	font-size: 10px;
	line-height: 1.5;
}

/* =========================
   DASHBOARD INFORMATION
========================= */
.dashboard-section {
	max-width: 1300px;
	margin: 65px auto;
	padding: 0 30px;
}

.dashboard-heading {
	text-align: center;
	margin-bottom: 35px;
}

.dashboard-heading h2 {
	margin: 0;
	font-size: 30px;
	color: #34205f;
}

.dashboard-heading p {
	margin-top: 8px;
	color: #817b91;
	font-size: 14px;
}

/* =========================
   STATISTICS
========================= */
.stats-grid {
	display: grid;
	grid-template-columns: repeat(4, 1fr);
	gap: 20px;
}

/* =========================
   STAT CARD
========================= */
.stat-card {
	background: white;
	border: 1px solid #ece8f5;
	border-radius: 13px;
	padding: 22px;
	min-height: 135px;
	box-shadow: 0 5px 18px rgba(52, 32, 95, 0.06);
	transition: 0.25s;
}

.stat-card:hover {
	transform: translateY(-5px);
	border-color: #d8cff4;
	box-shadow: 0 12px 28px rgba(52, 32, 95, 0.12);
}

.card-top {
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.card-title {
	color: #716b80;
	font-size: 14px;
	font-weight: 600;
}

.card-icon {
	width: 42px;
	height: 42px;
	border-radius: 10px;
	background: #f0ecff;
	color: #7657d9;
	display: flex;
	align-items: center;
	justify-content: center;
	font-size: 18px;
	font-weight: bold;
}

.card-number {
	margin-top: 18px;
	color: #7657d9;
	font-size: 30px;
	font-weight: 700;
}

/* =========================
   FOOTER
========================= */
.footer {
	margin-top: 70px;
	padding: 28px;
	text-align: center;
	background: #34205f;
	color: white;
	font-size: 12px;
}

/* =========================
   RESPONSIVE
========================= */
@media ( max-width : 1100px) {
	.hero {
		grid-template-columns: 1fr;
	}
	.hero-content {
		padding: 50px;
	}
	.hero-image {
		min-height: 400px;
	}
	.hero-image img {
		min-height: 400px;
	}
	.stats-grid {
		grid-template-columns: repeat(3, 1fr);
	}
}

@media ( max-width : 800px) {
	.service-strip {
		grid-template-columns: 1fr 1fr;
	}
	.service-box {
		border-bottom: 1px solid #eee8fa;
	}
	.stats-grid {
		grid-template-columns: repeat(2, 1fr);
	}
}

@media ( max-width : 500px) {
	.hero-content {
		padding: 40px 25px;
	}
	.hero-title {
		font-size: 36px;
	}
	.service-strip {
		width: calc(100% - 30px);
		grid-template-columns: 1fr;
	}
	.stats-grid {
		grid-template-columns: 1fr;
	}
}
</style>

</head>


<body>


	<!-- =========================
     NAVBAR
========================= -->

	<%@ include file="header.jspf"%>


	<!-- =========================
     HERO SECTION
========================= -->

	<section class="hero">


		<!-- LEFT CONTENT -->

		<div class="hero-content">

			<div class="welcome-text">WELCOME TO PRANAVI HEALTH CARE</div>


			<h1 class="hero-title">

				Your Health,<br> <span>Our Priority</span>

			</h1>


			<p class="hero-description">Compassionate care, advanced
				technology and dedicated medical professionals working together for
				a healthier tomorrow.</p>


			<div class="hero-buttons">

				<a href="appointments" class="primary-btn"> Book Appointment </a> <a
					href="#services" class="secondary-btn"> Explore Services </a>

			</div>

		</div>



		<!-- RIGHT HOSPITAL IMAGE -->

		<div class="hero-image">

			<img
				src="${pageContext.request.contextPath}/images/pranavi-hospital.jpeg"
				alt="Pranavi Health Care Hospital">

		</div>


	</section>



	<!-- =========================
     SERVICES
========================= -->

	<section class="service-strip" id="services">


		<div class="service-box">

			<div class="service-icon">D</div>

			<div>

				<h4>Expert Doctors</h4>

				<p>Experienced specialists providing quality care.</p>

			</div>

		</div>



		<div class="service-box">

			<div class="service-icon">+</div>

			<div>

				<h4>Advanced Care</h4>

				<p>Modern healthcare facilities for better treatment.</p>

			</div>

		</div>



		<div class="service-box">

			<div class="service-icon">24</div>

			<div>

				<h4>24/7 Services</h4>

				<p>Round-the-clock healthcare whenever you need it.</p>

			</div>

		</div>



		<div class="service-box">

			<div class="service-icon">P</div>

			<div>

				<h4>Patient First</h4>

				<p>Compassionate care focused on every patient.</p>

			</div>

		</div>


	</section>






	<!-- =========================
     FOOTER
========================= -->

	<footer class="footer"> © 2026 Pranavi Health Care | Quality
		Care • Advanced Technology • Patient First </footer>


</body>

</html>