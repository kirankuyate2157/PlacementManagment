import Chart from "react-apexcharts";
const BubbleChart = ({categoryData}) => {

  // Function to generate data for bubbles based on category data
  const generateData = () => {
    const data = [];

    // Iterate over the category data and create bubbles
    categoryData?.forEach((category) => {
      const { name, count } = category;
      // Use the category name and count to create a bubble
      data.push({
        x: name,
        y: count, // Use count as the y-value
        z: count, // Use count also for the size of the bubble (z-value)
      });
    });

    return data;
  };

  // Configure chart options
  const options = {
    chart: {
      height: "100%",
      type: "bubble",
      toolbar: {
        show: false, // Hide toolbar (including zoom option)
      },
    },
    dataLabels: {
      enabled: false,
    },
    xaxis: {
      type: "category",
    },
    yaxis: {
      show: true,
    },
    grid: {
      show: false,
    },
  };

  // Generate series data using the generated bubble data
  const series = [
    {
      name: "Bubble Series",
      data: generateData(), // Use the generated data for bubbles
    },
  ];

  return (
    <div className='bubble-chart h-full'>
      <Chart options={options} series={series} type='bubble' height="100%" />
    </div>
  );
};

export default BubbleChart;
