<!doctype html>
<html lang="en-US">
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
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
	
	<!-- Login Section -->
	<div style="margin: auto;" class="col-xs-12 col-sm-7 col-md-5 col-lg-5">
		<div class="panel panel-info" >
			<div class="panel-heading">
          		<div class="panel-title">Sign In</div>
           	</div>     

            	<div class="panel-body" >
            	
            		<!-- Login Form -->
            		<form id="login_form" 
            			action="LoginServlet"
					method="post" 
					class="form-horizontal" 
					role="form">
					
            			<div class="input-group mb-3">
  						<div class="input-group-prepend">
    							<span class="input-group-text" id="username-icon"><i class="fa fa-user"></i></span>
  						</div>
  						<input type="text" required 
  							class="form-control" 
  							name="username"
  							placeholder="Username" 
  							aria-label="Username" 
  							aria-describedby="username-icon"
  							oninvalid="this.setCustomValidity('Username is required.')"
							oninput="this.setCustomValidity('')">
					</div>
					
					<div class="input-group mb-3">
  						<div class="input-group-prepend">
    							<span class="input-group-text" id="password-icon"><i class="fa fa-lock"></i></span>
  						</div>
  						<input type="password" required
  						class="form-control" 
  						name="password" 
  						placeholder="Password" 
  						aria-describedby="password-icon"
  						oninvalid="this.setCustomValidity('Password is required.')"
						oninput="this.setCustomValidity('')"
  						>
					</div>
            		
           			<div style="margin-top:10px" class="form-group">
           				<input type="hidden" name="action" value="login">
                			<input type="submit" class="btn btn-success col-xs-12 col-sm-12 col-md-12 col-lg-12" value="Login">
                		</div>
                		
						<h5>${msg}</h5>
                 <!-- Login Form Ends -->   
         		</form>  
         		
         		
         		<div class="form-group">
             		<div class="col-md-12">
             			<form id="register_from_login_form" 
            					action="LoginServlet"
							method="post" 
							class="form-horizontal" 
							role="form">
					
					
							<div style="border-top: 1px solid#888; padding-top:15px; font-size:85%" >
                       			If you do not have an account, please sign up
                       			<input type="hidden" name="action" value="register">  
                          		<a href="#" onclick="$(this).closest('form').submit(); return false;">
                               		here
                           		</a>
                           	</div>
                           
						</form>
                      </div>
                  </div>
        		</div>                     
     	</div> 
     	
     <!-- Login Section Ends --> 
   	</div>
	
	<br>
</body>
</html>
