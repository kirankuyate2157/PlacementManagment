import { useEffect, useState } from "react";
import Chart from "react-apexcharts";
import ApexCharts from "apexcharts";

const TodaysOrders = () => {
  const [chartData, setChartData] = useState(null);

  useEffect(() => {
    // Simulated data for demonstration
    const generateData = () => {
      const prices = [];
      const dates = [];

      // Generate 30 days of data
      const currentDate = new Date();
      for (let i = 29; i >= 0; i--) {
        const date = new Date(currentDate);
        date.setDate(date.getDate() - i);
        const price = Math.floor(Math.random() * (200 - 100 + 1) + 100); // Random price between 100 and 200
        dates.push(date.toISOString().slice(0, 10)); // Format date as 'YYYY-MM-DD'
        prices.push(price);
      }

      return { prices, dates };
    };

    const seriesData = generateData();
    setChartData(seriesData);

    // Clean up function to destroy the chart on component unmount
    return () => {
      ApexCharts.exec("stock-chart", "destroy");
    };
  }, []);

  const options = {
    series: [
      {
        name: "Stock Price",
        data: chartData ? chartData.prices : [],
      },
    ],
    chart: {
      type: "area",
      height: 350,
      id: "stock-chart",
      toolbar: {
        show: false, // Hide toolbar (including zoom option)
      },
      background: "transparent",
    },
    dataLabels: {
      enabled: false,
    },
    stroke: {
      curve: "smooth",
    },
    xaxis: {
      type: "datetime",
      categories: chartData ? chartData.dates : [],
    },
    yaxis: {
      show: false, // Hide Y-axis
    },
    grid: {
      show: false, // Hide grid lines
    },
    tooltip: {
      x: {
        format: "dd MMM yyyy",
      },
    },
  };

  return (
    <div className='stock-chart'>
      <Chart
        options={options}
        series={options.series}
        type='area'
        height={350}
      />
    </div>
  );
};

export default TodaysOrders;
