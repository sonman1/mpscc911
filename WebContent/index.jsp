<!doctype html>
<html lang="en-US">
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>
<title>Software Development Seminar</title>
<link href="styles/singlePageTemplate.css" rel="stylesheet"
	type="text/css">
<script>
	$(function() {
		$("#siteHeader").load("includes/header.html");
		$("#siteFooter").load("includes/footer.html");
	});
</script>
</head>
<body>

	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ page import="java.util.*, java.io.*"%>
	<%@ page import="java.io.*"%>

	<!-- Main Container -->
	<div class="container">
		<!-- Navigation -->
		<div id="siteHeader"></div>

		<!-- home Section -->
		<section class="home" id="home">
			<!--<h2 class="home_header">home <span class="subheading">subheading</span></h2>-->
			<p class="tagline">Home</p>
		</section>
		<!-- Register Section -->
		<section class="register" id="register">
			<article class="register_column">
				<form id="register_form" action="OrderCartServlet"
					method="post">
					<fieldset>
						<legend>
							<h3>Example Input</h3>
						</legend>
						<div class="input_contact_info">
							<table>
								<tr><h4>${msg}</h4></tr>
								<tr>
									<td align="right"><label>Name: <input type="text"
											name="name" required value='${name}'
											oninvalid="this.setCustomValidity('A name is required.')"
											oninput="this.setCustomValidity('')">
									</label></td>
								</tr>
								<tr>
									<td align="right"><label>E-mail: <input
											type="email" name="email" required value='${email}'
											oninvalid="this.setCustomValidity('A valid e-mail is required.')"
											oninput="this.setCustomValidity('')">
									</label></td>
								</tr>
							</table>
						</div>
					</fieldset>
					<input type="hidden" name="example" value="1"> <input
						type="submit" value="Example button">
				</form>
			</article>
			<h2 class="hidden">example</h2>
			<p class="register_column">Example input info.....</p>
			<p class="register_column">Example inout info.....</p>
		</section>

		<!-- Footer Section -->
		<div id="siteFooter"></div>
	</div>
	<!-- Main Container Ends -->
	<br>
</body>
</html>
