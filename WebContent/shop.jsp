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
		ArrayList<Product> products = new ArrayList<Product>();
		Product product1 = new Product();
		product1.setId(1);
		product1.setName("Shirt");
		product1.setDescription("Best shirt to wear ever. It is hand-made and made out of pure silk. Great to wear" 
				+ " during the summer.");
		product1.setCostEach(30);
		product1.setQuantity(50);
		product1.setImage("https://static.boredpanda.com/blog/wp-content/uploads/2014/11/creative-t-shirts-29__605.jpg");
		
		Product product2 = new Product();
		product2.setId(2);
		product2.setName("Bag");
		product2.setDescription("This bag lightweight and very durable. Great of taking grocery shopping or for" 
				+ " just carrying things around.");
		product2.setCostEach(70);
		product2.setQuantity(40);
		product2.setImage("https://makerist-production.s3.amazonaws.com/uploads/pattern/10506/featured_image/zoom_featured_image.jpg");
		
		
		Product product3 = new Product();
		product3.setId(3);
		product3.setName("Coffee Mug");
		product3.setDescription("Great gift for your dad or mom. Sure to make any drink taste good and feel great.");
		product3.setCostEach(20);
		product3.setQuantity(10);
		product3.setImage("https://cremationdesigns.com/wp-content/uploads/2016/09/mug2-1200.jpg");
		
		Product product4 = new Product();
		product4.setId(4);
		product4.setName("Ring");
		product4.setDescription("This is a ring and it looks really good. You should wear it.");
		product4.setCostEach(100);
		product4.setQuantity(80);
		product4.setImage("https://cdn7.bigcommerce.com/s-77qyoan9x9/images/stencil/1280x1280/products/220/1449/morganite106_6__82230.1490349088.JPG?c=2&imbypass=on");
		
		
		products.add(product1);
		products.add(product2);
		products.add(product3);
		products.add(product4);
		
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