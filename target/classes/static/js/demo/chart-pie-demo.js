// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#858796';

// Function to fetch data from API
async function getDataOrders() {
  try {
    const response = await fetch("http://localhost:8080/api/v1/order/details");

    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }

    const dataOrders = await response.json();
    return dataOrders;

  } catch (error) {
    console.error("Failed to fetch data: ", error);
    return []; // Return empty array in case of error
  }
}

async function getUsers() {
  try {
    const response = await fetch('http://localhost:8080/api/v1/users', {
      method: "GET",
      headers: {
        "Accept": "application/json",
        "Content-Type": "application/json"
      }
    });
    if (!response.ok) {
      throw new Error(`Error en la solicitud: ${response.status}`);
    }

    users = await response.json();
    return users


  }catch (error){
    console.error("Failed to fetch data: ", error)
  }
}

async function getPurchaseProducts() {
  const response = await fetch('http://localhost:8080/api/v1/purchase/products', {
    method: "GET",
    headers: {
      "Accept": "application/json",
      "Content-Type": "application/json"
    }
  });
  let products = await response.json()
  return products
}





// Function to initialize the chart
async function initializeChart() {
  const fetchedData = await getDataOrders();
  const usersData = await getUsers()
  const purchasedData = await getPurchaseProducts()

  // Process data without using map or advanced methods
  let totalOrders = 0;
  for (let i = 0; i < fetchedData.length; i++) {
    if (fetchedData[i] !== undefined) {
      totalOrders++
    }
  }

  let totalUsers = 0;
  for (let i = 0; i < usersData.length; i++) {
    if (usersData[i] !== undefined) {
      totalUsers ++
    }
  }

  let purchasedProducts = 0

  for (let i= 0 ; i < purchasedData.length;  i++){
   if (purchasedData[i] !== undefined){
     purchasedProducts++
   }
  }


  // Default labels
  const labels = ["Users", "Orders", "Purchased Products"];

  // Data corresponding to labels
  const chartData = [totalUsers, totalOrders,purchasedProducts];

  // Ensure we don't pass a single value directly
  const ctx = document.getElementById("myPieChart");
  const myPieChart = new Chart(ctx, {
    type: 'doughnut',
    data: {
      labels: labels,
      datasets: [{
        data: chartData,
        backgroundColor: ['#4e73df', '#1cc88a', '#36b9cc'],
        hoverBackgroundColor: ['#2e59d9', '#17a673', '#2c9faf'],
        hoverBorderColor: "rgba(234, 236, 244, 1)",
      }],
    },
    options: {
      maintainAspectRatio: false,
      tooltips: {
        backgroundColor: "rgb(255,255,255)",
        bodyFontColor: "#858796",
        borderColor: '#dddfeb',
        borderWidth: 1,
        xPadding: 15,
        yPadding: 15,
        displayColors: false,
        caretPadding: 10,
      },
      legend: {
        display: false
      },
      cutoutPercentage: 80,
    },
  });
}

// Call the function to initialize the chart
initializeChart();
