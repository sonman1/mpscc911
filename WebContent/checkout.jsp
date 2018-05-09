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

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
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
	
	<!-- Payment Section -->
	<div style="margin: auto;" class="col-xs-12 col-sm-9 col-md-9 col-lg-9">
		<div class="panel panel-info" >
			<div class="panel-heading">
          		<div class="panel-title">
          			Payment Info
          			<div>
          				<i class"fa fa-cc-visa"></i>
          				<i class"fa fa-cc-discover"></i>
          				<i class"fa fa-cc-amex"></i>
          				<i class"fa fa-cc-mastercard"></i>
          			</div>
          		</div>
           	</div>     

            	<div class="panel-body" >
            	
            		<!-- Payment Form -->
            		<form id="payment_form" 
            			action="OrderServlet"
					method="post" 
					class="" 
					role="form">
					
					<div class="form-row">
            				<div class="form-group col-md-9">
            					<label>Credit Card #</label>
  							<input type="text" required 
  								class="form-control" 
  								name="creditCardNumber"
  								placeholder="Enter Credit Card Number" 
  								oninvalid="this.setCustomValidity('Credit Card Number is required.')"
								oninput="this.setCustomValidity('')">
						</div>
					
						<div class="form-group col-md-3">
            					<label>Expiration Date</label>
  							<input type="month" required 
  								class="form-control" 
  								name="expirationDate"
  								oninvalid="this.setCustomValidity('Expiration Date is required.')"
								oninput="this.setCustomValidity('')">
						</div>
					</div>
					
					<div class="form-group">
    						<label>Address</label>
    						<input type="text" required
    							class="form-control"
    							name="addressStreet"
    							placeholder="Enter Address"
    							oninvalid="this.setCustomValidity('Address is required.')"
							oninput="this.setCustomValidity('')">
  					</div>
  					
  					<div class="form-row">
  						<div class="form-group col-md-6">
            					<label>City</label>
  							<input type="text" required 
  								class="form-control" 
  								name="addressCity"
  								placeholder="Enter City"
  								oninvalid="this.setCustomValidity('City is required.')"
								oninput="this.setCustomValidity('')">
						</div>
						
						<div class="form-group col-md-4">
            					<label>State</label>
  							<input type="text" required 
  								class="form-control" 
  								name="addressState"
  								placeholder="Enter State"
  								oninvalid="this.setCustomValidity('State is required.')"
								oninput="this.setCustomValidity('')">
						</div>
						
						<div class="form-group col-md-2">
            					<label>Zip Code</label>
  							<input type="text" required 
  								class="form-control" 
  								name="addressZipCode"
  								placeholder="Enter Zip Code"
  								oninvalid="this.setCustomValidity('Zip Code is required.')"
								oninput="this.setCustomValidity('')">
						</div>
  					</div>
					
            		
           			<div style="margin-top:10px" class="form-group">
           				<input type="hidden" name="action" value="payment">
                			<input type="submit" class="btn btn-success col-xs-12 col-sm-12 col-md-12 col-lg-12" value="Pay">
                		</div>
                		
                 <!-- Payment Ends -->   
         		</form>  
         
        		</div>                     
     	</div>
	</div>
	
	
	
</body>
</html>