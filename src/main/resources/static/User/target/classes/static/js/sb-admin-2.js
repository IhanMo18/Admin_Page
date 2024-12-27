let cardTotal = document.querySelector("#earn_total");
let cardOrders = document.querySelector("#orders");
const cardStock = document.querySelector("#total_Stock");

showTotalCard();
showProductsInStock();

async function showTotalCard() {
  try {
    const response = await fetch('http://localhost:8080/api/v1/order/details', {
      method: "GET",
      headers: {
        "Accept": "application/json",
        "Content-Type": "application/json"
      }
    });

    if (!response.ok) {
      throw new Error(`Error en la solicitud: ${response.status}`);
    }

    const data = await response.json();

    let total = data.reduce((accum, obj) => accum + obj.total, 0);
    let orderCount = data.filter(obj => obj != null).length;

    // Actualizar tarjeta total
    if (cardTotal) {
      let span = document.createElement("span")
      cardTotal.appendChild(span)
      span.innerHTML=`${total} $`;
      span.className= "text-success";
    } else {
      console.error('Elemento con ID "#earn_total" no encontrado.');
    }

    // Actualizar tarjeta órdenes
    if (cardOrders) {
      let spanOrderCount = document.createElement("span")
      cardOrders.appendChild(spanOrderCount)
      spanOrderCount.innerHTML=`${orderCount}`;
      spanOrderCount.className="text-primary";
    } else {
      console.error('Elemento con ID "#orders" no encontrado.');
    }

  } catch (error) {
    console.error('Error al obtener los datos:', error);
  }
}

async function getProductData(){
  try {
    const response = await fetch('http://localhost:8080/api/v1/products', {
      method: "GET",
      headers: {
        "Accept": "application/json",
        "Content-Type": "application/json"
      }
    });

    return await response.json()
  }catch (error) {
    console.log(error)
  }
}

async function showProductsInStock() {
  try {
    let productStockData = await getProductData();
    var productStock = 0;

    for (let i = 0; i < productStockData.length; i++) {

      if (productStockData[i].stock !== undefined) {
        productStock += productStockData[i].stock;
      }
    }

    let spanOrderCount = document.createElement("span");
    cardStock.appendChild(spanOrderCount);
    spanOrderCount.innerHTML = `${productStock}`;
    spanOrderCount.className = "text-info";

  } catch (error) {
    console.error("Error al procesar los datos: ", error);
  }


}

(function($) {
  "use strict"; // Start of use strict

  // Toggle the side navigation
  $("#sidebarToggle, #sidebarToggleTop").on('click', function(e) {
    $("body").toggleClass("sidebar-toggled");
    $(".sidebar").toggleClass("toggled");
    if ($(".sidebar").hasClass("toggled")) {
      $('.sidebar .collapse').collapse('hide');
    };
  });

  // Close any open menu accordions when window is resized below 768px
  $(window).resize(function() {
    if ($(window).width() < 768) {
      $('.sidebar .collapse').collapse('hide');
    };

    // Toggle the side navigation when window is resized below 480px
    if ($(window).width() < 480 && !$(".sidebar").hasClass("toggled")) {
      $("body").addClass("sidebar-toggled");
      $(".sidebar").addClass("toggled");
      $('.sidebar .collapse').collapse('hide');
    };
  });

  // Prevent the content wrapper from scrolling when the fixed side navigation hovered over
  $('body.fixed-nav .sidebar').on('mousewheel DOMMouseScroll wheel', function(e) {
    if ($(window).width() > 768) {
      var e0 = e.originalEvent,
          delta = e0.wheelDelta || -e0.detail;
      this.scrollTop += (delta < 0 ? 1 : -1) * 30;
      e.preventDefault();
    }
  });

  // Scroll to top button appear
  $(document).on('scroll', function() {
    var scrollDistance = $(this).scrollTop();
    if (scrollDistance > 100) {
      $('.scroll-to-top').fadeIn();
    } else {
      $('.scroll-to-top').fadeOut();
    }
  });

  // Smooth scrolling using jQuery easing
  $(document).on('click', 'a.scroll-to-top', function(e) {
    var $anchor = $(this);
    $('html, body').stop().animate({
      scrollTop: ($($anchor.attr('href')).offset().top)
    }, 1000, 'easeInOutExpo');
    e.preventDefault();
  });

})(jQuery); // End of use strict