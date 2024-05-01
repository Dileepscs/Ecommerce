package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import models.Customer;

public class UsersDAL implements Users {

	// public Users() {
	// super();
	// }

	public boolean verifyUser(String cemail, String cpassword) {
		boolean ispwd = false;
		try {
			Connection con = DAL.connect();
			String query = "Select cpassword from os_customer where cemail=?";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, cemail);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				if (cpassword.equals(rs.getString(1))) {
					ispwd = true;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return ispwd;
	}

	public void registerUser(Customer c) {
		// TODO Auto-generated method stub
		String query = ("insert into os_customer values(?,?,?,?,?,?);");
		try {
			Connection con = DAL.connect();
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, c.getCid());
			st.setString(2, c.getCname());
			st.setString(3, c.getCmobile());
			st.setString(4, c.getCemail());
			st.setString(5, c.getClocation());
			st.setString(6, c.getCpassword());
			st.execute();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
