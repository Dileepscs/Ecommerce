$(document).ready(function () {
    // Function to create a product card
    function createProductCard(product) {
      var card = $("<div>").addClass("py-3 px-2 m-2 border border-primary border-1 rounded w-30");
      card.append($("<img>").attr({ src: product.image, height: "150px", width: "150px" }));
      card.append($("<p>").addClass("name").text(product.pname));
      card.append($("<p>").text(product.price));
      var btn=$("<button>").addClass("btn btn-primary").text("Add to cart");
      card.append(btn);
      btn.click(function () { // Correct spelling and syntax
          var url = "http://localhost:8080/store/cart"; // The endpoint URL
          
          fetch(url, {
              method: "POST", // Use POST method for creating/updating data
              headers: {
                  "Content-Type": "application/json" // Set content type to JSON
              },
              body: JSON.stringify(product) // Convert the object to JSON string
          })
          .then((response) => {
              if (!response.ok) { // Check if the response is not successful
                  throw new Error(`Network response was not ok: ${response.statusText}`);
              }
              console.log("Data sent successfully");
          })
          .catch((error) => {
              console.error("Fetch error:", error);
              // Consider adding user-friendly error handling here
          });
      });
  
      return card;
    }
  
    function fetchAndRenderProducts(url) {
      fetch(url)
        .then((response) => {
          if (!response.ok) {
            throw new Error(`Network response was not ok: ${response.statusText}`);
          }
          return response.json();
        })
        .then((data) => {
          $(".body").empty(); // Clear existing content
          console.log(data);
          data.map((product) => {
            $(".body").append(createProductCard(product));
          });
        })
        .catch((error) => {
          console.error("Fetch error:", error);
          // Consider displaying a user-friendly error message
        });
    }
  
    // Initial fetch
    fetchAndRenderProducts("http://localhost:8080/store/product");
  
    // Event handler for category and sort changes
    $("#option1").change(function () {
      var category = $("#option1").val();
      var sort = $("#option2").val();
      if (category === "category") {
          category = null;
      }
      if (sort === "sort") {
          sort = null;
      }
       var url ="http://localhost:8080/store/product?category="+category+"&sort="+sort;
      fetchAndRenderProducts(url);
    });
    $("#option2").change(function () {
       var category = $("#option1").val();
       var sort = $("#option2").val();
       if (category === "category") {
              category = null;
       }
       if (sort === "sort") {
              sort = null;
       }
       var url ="http://localhost:8080/store/product?category="+category+"&sort="+sort;
       fetchAndRenderProducts(url);
    });
    $("#cart").click(function(){
      // Redirects to a new page
        window.location.href = "http://localhost:8080/store/cart.jsp";
  
        /* var url="http://localhost:8080/online_store/cart";
        fetch(url)
        .then(()=>{console.log("hihihi")})
        .catch((error) => {
          console.error("Fetch error:", error);
           
        });*/
    });
    
  });