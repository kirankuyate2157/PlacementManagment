import Chart from "react-apexcharts";
import { MdArrowDownward, MdArrowUpward } from "react-icons/md";

const TotalSales = () => {
  const salesData = [
    { date: "2024-03-30", dayName: "Saturday", totalSales: 0 },
    { date: "2024-03-31", dayName: "Sunday", totalSales: 2 },
    { date: "2024-04-01", dayName: "Monday", totalSales: 0 },
    { date: "2024-04-02", dayName: "Tuesday", totalSales: 0 },
    { date: "2024-04-03", dayName: "Wednesday", totalSales: 0 },
    { date: "2024-04-04", dayName: "Thursday", totalSales: 0 },
    { date: "2024-04-05", dayName: "Friday", totalSales: 0 },
    { date: "2024-04-06", dayName: "Saturday", totalSales: 0 },
    { date: "2024-04-07", dayName: "Sunday", totalSales: 1 },
    { date: "2024-04-08", dayName: "Monday", totalSales: 0 },
    { date: "2024-04-09", dayName: "Tuesday", totalSales: 0 },
    { date: "2024-04-10", dayName: "Wednesday", totalSales: 0 },
    { date: "2024-04-11", dayName: "Thursday", totalSales: 0 },
    { date: "2024-04-12", dayName: "Friday", totalSales: 0 },
  ];

  // Get the last 7 days from salesData
  const last7DaysData = salesData.slice(0, 7);

  // Extract day names and sales values for the last 7 days
  const categories = last7DaysData.map((item) => item.dayName.slice(0, 3));
  const salesValues = last7DaysData.map((item) => item.totalSales);
  const salesValues2 = salesData.slice(7).map((item) => item.totalSales);

  const options = {
    series: [
      {
        name: "Last Week",
        data: salesValues,
      },
      {
        name: "This Week",
        data: salesValues2,
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
    xaxis: {
      categories: categories,
    },
    yaxis: {
      show: false,
    },
    grid: {
      show: false,
    },
  };

  const totalCost = {
    firstWeek: 0,
    secondWeek: 9897,
  };
  const difference = totalCost.firstWeek - totalCost.secondWeek;

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
  return (
    <div className='flex min-h-[200px] p-4 w-full md:w-[56%]  rounded-md justify-between bg-gray-200 text-black '>
      <div className='flex flex-col  items-start justify-between'>
        <div className='text-start'>
          <h4 className='font-semibold'>Total Sales & Cost</h4>
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

export default TotalSales;
