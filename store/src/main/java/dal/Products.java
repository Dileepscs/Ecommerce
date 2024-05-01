/**
 * 
 */
package dal;

import java.sql.Date;

import models.ProductsList;

/**
 * @author DileepKumarK
 *
 */
public interface Products {

	ProductsList getProducts(String category, String sort);

	void setOrderDetails(int oid, Date sqlDate, int total, int cid);

	int getCid(String email);

	String getCname(String email);

	void setProductDetails(int oid, ProductsList p);

	int[] getgst(int[] hsn);
}
