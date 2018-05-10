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
	<%@ page import="business.OrderCart, business.OrderLineItem"%>
	<%
// 		OrderLineItem oli1 = new OrderLineItem();
// 		oli1.setCost(30);
// 		oli1.setItem("Shirt");
// 		oli1.setQuantity(2);
		
// 		OrderLineItem oli2 = new OrderLineItem();
// 		oli2.setCost(70);
// 		oli2.setItem("Bag");
// 		oli2.setQuantity(5);
		
// 		OrderLineItem oli3 = new OrderLineItem();
// 		oli3.setCost(20);
// 		oli3.setItem("Coffee Mug");
// 		oli3.setQuantity(10);
		
// 		OrderLineItem oli4 = new OrderLineItem();
// 		oli4.setCost(100);
// 		oli4.setItem("Ring");
// 		oli4.setQuantity(1);
		
// 		OrderCart orderCart = new OrderCart();
		
// 		ArrayList<OrderLineItem> orderLineItems = new ArrayList<OrderLineItem>();
// 		orderLineItems.add(oli1);
// 		orderLineItems.add(oli2);
// 		orderLineItems.add(oli3);
// 		orderLineItems.add(oli4);
		
// 		orderCart.setItems(orderLineItems);
		
// 		pageContext.setAttribute("orderCart", orderCart);
		
		
		OrderCart orderCart = (OrderCart) session.getAttribute("orderCart");
		pageContext.setAttribute("orderCart", orderCart);
	%>
	
	
	<!-- Navigation -->
	<div id="siteHeader"></div>
	
	<!-- Cart Items -->
	<div class="container-fluid">
		<c:if test="${not empty orderCart.items}">
			<table class="table table-striped col-xs-12">
				<thead class="thead-dark">
					<tr>
						<th>Item</th>
						<th>Quantity</th>
						<th>Cost</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="orderLineItem" items="${orderCart.items}">
						<tr>
							<td><c:out value="${orderLineItem.item}" /></td>
							<td>
								<form action="OrderCartServlet" method="POST" style="display: inline-table;">
									<input type="hidden" name="action" value="orderSubtract" />
									<input type="hidden" name="itemString" value="${orderLineItem.item}" />
									<input type="hidden" name="quantityString" value="${orderLineItem.quantity - 1}" />
									<button class="btn btn-danger" type="submit"><i class="fa fa-minus"></i></button>
								</form>
								<div style="display: inline-table; width: 30px; text-align: center;">
										<c:out value="${orderLineItem.quantity}" />
								</div>
								<form action="OrderCartServlet" method="POST" style="display: inline-table;">
									<input type="hidden" name="action" value="orderAdd" />
									<input type="hidden" name="itemString" value="${orderLineItem.item}" />
									<input type="hidden" name="quantityString" value="${orderLineItem.quantity + 1}" />
									<button class="btn btn-primary" type="submit"><i class="fa fa-plus"></i></button>
								</form>
							</td>
							<td>
								<div style="display: inline-table; width: 100px; text-align: left;">
									<c:out value="${orderLineItem.getTotalCurrencyFormat()}" />
								</div>
								<form action="OrderCartServlet" method="POST" style="display: inline-table;">
									<input type="hidden" name="action" value=orderCart />
									<input type="hidden" name="removeItem" value="${orderLineItem.item}" />
									<button class="btn btn-danger" type="submit"><i class="fa fa-times"></i></button>
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		
			<div>
				<h3 style="display: inline-table;">Total Cost
					<div class="badge badge-success">
						<c:out value="${orderCart.getTotalCurrencyFormat()}" />
					</div> 
				</h3>
				<form action="OrderCartServlet" method="POST" style="display: inline-table; float: right;">
					<input type="hidden" name="action" value="checkout" />
					<button class="btn btn-primary" type="submit">Checkout</button>
				</form>
						<h5>${msg}</h5>
			</div>
		</c:if>
		<c:if test="${empty orderCart.items}">
			<h2 class="jumbotron" style="text-align: center;">Your Cart is Empty</h2>
		</c:if>
	</div>
	
	
</body>
</html>