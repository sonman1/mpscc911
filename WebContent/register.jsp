<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Software Development Seminar</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
<link rel="stylesheet" href="styles/application.css">
<link rel="stylesheet" href="styles/bootstrap3panel.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>
<script>
	$(function() {
		$("#siteHeader").load("includes/header.html");
		//$("#siteFooter").load("includes/footer.html");
	});
</script>
</head>
<body>

	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ page import="java.util.*, java.io.*"%>
	<%@ page import="java.io.*"%>
	
	<!-- Navigation -->
	<div id="siteHeader"></div>
	
	<!-- Register Section -->
	<div style="margin: auto;" class="col-xs-12 col-sm-8 col-md-7 col-lg-6">
		<div class="panel panel-info" >
			<div class="panel-heading">
          		<div class="panel-title">Register</div>
           	</div>     

            	<div class="panel-body" >
            	
            		<!-- Register Form -->
            		<form id="register_form" 
            			action="OrderCartServlet"
					method="post" 
					class="form-horizontal" 
					role="form">
					
					<div class="form-group row">
						<label class="col-xs-3 col-sm-3 col-md-3 col-lg-3 control-label">First Name</label>
						<div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
							<input type="text" required
								class="form-control"
								name="firstname"
								placeholder="First Name"
								aria-label="First Name"
								oninvalid="this.setCustomValidity('First Name is required.')"
								oninput="this.setCustomValidity('')">
						</div>
					</div>
					
					<div class="form-group row">
						<label class="col-xs-3 col-sm-3 col-md-3 col-lg-3 control-label">Last Name</label>
						<div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
							<input type="text" required
								class="form-control"
								name="lastname"
								placeholder="Last Name"
								aria-label="Last Name"
								oninvalid="this.setCustomValidity('Last Name is required.')"
								oninput="this.setCustomValidity('')">
						</div>
					</div>
					
					<div class="form-group row">
						<label class="col-xs-3 col-sm-3 col-md-3 col-lg-3 control-label">Email</label>
						<div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
							<input type="email" required
								class="form-control"
								name="email"
								placeholder="Email"
								aria-label="Email"
								oninvalid="this.setCustomValidity('Email is required.')"
								oninput="this.setCustomValidity('')">
						</div>
					</div>
					
					<div class="form-group row">
						<label class="col-xs-3 col-sm-3 col-md-3 col-lg-3 control-label">Password</label>
						<div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
							<input type="password" required
								class="form-control"
								name="password"
								placeholder="Password"
								aria-label="Password"
								oninvalid="this.setCustomValidity('Password is required.')"
								oninput="this.setCustomValidity('')">
						</div>
					</div>
						
           			<div style="margin-top:10px" class="form-group">
           				<input type="hidden" name="action" value="register">
                			<input type="submit" class="btn btn-primary col-xs-12 col-sm-12 col-md-12 col-lg-12" value="Register">
                		</div>
                		
                  	
                 <!-- Register Form Ends -->   
         		</form>     
        		</div>                     
     	</div> 
     	
     <!-- Register Section Ends --> 
   	</div>

</body>
</html>