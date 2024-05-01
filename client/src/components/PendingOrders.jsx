import Chart from "react-apexcharts";
import { MdArrowDownward, MdArrowUpward } from "react-icons/md";
import PropTypes from "prop-types";

const PendingOrders = ({ ordersData }) => {
  const { name, totalCost, sales } = ordersData;
  const salesdata = [
    {
      date: "2024-04-06",
      dayName: "Saturday",
      totalSales: 0,
    },
    {
      date: "2024-04-07",
      dayName: "Sunday",
      totalSales: 1,
    },
    {
      date: "2024-04-08",
      dayName: "Monday",
      totalSales: 0,
    },
    {
      date: "2024-04-09",
      dayName: "Tuesday",
      totalSales: 0,
    },
    {
      date: "2024-04-10",
      dayName: "Wednesday",
      totalSales: 0,
    },
    {
      date: "2024-04-11",
      dayName: "Thursday",
      totalSales: 0,
    },
    {
      date: "2024-04-12",
      dayName: "Friday",
      totalSales: 0,
    },
  ];
  //   const totalCost = {
  //     firstWeek: 3299,
  //     secondWeek: 0,
  //   };
  const difference = totalCost.secondWeek - totalCost.firstWeek;

  // Determine the color and icon based on the sign of the difference
  const isPositiveDifference = difference > 0;
  const colorClass = isPositiveDifference ? "text-green-600" : "text-red-600";
  const arrowIcon = isPositiveDifference ? (
    <MdArrowUpward />
  ) : (
    <MdArrowDownward />
  );

  // Format the absolute difference in thousands
  const formatLargeNumber = (num) => {
    let number = Math.abs(num);
    if (number >= 1000000) {
      return `${(number / 1000000).toFixed(1)}M`; // Convert to millions (M)
    } else if (number >= 1000) {
      return `${(number / 1000).toFixed(1)}K`; // Convert to thousands (K)
    } else {
      return `${number}`; // Return the number as is if less than 1000
    }
  };
  // Get the last 7 days from sales
  const last7DaysData = sales.slice(0, 7);

  // Extract day names and sales values for the last 7 days
  const categories = last7DaysData.map((item) => item.dayName.slice(0, 3));
  const salesValues = last7DaysData.map((item) => item.totalSales);
  let graphColor = "#008ffb"; // Default color for positive difference
  if (difference < 0) {
    graphColor = "#e20f04"; // Change color to red for negative difference
  }else if(0<difference){
    graphColor = "#04e298";
  }
  const options = {
    series: [
      {
        name: "Last Week",
        data: salesValues,
        color: graphColor,
      },
    ],
    chart: {
      height: 350,
      type: "area",
      toolbar: {
        show: false,
      },
      background: "transparent",
    },
    dataLabels: {
      enabled: false,
    },
    stroke: {
      curve: "smooth",
    },
    fill: {
      colors: [graphColor], // Set the fill color(s) for the area chart
      type: "gradient", // Use gradient fill for a smoother transition
      gradient: {
        opacityFrom: 0.7,
        opacityTo: 0.0,
        stops: [0, 100],
      },
    },
    xaxis: {
      categories: categories,
      axisBorder: {
        show: false, // Hide the x-axis line
      },
      labels: {
        show: false, // Hide x-axis labels
      },
    },
    yaxis: {
      show: false,
    },
    grid: {
      show: false,
    },
  };

  return (
    <div className='flex min-h-[200px] p-4 w-full md:w-[45%]  rounded-md justify-between bg-gray-200 text-black '>
      <div className='flex flex-col  items-start justify-between'>
        <div className='text-start'>
          <h4 className='font-semibold'>{name}</h4>
          <p className='text-xs px-1 text-gray-500'>Last 7 days</p>
        </div>
        <div className='text-start'>
          <h4 className='font-bold pl-1 text-2xl'>{`$${formatLargeNumber(
            totalCost.firstWeek + totalCost.secondWeek
          )}`}</h4>
          <p className='text-gray-500 text-xs flex items-center'>
            <span className={`flex items-center gap-1 mr-2 ${colorClass}`}>
              {arrowIcon}
              {formatLargeNumber(difference)}
            </span>
            vs last 7 days
          </p>
        </div>
      </div>
      <div className='w-full max-w-[65%] h-full'>
        <div className='w-full h-full' style={{ background: "transparent" }}>
          <Chart
            options={options}
            series={options.series}
            type='area'
            height="100%"
          />
        </div>
      </div>
    </div>
  );
};

PendingOrders.propTypes = {
  ordersData: PropTypes.shape({
    name: PropTypes.string.isRequired,
    totalCost: PropTypes.shape({
      firstWeek: PropTypes.number.isRequired,
      secondWeek: PropTypes.number.isRequired,
    }).isRequired,
    sales: PropTypes.arrayOf(
      PropTypes.shape({
        date: PropTypes.string.isRequired,
        dayName: PropTypes.string.isRequired,
        totalSales: PropTypes.number.isRequired,
      })
    ).isRequired,
  }).isRequired,
};
export default PendingOrders;
