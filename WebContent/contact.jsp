<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Software Development Seminar</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"
	integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4"
	crossorigin="anonymous">
<link rel="stylesheet" href="styles/application.css">
<link rel="stylesheet" href="styles/bootstrap3panel.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"
	integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"
	integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm"
	crossorigin="anonymous"></script>
<script>
	$(function() {
		$("#siteHeader").load("includes/mainHeader.html");
	});
</script>
</head>
<body>

	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ page import="java.util.*, java.io.*"%>
	<%@ page import="java.io.*"%>

	<!-- Navigation -->
	<div id="siteHeader"></div>

	<div class="container-fluid">
		<h2>MPSCC</h2>

		<h4 style="padding-top: 10px;">About Us</h4>
		<div style="padding-top: 10px;">The Missouri Public Safety Conference is the
			premier event in Missouri for public safety communications officials
			working in 9-1-1, law enforcement, fire, EMS, and emergency operations
			centers. 
			<br><br>
			Attendees will participate in a four (4) day conference
			comprised of over 60 educational hours, chapter meetings, day and
			evening events and dedicated exclusive time with commercial partners.
			<br><br>
			Co-sponsored by: 
			<br>
				Missouri Association Public Safety Communication Officers <a class="nav-link"
					style="display: inline-table; padding: 0px;"
					href="http://moapco.org/">http://moapco.org/</a>
			<br>
				Missouri National Emergency Number Association <a class="nav-link"
					style="display: inline-table; padding: 0px;"
					href="http://monena.org/">http://moapco.org/</a>
			<br>
				Missouri 911 Directors Association <a class="nav-link"
					style="display: inline-table; padding: 0px;"
					href="http://mo911da.org/">http://mo911da.org/</a>
				
		</div>
		<div style="padding-top: 30px;">
			<p>
				You can learn more about us at <a class="nav-link"
					style="display: inline-table; padding: 0px;"
					href="http://mpscc911.org/">http://mpscc911.org/</a>
			</p>
		</div>
	</div>


</body>
</html>