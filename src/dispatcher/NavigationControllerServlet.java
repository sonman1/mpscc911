package dispatcher;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class NavigationControllerServlet
 * 
 * @version 1.0 2018-05-07
 * @author Sonny Saxton
 */
@WebServlet("/NavigationControllerServlet")
public class NavigationControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NavigationControllerServlet() {
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
		
		// for debug
		String jSessionId = session.getId();
		System.out.println("In NavigationControllerServlet. jSessionId: " + jSessionId);
		System.out.println("\tsession user: " + session.getAttribute("currentSessionUser"));

		// remove any previous messages to user
		session.setAttribute("msg", null);
		
		// get current action
		String action = request.getParameter("action");
		System.out.println("Action ===> " + action);

		if (action == null) {
			action = "go_to_index";
		}

		String url = "/index.jsp";

		if (action.equals("go_to_index")) {
			
			url = "/index.jsp";
		} else if (action.equals("go_to_home")) {
			url = "/home.jsp";
		} else if (action.equals("go_to_shop")) {
			url = "/shop.jsp";
		} else if (action.equals("go_to_cart")) {
			url = "/cart.jsp";
		} else if (action.equals("go_to_contact")) {
			url = "/contact.jsp";
		} else if (action.equals("go_to_register")) {
			url = "/register.jsp";
		} else if (action.equals("logout")) {
			session.invalidate();
			url = "/index.jsp";
			
		}

		// for debug
		System.out.println("In NavigationControllerServlet prior to forwarding. jSessionId: " + jSessionId);

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
