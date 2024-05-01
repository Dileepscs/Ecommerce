<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="models.ProductsList, models.Product" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <title>Shopping Cart</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        

        <%
            int oid = (int) session.getAttribute("order_id");
            String cid =((String)session.getAttribute("customer_name")).toUpperCase();
            System.out.println(cid);
            int total = (int) session.getAttribute("cart_total");
            ProductsList cart = (ProductsList) session.getAttribute("cart_items");
        %>
        <h3 style="color:green; display:inline;">  <%= cid %>  ,Your Order is successful with order id:</h3>
        <h3 style="color:green; display:inline;"><%= oid %></h3>
        
        <h3 style="color:green">Total payable amount is: <%= total %></h3>
        

        <!-- Button to trigger modal -->
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#cartModal" >View Order items</button>
        
        

        <!-- Modal Structure -->
        <div class="modal fade" id="cartModal" tabindex="-1" role="dialog" aria-labelledby="cartModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="cartModalLabel">Items</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <!-- Table with cart items -->
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>Product ID</th>
                                    <th>Product Name</th>
                                    <th>Price</th>
                                    <th>Image</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    if (cart != null && !cart.isEmpty()) {
                                        for (Product product : cart) {
                                %>
                                    <tr>
                                        <td><%= product.getPid() %></td>
                                        <td><%= product.getPname() %></td>
                                        <td><%= product.getPrice() %></td>
                                        <td><img src="<%= product.getImage() %>" alt="<%= product.getPname() %>" width="50" height="50"/></td>
                                    </tr>
                                <%
                                        }
                                    } else {
                                %>
                                    <tr>
                                        <td colspan="4">No items in the cart.</td>
                                    </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
                  
                </div>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
