package controllers;

import java.io.BufferedReader;
import java.io.IOException;

import com.google.gson.Gson;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Product;
import models.ProductsList;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson;

	public CartServlet() {
		gson = new Gson();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		BufferedReader reader = req.getReader();
		StringBuilder jsonBody = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			jsonBody.append(line);
		}
		Product product = gson.fromJson(jsonBody.toString(), Product.class);

		HttpSession session = req.getSession(true); // Create session if it doesn't exist
		ProductsList cart = (ProductsList) session.getAttribute("cart_items");
		if (cart == null) {
			cart = new ProductsList(); // Initialize if not present
			session.setAttribute("cart_items", cart); // Store in session
		}
		cart.add(product); // Add the new product to the cart

		res.setContentType("application/json");
		res.getWriter().write("{\"status\":\"success\",\"message\":\"Product added to cart.\"}");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("cart.jsp"); // Relative path
		rd.forward(req, res); // Forward to JSP
	}
}
