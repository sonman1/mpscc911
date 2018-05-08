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

import business.*;
import data.ProductDB;
import data.UserDB;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
			action = "login";
		}

		String url = "/index.jsp";

		if (action.equals("login")) {

			System.out.println("in authenticateLogin");

			String username;
			String password;
			String msg;
			
			// get context parameters from web.xml
			String passwordMaxAttempts = request.getSession().getServletContext()
					.getInitParameter("passwordMaxAttempts");

			Integer passwordAttempts = (Integer) session.getAttribute("currentSessionPasswordAttempts");

			String navResult = "";

			if (passwordAttempts == null) {
				// set default to 1
				passwordAttempts = 0;
				System.out.println("currentSessionPasswordAttempts = " + passwordAttempts);
			}

			username = request.getParameter("username");
			password = request.getParameter("password");
			
			User user = UserDB.selectUserByUseridandPW(username, password);

			if (user != null) {

				session.setAttribute("currentSessionUser", user);

				navResult = "successLogon";
				System.out.println("navResult= " + navResult);
				
				// get all products
				List allProducts = new LinkedList();
				allProducts = ProductDB.selectAllProducts();
				session.setAttribute("allProducts", allProducts);
				
				url = "/shop.jsp";

			} else {
				if (passwordAttempts < (Integer.parseInt(passwordMaxAttempts) - 1)) {

					msg = "User and Password combination not found. You have "
							+ ((Integer.parseInt(passwordMaxAttempts) - 1) - passwordAttempts) + " remaining.";

					// increment login attempts
					passwordAttempts = passwordAttempts + 1;
					session.setAttribute("currentSessionPasswordAttempts", passwordAttempts);

					System.out.println("currentSessionPasswordAttempts = " + passwordAttempts);
					System.out.println("passwordMaxAttempts = " + passwordMaxAttempts);

					navResult = "wrongPassword";
					System.out.println("navResult= " + navResult);
					session.setAttribute("msg", msg);
					url = "/index.jsp";

				} else {
					msg = "Sorry, you don't have an account. You must register first.";
					session.setAttribute("message", "Sorry, you don't have an account. You must register first.");

					navResult = "failedLogon";
					System.out.println("navResult= " + navResult);
					session.setAttribute("msg", msg);
					url = "/register.jsp";

				}
			}

		} else if (action.equals("register")) {
			
			url = "/register.jsp";
		}

		// for debug
		String jSessionId = session.getId();
		System.out.println("In LoginServlet prior to forwarding. jSessionId: " + jSessionId);
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
