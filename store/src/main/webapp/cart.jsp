<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="models.ProductsList, models.Product" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <title>Shopping Cart</title>
    <!-- Include some basic styling -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h1>Your Shopping Cart</h1>
        
        <%
            ProductsList cart = (ProductsList) session.getAttribute("cart_items");
            if (cart == null || cart.isEmpty()) {
                out.println("<p>Your cart is empty.</p>");
            } else {
        %>
            <table class="table">
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
                    %>
                </tbody>
            </table>
        <%
            }
        %>
        
        <!-- Add more UI elements like buttons to proceed to checkout -->
        <button id="btn" class="btn btn-primary" onclick="window.location.href='checkout.jsp'">Proceed to Checkout</button>
    </div>

    <!-- Include Bootstrap JS for styling -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
      $("#btn").click(function(){
    	  fetch("http://localhost:8080/store/checkout")
    	  .catch((error) => {
    	        console.error("Fetch error:", error);
    	        // Consider displaying a user-friendly error message
    	   });
      });
    </script>
</body>
</html>
