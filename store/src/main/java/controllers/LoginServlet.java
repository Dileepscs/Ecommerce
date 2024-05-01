package controllers;

import java.io.IOException;

import DAO.DALBridge;
import dal.Users;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.ProductsList;

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

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cemail = req.getParameter("cemail");
		String cpassword = req.getParameter("cpassword");

		// DAO LAYER INSERTION
		// DALBridge dao = new DALBridge();
		Users user = DALBridge.getUsersDAL();

		// ACCESSING DAL object using interface
		boolean ispwd = user.verifyUser(cemail, cpassword);
		ProductsList pl = new ProductsList();
		if (ispwd) {
			// On successful login, set session attribute and redirect to store.jsp
			HttpSession session = req.getSession();
			session.setAttribute("user", cemail);
			session.setAttribute("cart_items", pl);
			res.sendRedirect("store.jsp");
		} else {
			// If login fails, re-dispatch to login.html with an error message
			RequestDispatcher rd = req.getRequestDispatcher("login.html");
			res.setContentType("text/html");
			res.getWriter().write(
					"<div class='error-message'>Your credentials are incorrect. Please check or register.</div>");
			rd.include(req, res); // Include the error message and re-dispatch to login.html
		}
	}

}
