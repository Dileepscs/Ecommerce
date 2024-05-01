package dal;

import models.Customer;

public interface Users {

	boolean verifyUser(String cemail, String cpassword);

	void registerUser(Customer c);
}
