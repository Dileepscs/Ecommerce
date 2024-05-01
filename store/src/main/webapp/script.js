$(document).ready(function(){

    console.log("hello");

    // Function to create a product card
    function createProductCard(product) {
        var card = $("<div>").addClass("service-item-container");
        var card_top = $("<div>").addClass("service-item service-item-2");
        let img = $("<img>").addClass("service-item-image");
        card_top.append(img.attr({ src: product.image }));
        let displayDiv = $("<div>").addClass("service-item-top service-item-top-invisible");
        let head = $("<h2>").text(product.pname);        
        displayDiv.append(head);
        card_top.append(displayDiv);
        
        let desc = "iuhfdulisghuibh uieiweroishvfhuipwrjeipuhgiwug";
        card_top.append($("<p>").addClass("service-item-description service-item-description-invisible").text(desc));
        var btn = $("<button>").addClass("btn").text("Add to cart");
        card_top.append(btn);
        btn.click(function () {
            var url = "http://localhost:8080/store/cart"; 
            fetch(url, {
                method: "POST", 
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(product)
            })
            .then((response) => {
                if (!response.ok) {
                    throw new Error(`Network response was not ok: ${response.statusText}`);
                }
                console.log("Data sent successfully");
            })
            .catch((error) => {
                console.error("Fetch error:", error);
            });
        });
        card.append(card_top);
        let card_bottom = $("<div>").addClass("service-item-bottom service-item-botton-visible");
        card_bottom.append($("<h2>").text(product.pname));
        card_bottom.append($("<p>").text(product.price));
        card.append(card_bottom);
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
            $(".service-items").empty(); 
            console.log(data);
            data.forEach((product) => {
              $(".service-items").append(createProductCard(product));
            });
            addEventListeners();
          })
          .catch((error) => {
            console.error("Fetch error:", error);
          });
    }

    function addEventListeners() {
        const serviceItems = $(".service-item");
        serviceItems.each(function(index) {
            $(this).on('mouseenter', function() {
                // console.log('one');
                $(this).find('.service-item-bottom').removeClass('service-item-botton-visible').addClass('service-item-botton-invisible');
                $(this).find('.service-item-description').addClass('service-item-description-visible').removeClass('service-item-description-invisible');
                $(this).find('.service-item-top').addClass('service-item-top-visible').removeClass('service-item-top-invisible');
                $(this).find('.service-item-bottom').hide();
              });
            $(this).on('mouseleave', function() {
                // console.log('two');
                $(this).find('.service-item-bottom').show();
                $(this).find('.service-item-bottom').addClass('service-item-botton-visible').removeClass('service-item-botton-invisible');
                $(this).find('.service-item-description').removeClass('service-item-description-visible').addClass('service-item-description-invisible');
                $(this).find('.service-item-top').removeClass('service-item-top-visible').addClass('service-item-top-invisible');
            });
        });
    }

    // Initial fetch
    fetchAndRenderProducts("http://localhost:8080/store/product");

    // Event handler for category and sort changes
    $("#option1, #option2").change(function () {
        var category = $("#option1").val();
        var sort = $("#option2").val();
        if (category === "category") {
            category = null;
        }
        if (sort === "sort") {
            sort = null;
        }
        var url = "http://localhost:8080/store/product?category=" + category + "&sort=" + sort;
        fetchAndRenderProducts(url);
    });

    $("#cart").click(function(){
		console.log("button clicked");
        window.location.href = "http://localhost:8080/store/cart.jsp";
    });
});
