package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import models.Product;
import models.ProductsList;

public class ProductsDAL implements Products {
	//
	// public ProductsDAL() {
	// }

	public ProductsList getProducts(String category, String sort) {
		// TODO Auto-generated method stub
		ProductsList pl = null;
		System.out.println("from database");
		try {
			Connection con = DAL.connect();
			String query = "SELECT * FROM get_products(?,?);";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, category);
			st.setString(2, sort);
			ResultSet rs = st.executeQuery();
			pl = new ProductsList();
			while (rs.next()) {
				pl.add(new Product(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5)));
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return pl;
	}

	public void setOrderDetails(int oid, Date sqlDate, int total, int cid) {
		// TODO Auto-generated method stub // TODO Auto-generated method stub
		try {
			Connection con = DAL.connect();
			String query = "Insert into os_orders values(?,?,?,?)";
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, oid);
			st.setDate(2, sqlDate);
			st.setInt(3, total);
			st.setInt(4, cid);
			st.execute();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public int getCid(String email) {
		int x = 0;
		// TODO Auto-generated method stub
		try {
			Connection con = DAL.connect();
			String query = "Select cid from os_customer where cemail= ?";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, email);
			ResultSet rs = st.executeQuery();
			rs.next();
			x = rs.getInt(1);
		} catch (Exception e) {
			System.out.println(e);
		}
		return x;
	}

	public String getCname(String email) {
		String x = "";
		// TODO Auto-generated method stub
		try {
			Connection con = DAL.connect();
			String query = "Select cname from os_customer where cemail= ?";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, email);
			ResultSet rs = st.executeQuery();
			rs.next();
			x = rs.getString(1);
		} catch (Exception e) {
			System.out.println(e);
		}
		return x;
	}

	public void setProductDetails(int oid, ProductsList p) {
		try {
			Connection con = DAL.connect();
			String query = "insert into os_order_products values(?,?)";
			PreparedStatement st = con.prepareStatement(query);
			for (Product pr : p) {
				st.setInt(1, oid);
				st.setInt(2, pr.getPid());
				st.addBatch();
			}
			st.executeBatch();
		} catch (Exception e) {
		}
	}

	public int[] getgst(int[] hsn) {
		// TODO Auto-generated method stub
		int gst[] = new int[hsn.length];
		for (int ele : hsn)
			System.out.println("in hsn jflas" + ele);
		try {
			Connection con = DAL.connect();
			int i = 0;
			for (int h : hsn) {
				String query = "select gst from os_hsn where hsn_code=?";
				PreparedStatement st = con.prepareStatement(query);
				st.setInt(1, h);
				ResultSet rs = st.executeQuery();
				rs.next();
				gst[i] = rs.getInt(1);
				i++;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return gst;
	}

}
