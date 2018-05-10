package dispatcher;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import business.OrderLineItem;
import business.Payment;
import business.Product;
import business.Order;
import business.OrderCart;
import business.User;
import data.OrderDB;
import data.PaymentDB;
import data.ProductDB;

/**
 * Servlet implementation class OrderCartServlet
 * 
 * @version 1.0 2018-04-05
 * @author Sonny Saxton
 */
@WebServlet(description = "OrderCartServlet", urlPatterns = { "/OrderCartServlet" })
public class OrderCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderCartServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		// get session
		HttpSession session = request.getSession();

		// get current action
		String action = request.getParameter("action");
		System.out.println("Action ===> " + action);

		if (action == null) {
			action = "orderCart";
		}

		String url = "/index.jsp";

		 if (action.contains("orderCart")) {

			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String username = request.getParameter("username");
			String email = request.getParameter("email");
			String productId = request.getParameter("productId");
			String itemString = request.getParameter("itemString");
			String quantityString = request.getParameter("quantityString");
			String removeItem = request.getParameter("removeItem");

			System.out.println("firstName: " + firstName);
			System.out.println("lastName: " + lastName);
			System.out.println("username: " + username);
			System.out.println("email: " + email);
			// System.out.println("products: " + products);
			System.out.println("productId: " + productId);
			System.out.println("itemString: " + itemString);
			System.out.println("quantityString: " + quantityString);
			System.out.println("removeItem: " + removeItem);

			// OrderCart
			int quantity; // default value

			OrderCart orderCart = (OrderCart) session.getAttribute("orderCart");

			// load cart or create new cart if needed
			if (orderCart == null) {
				orderCart = new OrderCart();
			}

			// parse quantityString to int and handle invalid values
			try {
				quantity = Integer.parseInt(quantityString);
				System.out.println("quantity: " + quantity);
				if (quantity < 0) {
					quantity = 1;
				}
			} catch (NumberFormatException nfe) {
				quantity = 1; // default value
			}

			if (productId != null) {

				// lookup product
				System.out.println("looking up product productId: " + productId);
				Product product = (Product) ProductDB.selectProductById(productId);

				// add cart item and cost
				OrderLineItem lineItem = new OrderLineItem();
				lineItem.setItem(product.getName());
				lineItem.setQuantity(quantity);
				lineItem.setCost(product.getCostEach());
				lineItem.setProduct(product);

				// check to see if adding or removing items
				if (quantity > 0) {
					// check if item has already been added to order cart
					if (orderCart.containsItem(product.getName()) == true) {
						System.out.println("line item already exists. Not addded to orderCart: " + lineItem);
					} else {
						orderCart.addItem(lineItem);
						System.out.println("line item added orderCart: " + lineItem);
					}
				} else if (quantity == 0) {
					orderCart.removeItem(removeItem);
					System.out.println("line item removed orderCart: " + lineItem);
				} else {
					System.out.println("line item not added to orderCart");
				} // end if

			}

			if (removeItem != null) {
				orderCart.removeItem(removeItem);
				System.out.println("line item removed orderCart: " + removeItem);
				System.out.println("orderCart.getCost() " + orderCart.getCost());
			} else {
				System.out.println("line item NOT removed orderCart: ");
			}

			session.setAttribute("orderCart", orderCart);

			String msg = "Item added to your cart. When ready to checkout select CART in the menu.";
			session.setAttribute("msg", msg);
			url = "/shop.jsp";

		} else if (action.contains("orderAdd")) {

			String itemString = request.getParameter("itemString");
			System.out.println("itemString: " + itemString);

			OrderCart orderCart = (OrderCart) session.getAttribute("orderCart");

			if (itemString != null) {

				// lookup product
				System.out.println("looking up product productId: " + itemString);
				Product product = (Product) ProductDB.selectProduct(itemString);

				// check if item has already been added to order cart
				if (orderCart.containsItem(product.getName()) == true) {
					System.out.println("Line item already exists. Not addded to orderCart for item: " + itemString);

					for (OrderLineItem lineItem : orderCart.getItems()) {
						System.out.println("\tLine productItem: " + lineItem.getItem() + ", quantity: "
								+ lineItem.getQuantity() + ", cost: " + lineItem.getTotalCurrencyFormat());

						if (lineItem.getItem().equals(itemString)) {
							lineItem.setQuantity(lineItem.getQuantity() + 1);
							System.out.println("\tincreased lineItem.setQuantity: " + lineItem.getQuantity());
						}
					}
				}
			} else {
				System.out.println("line item not added to orderCart");
			} // end if

			url = "/cart.jsp";

		} else if (action.contains("orderSubtract")) {

			String itemString = request.getParameter("itemString");
			System.out.println("itemString: " + itemString);

			OrderCart orderCart = (OrderCart) session.getAttribute("orderCart");

			// lookup product
			System.out.println("looking up product productId: " + itemString);
			Product product = (Product) ProductDB.selectProduct(itemString);

			// check if item has already been added to order cart
			if (orderCart.containsItem(product.getName()) == true) {
				System.out.println("Line item already exists. Not addded to orderCart for item: " + itemString);

				for (OrderLineItem lineItem : orderCart.getItems()) {
					System.out.println("\tLine productItem: " + lineItem.getItem() + ", quantity: "
							+ lineItem.getQuantity() + ", cost: " + lineItem.getTotalCurrencyFormat());

					if (lineItem.getItem().equals(itemString)) {

						if (lineItem.getQuantity() <= 1) {
							// minimum quantity must be 1
							lineItem.setQuantity(1);
							System.out.println("\tAn attempt was made to set quanitity below minumum of 1");
							System.out.println("\tlineItem.setQuantity: " + lineItem.getQuantity());
						} else {
							lineItem.setQuantity(lineItem.getQuantity() - 1);
							System.out.println("\tdecreased lineItem.setQuantity: " + lineItem.getQuantity());
						}
					}
				}
			} else {
				System.out.println("line item not added to orderCart");
			} // end if

			url = "/cart.jsp";

		} else if (action.contains("checkout")) {

			// load cart
			OrderCart orderCart = (OrderCart) session.getAttribute("orderCart");

			User user = (User) session.getAttribute("currentSessionUser");

			if (user == null) {
				System.out.println("user == null");
				String msg = "There is a problem in your cart. Please correct.";
				session.setAttribute("msg", msg);
				url = "/cart.jsp";
			} else if (orderCart == null) {
				System.out.println("orderCart == null");
				String msg = "There is a problem in your cart. Please correct.";
				System.out.println(msg);
				session.setAttribute("msg", msg);
				url = "/cart.jsp";
			} else {
				// delete all line items from DB first before saving new
				System.out.println(
						"Deleting order cart line items for this user before saving revisions to DB for Username: "
								+ user.getUsername());
				OrderDB.deleteByUser(user);

				System.out.println("Saving new order cart line items to DB.");

				for (OrderLineItem item : orderCart.getItems()) {
					System.out.println("\tLine productId: " + item.getProduct() + ", quantity: " + item.getQuantity()
							+ ", cost: " + item.getTotalCurrencyFormat() + ", username: " + user.getUsername());

					Order order = new Order();
					order.setProduct(item.getProduct());
					order.setQuantity(item.getQuantity());
					order.setCostEach(item.getCost());
					order.setUser(user);
					OrderDB.insert(order);
					System.out.println("\tLine item saved in DB");
				}

				url = "/checkout.jsp";

				// reload cart from DB
				List<Order> orderLineItems = OrderDB.selectAllLineItemsByUsername(user);
				session.setAttribute("orderLineItems", orderLineItems);

				// calculate total cost for line items from DB
				double totalCost = 0;
				for (int i = 0; i < orderLineItems.size(); i++) {
					totalCost = totalCost + orderLineItems.get(i).getCostEach();
				}

				NumberFormat currency = NumberFormat.getCurrencyInstance();
				request.setAttribute("totalCost", currency.format(totalCost));
			}
		} else if (action.contains("payment")) {

			// payment form
			String creditCardNumber = request.getParameter("creditCardNumber");
			String expirationDate = request.getParameter("expirationDate");
			String addressStreet = request.getParameter("addressStreet");
			String addressCity = request.getParameter("addressCity");
			String addressState = request.getParameter("addressState");
			String addressZipCode = request.getParameter("addressZipCode");
			System.out.println("creditCardNumber: " + creditCardNumber);
			System.out.println("expirationDate: " + expirationDate);
			System.out.println("addressStreet: " + addressStreet);
			System.out.println("addressCity: " + addressCity);
			System.out.println("addressState: " + addressState);
			System.out.println("addressZipCode: " + addressZipCode);

			// load cart
			OrderCart orderCart = (OrderCart) session.getAttribute("orderCart");

			User user = (User) session.getAttribute("currentSessionUser");

			if (user == null) {
				System.out.println("user == null");
				String msg = "There is a problem in your cart. Please correct.";
				session.setAttribute("msg", msg);
				url = "/cart.jsp";
			} else if (orderCart == null) {
				System.out.println("orderCart == null");
				String msg = "There is a problem in your cart. Please correct.";
				System.out.println(msg);
				session.setAttribute("msg", msg);
				url = "/cart.jsp";
			} else {

				// validate and complete payment here...
				// purposefully left blank - out of scope for current project

				System.out.println("Saving payment to DB.");
				Date paymentDatetime = new Date();
				Payment payment = new Payment();
				payment.setPaymentDatetime(paymentDatetime);
				payment.setPaymentStatus("Complete");
				payment.setPaymentTotal(orderCart.getTotal());
				payment.setUser(user);

				PaymentDB.insert(payment);
				System.out.println("\tLine item saved in DB");

				// cart can be cleared
				session.setAttribute("orderLineItems", null);
				session.setAttribute("orderCart", null);

				url = "/confirmation.jsp";
			}
		} // end if

		// for debug
		String jSessionId = session.getId();
		System.out.println("In OrderCartServlet prior to forwarding. jSessionId: " + jSessionId);
		System.out.println("\tsession user: " + session.getAttribute("currentSessionUser"));
		System.out.println("\tsession orderCart: " + session.getAttribute("orderCart"));

		// forward request/response
		RequestDispatcher dispatcher = getServletConfig().getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

	}

}
