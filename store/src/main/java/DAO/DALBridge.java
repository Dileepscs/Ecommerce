package DAO;

import dal.Products;
import dal.ProductsDAL;
import dal.Users;
import dal.UsersDAL;

public class DALBridge {

	public static Products getProductsDAL() {
		return new ProductsDAL();
	}

	public static Users getUsersDAL() {
		return new UsersDAL();
	}
}
