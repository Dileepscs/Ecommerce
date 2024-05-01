<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Customer Registration Form</title>
<!-- Include Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<link rel="stylesheet" href='style.css'/>
<body>
	<div class="container mt-5">
		<div class="header">
		<div>Online Store</div>
		<div id="cart"><i id="img" class="bi bi-cart4"></i>
        </div>
		</div>
            
		<div
			class="main">
      
      <div class="options">
      
       <div class="option1">
      <select id="option1" name="category" class="form-select" aria-label="multiple select example">
        <option selected>category</option>
        <option value="men's clothing">men's clothing</option>
        <option value="jewelery">jewelery</option>
        <option value="electronics">electronics</option>
        <option value="women's clothing">women's clothing</option>
      </select>
      </div>
      
      <div class="option2">
      <select id="option2" name="sort" class="form-select" aria-label="multiple select example">
        <option selected>sort</option>
        <option value="price">by price</option>
        <option value="name">by name</option>
      </select>
      </div>
      
      </div>
      <div class="body">
     
      </div>
      </div>
       
      <div class="footer">Footer Section</div>
    </div>
    <!-- Include Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  </body>
<script src="script.js">
</script>

</html>
