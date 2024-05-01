package controllers;

import java.io.IOException;

import DAO.DALBridge;
import dal.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Customer;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int cid = (int) (Math.random() * (99999 - 10000) * 10000);
		String cname = req.getParameter("cname");
		String cmobile = req.getParameter("cmobile");
		String cemail = req.getParameter("cemail");
		String clocation = req.getParameter("clocation");
		String cpassword = req.getParameter("cpassword");
		Customer c = new Customer(cid, cname, cmobile, cemail, clocation, cpassword);

		// DAO LAYER INSERTION
		// DALBridge dao = new DALBridge();
		Users user = DALBridge.getUsersDAL();

		// ACCESSING DAL object using interface

		user.registerUser(c);
		System.out.println("register called");
		res.sendRedirect("store.jsp");
	}

}
