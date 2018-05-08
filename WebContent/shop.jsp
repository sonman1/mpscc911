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
		$("#siteHeader").load("includes/mainHeader.html");
	});
</script>
</head>
<body>

	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ page import="java.util.*, java.io.*"%>
	<%@ page import="java.io.*"%>
	<%@ page import="business.Product"%>
	<%
		ArrayList<Product> products = (ArrayList<Product>) session.getAttribute("allProducts");
		pageContext.setAttribute("products", products);
	%>
	
	<!-- Navigation -->
	<div id="siteHeader"></div>
	
	<!-- Shop Items -->
	<div class="container-fluid">
	<div class="row">
	<c:if test="${not empty products}">
		<c:forEach var="product" items="${products}">
		
			<div class="container py-3">
    				<div class="card">
     				 <div class="row ">
        					<div class="col-md-4">
            					<img src="${product.image}" class="w-100" style="max-height: 300px;">
          				</div>
          				<div class="col-md-8 px-3">
            					<div class="card-block px-3">
            						<h3 class="card-title" style="padding-top: 20px;"><c:out value="${product.name}" /></h5>
              					<p class="card-text"><c:out value="${product.description}" /></p>
              					<h4 class="card-title">$<c:out value="${product.costEach}" /></h4>
              					<form action="OrderCartServlet" method="POST">
									<input type="hidden" name="action" value="addToCart" />
									<input type="hidden" name="productId" value="${product.id}" />
									<button class="btn btn-primary" type="submit">Add To Cart</button>
								</form>
            					</div>
          				</div>
        				</div>
      			</div>
    			</div>
		</c:forEach>
	</c:if>
	</div>
	</div>
	
	
</body>
</html>