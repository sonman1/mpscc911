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
	$(function(){
	    $('.carousel').carousel({
	      interval: 4000
	    });
	});
</script>
</head>
<body>
	
	<!-- Navigation -->
	<div id="siteHeader"></div>
	
	<!-- Home Page -->
	<div class="container-fluid">
		<div id="carouselPictures" class="carousel slide" data-ride="carousel">
  			<ol class="carousel-indicators">
    				<li data-target="#carouselPictures" data-slide-to="0" class="active"></li>
    				<li data-target="#carouselPictures" data-slide-to="1"></li>
    				<li data-target="#carouselPictures" data-slide-to="2"></li>
  			</ol>
  			<div class="carousel-inner">
    				<div class="carousel-item active">
      				<img class="d-block w-100" 
      					src="http://mpscc911.org/wp-content/uploads/2018/04/banner-2018-fall-2-1024x275.jpg" alt="First slide"
      					style=" width:100%; height: 250px !important;">
    				</div>
    				<div class="carousel-item">
     				<img class="d-block w-100" 
     					src="http://mpscc911.org/wp-content/uploads/2017/03/open-colors-1024x683.jpg" alt="Second slide"
     					style=" width:100%; height: 250px !important;">
    				</div>
    				<div class="carousel-item">
      				<img class="d-block w-100" 
      					src="http://mpscc911.org/wp-content/uploads/photo-gallery/2017_Fall/DSC_0722.JPG" alt="Third slide"
      					style=" width:100%; height: 250px !important;">
    				</div>
  			</div>
  			<a class="carousel-control-prev" href="#carouselPictures" role="button" data-slide="prev">
   				<span class="carousel-control-prev-icon" aria-hidden="true"></span>
    				<span class="sr-only">Previous</span>
  			</a>
  			<a class="carousel-control-next" href="#carouselPictures" role="button" data-slide="next">
    				<span class="carousel-control-next-icon" aria-hidden="true"></span>
    				<span class="sr-only">Next</span>
  			</a>
		</div>
		
		<div style="padding-top: 30px;"></div>
		
		<h3>Events</h3>
		
		<div id="accordion">
  			<div class="card">
    				<div class="card-header" id="event1">
     				<h5 class="mb-0">
     					<span class="badge badge-success">April 19, 2018</span>
        					<button class="btn btn-link" data-toggle="collapse" data-target="#collapseEvent1" aria-expanded="true" aria-controls="collapseEvent1">
          					2018 Fall MPSCC Call for Papers is Open
        					</button>
      				</h5>
    				</div>

    				<div id="collapseEvent1" class="collapse show" aria-labelledby="event1" data-parent="#accordion">
     	 			<div class="card-body">
     	 				Call for papers is now open! Missouri Public Safety Communications Conference Training Symposium 2018 MPSCC Fall TS Call For Papers Fillable  DEADLINE:  June 29, 2018 @ 1700 
      				</div>
    				</div>
  			</div>
  			<div class="card">
    				<div class="card-header" id="event2">
      				<h5 class="mb-0">
      					<span class="badge badge-success">February 16, 2018</span>
        					<button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseEvent2" aria-expanded="false" aria-controls="collapseEvent2">
          					Final Training Matrix Released!
        					</button>
      				</h5>
    				</div>
    				<div id="collapseEvent2" class="collapse" aria-labelledby="event2" data-parent="#accordion">
      				<div class="card-body">
      					http://mpscc911.org/agenda/
      				</div>
    				</div>
  			</div>
  			<div class="card">
    				<div class="card-header" id="event3">
      				<h5 class="mb-0">
      					<span class="badge badge-success">December 22, 2017</span>
        					<button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseEvent3" aria-expanded="false" aria-controls="collapseEvent3">
          					Pre-Conference Training: Disability Awareness for Telecommunicators
        					</button>
      				</h5>
    				</div>
    				<div id="collapseEvent3" class="collapse" aria-labelledby="event3" data-parent="#accordion">
      				<div class="card-body">
      					8 Hours Continuing Education – FREE – BUT registration is required.   Register online! Download the Flyer! Disability Awareness for Telecommunicators
      				</div>
   	 			</div>
  			</div>
		</div>
		
		
		<div style="padding-top: 30px;"></div>
		
	</div>
	
	
</body>
</html>