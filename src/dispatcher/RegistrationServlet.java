package dispatcher;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import business.User;
import data.ProductDB;
import data.UserDB;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrationServlet() {
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
			action = "register";
		}

		String url = "/register.jsp";
		String msg;

		if (action.equals("register")) {

			System.out.println("in register");

			// Registration Form
			String firstName = request.getParameter("firstName");
			String lastname = request.getParameter("lastName");
			String email = request.getParameter("email"); // username is the same as email for now
			String username = request.getParameter("email"); // username is the same as email for now
			String password = request.getParameter("password"); // need hash strategy for password in production

			// check if username exists first before entering new
			if (UserDB.usernameExists(username) == false) {

				// prepare to save user to DB
				User user = new User();
				user.setFirstName(firstName);
				user.setLastName(lastname);
				user.setEmail(email);
				user.setUsername(username);
				user.setPassword(password);
				UserDB.insert(user);
				session.setAttribute("currentSessionUser", user);

				// get all products
				List allProducts = new LinkedList();
				allProducts = ProductDB.selectAllProducts();
				session.setAttribute("allProducts", allProducts);
				
				url = "/shop.jsp";

			} else {
				msg = "Username already exists. Please select a different username and try again.";
				System.out.println("Username already exists for: " + username);
				session.setAttribute("msg", msg);
				url = "/register.jsp";
			}

		}

		// for debug
		String jSessionId = session.getId();
		System.out.println("In RegistrationServlet prior to forwarding. jSessionId: " + jSessionId);
		System.out.println("\tsession user: " + session.getAttribute("currentSessionUser"));

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
