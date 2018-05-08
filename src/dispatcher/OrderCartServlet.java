package dispatcher;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.hibernate.SessionFactory;
import org.hibernate.service.ServiceRegistry;
import org.omg.CORBA.portable.OutputStream;

import business.OrderLineItem;
import business.Order;
import business.OrderCart;
import business.User;
import data.OrderDB;
import data.UserDB;

/**
 * Servlet implementation class OrderCartServlet
 * 
 * @version 1.0 2018-04-05
 * @author Sonny Saxton
 */
@WebServlet(description = "OrderCartServlet", urlPatterns = { "/OrderCartServlet" })
public class OrderCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static SessionFactory factory;
	private static ServiceRegistry serviceRegistry;

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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// get session
		HttpSession session = request.getSession();

		// get current action
		String action = request.getParameter("action");
		System.out.println("Action ===> " + action);

		if (action == null) {
			action = "orderCart";
		}

		String url = "/index.jsp";

		if (action.equals("go_to_register")) {
			url = "/register.jsp";
		} else if (action.contains("shop")) {
			url = "/index.jsp";
		} else if (action.contains("orderCart")) {

			// Order Form
			String name = request.getParameter("name");
			String first_name = "";
			String last_name = "";
			String username = request.getParameter("email"); // username is the same as email for now
			String email = request.getParameter("email");
			String products[] = request.getParameterValues("products");
			String quantityString = request.getParameter("quantityString");
			String removeItem = request.getParameter("removeItem");

			if (name != null) {
				try {
					session.setAttribute("name", name);
					String[] parsedName = name.split(" ");
					first_name = parsedName[0];
					last_name = parsedName[1];
				} catch (Exception e) {
					   first_name = name;
					   last_name = "";
				       System.out.println("Error: " + e.getMessage());
				       e.printStackTrace();
				} 
			}
			if (email != null) {
				session.setAttribute("email", email);
			}

			System.out.println("quantityString: " + quantityString);

			// OrderCart
			String item = "";
			int quantity; // default value
			double cost = 0.00; // default value

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

			// products
			if (products != null) {
				for (String product : products) {
					item = product;

					// add cart item and cost
					OrderLineItem lineItem = new OrderLineItem();
					lineItem.setItem(item);
					lineItem.setQuantity(quantity);
					lineItem.setCost(cost);

					// check to see if adding or removing items
					if (quantity > 0) {

						// check if item has already been added to order cart
						if (orderCart.containsItem(item) == true) {
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

				} // end for
			}

			if (removeItem != null) {
				orderCart.removeItem(removeItem);
				System.out.println("line item removed orderCart: " + removeItem);
				System.out.println("orderCart.getCost() " + orderCart.getCost());
			} else {
				System.out.println("line item NOT removed orderCart: ");
			}

			session.setAttribute("orderCart", orderCart);

			// prepare to save user to DB
			User user = new User();
			user.setUsername(username);
			user.setFirstName(first_name);
			user.setLastName(last_name);
			user.setEmail(email);
			UserDB.insert(user);
			session.setAttribute("user", user);

			url = "/order_complete.jsp";

		} else if (action.contains("confirmOrder")) {

			// load cart
			OrderCart orderCart = (OrderCart) session.getAttribute("orderCart");

			User user = (User) session.getAttribute("user");

			if (orderCart == null) {
				String msg = "There is a problem in your cart. Please correct.";
				System.out.println(msg);
				session.setAttribute("msg", msg);
				url = "/order_complete.jsp";
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
			}

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

			url = "/order_confirm.jsp";
		} else if (action.contains("checkout")) {

			url = "/checkout.jsp";
		}// end if

		// for debug
		String jSessionId = session.getId();
		System.out.println("In OrderCartServlet prior to forwarding. jSessionId: " + jSessionId);
		System.out.println("\tsession user: " + session.getAttribute("currentSessionUser"));
		System.out.println("\tsession orderCart: " + session.getAttribute("orderCart"));

		// forward request/response
		RequestDispatcher dispatcher = getServletConfig().getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
