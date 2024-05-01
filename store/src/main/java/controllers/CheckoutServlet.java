package controllers;

import java.io.IOException;
import java.sql.Date;

import DAO.DALBridge;
import dal.Products;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Product;
import models.ProductsList;

/**
 * Servlet implementation class CheckoutServlet
 */
@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckoutServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession hs = req.getSession();
		ProductsList p = (ProductsList) hs.getAttribute("cart_items");
		String email = (String) hs.getAttribute("user");
		int oid = (int) (Math.random() * (99999 - 10000 + 1) + 10000);
		// String date = new Date().toString();

		long currentTimeMillis = System.currentTimeMillis();

		// Create a java.sql.Date object representing the current date
		Date sqlDate = new Date(currentTimeMillis); // Converts to java.sql.Date

		// DAO LAYER INSERTION
		// DALBridge dao = new DALBridge();
		Products pd = DALBridge.getProductsDAL();

		int total = 0;
		int hsn[] = new int[p.size()];
		int i = 0;
		for (Product pr : p) {
			hsn[i] = pr.getHsn();
			i++;
		}

		// ACCESSING DAL object using interface
		int gst[] = pd.getgst(hsn);
		i = 0;
		for (Product pr : p) {
			total += (pr.getPrice() + (int) ((double) pr.getPrice() * ((double) gst[i] / (double) 100)));
			i++;
		}

		int cid = pd.getCid(email);
		String cname = pd.getCname(email);

		pd.setOrderDetails(oid, sqlDate, total, cid);
		pd.setProductDetails(oid, p);
		hs.setAttribute("cart_total", total);
		hs.setAttribute("order_id", oid);
		hs.setAttribute("customer_name", cname);
	}

}
