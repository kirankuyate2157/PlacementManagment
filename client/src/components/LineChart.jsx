import React, { useEffect, useRef } from "react";
import Chart from "react-apexcharts";

const customers = [
  {
    count: 1,
    date: "2024-02-07",
    customers: ["7675300479283"],
  },
  {
    count: 1,
    date: "2024-02-09",
    customers: ["7675300479283"],
  },
  {
    count: 1,
    date: "2024-02-17",
    customers: ["7675300479283"],
  },
  {
    count: 3,
    date: "2024-02-21",
    customers: ["7639453139251", "7675300479283", "7714623521075"],
  },
  {
    count: 1,
    date: "2024-02-22",
    customers: ["7675300479283"],
  },
  {
    count: 3,
    date: "2024-02-23",
    customers: ["7675300479283", "7639453139251"],
  },
  {
    count: 3,
    date: "2024-02-24",
    customers: ["7639453139251", "7675300479283"],
  },
  {
    count: 5,
    date: "2024-02-27",
    customers: ["7675300479283", "7639453139251", "7714623521075"],
  },
  {
    count: 16,
    date: "2024-03-01",
    customers: ["7714623521075"],
  },
  {
    count: 1,
    date: "2024-03-07",
    customers: ["7714623521075"],
  },
  {
    count: 1,
    date: "2024-03-09",
    customers: ["7763376800051"],
  },
  {
    count: 1,
    date: "2024-03-14",
    customers: ["7714623521075"],
  },
  {
    count: 2,
    date: "2024-03-16",
    customers: ["7675300479283", "7714623521075"],
  },
  {
    count: 5,
    date: "2024-03-20",
    customers: [
      "7789143163187",
      "7714623521075",
      "7794808652083",
      "7794108530995",
    ],
  },
  {
    count: 1,
    date: "2024-03-23",
    customers: ["7714623521075"],
  },
  {
    count: 1,
    date: "2024-03-24",
    customers: ["7714623521075"],
  },
  {
    count: 1,
    date: "2024-03-31",
    customers: ["7810259616051"],
  },
  {
    count: 1,
    date: "2024-04-07",
    customers: ["7806839554355"],
  },
  {
    count: 1,
    date: "2024-01-01",
    customers: ["1234567890"],
  },
  {
    count: 2,
    date: "2024-01-02",
    customers: ["2345678901", "3456789012"],
  },
  {
    count: 3,
    date: "2024-01-03",
    customers: ["3456789012", "4567890123", "5678901234"],
  },
  {
    count: 1,
    date: "2024-01-04",
    customers: ["6789012345"],
  },
  // Add more entries for each day...
  {
    count: 4,
    date: "2024-04-09",
    customers: ["7890123456", "8901234567", "9012345678", "0123456789"],
  },
  {
    count: 2,
    date: "2024-04-10",
    customers: ["1234567890", "2345678901"],
  },
  {
    count: 3,
    date: "2024-04-11",
    customers: ["2345678901", "3456789012", "4567890123"],
  },
  {
    count: 1,
    date: "2024-01-01",
    customers: ["1234567890"],
  },
  {
    count: 2,
    date: "2024-01-02",
    customers: ["2345678901", "3456789012"],
  },
  {
    count: 3,
    date: "2024-01-03",
    customers: ["3456789012", "4567890123", "5678901234"],
  },
  {
    count: 1,
    date: "2024-01-04",
    customers: ["6789012345"],
  },
  // Continue adding entries for each day up to approximately 100 days...
  {
    count: 4,
    date: "2024-04-09",
    customers: ["7890123456", "8901234567", "9012345678", "0123456789"],
  },
  {
    count: 2,
    date: "2024-04-10",
    customers: ["1234567890", "2345678901"],
  },
  {
    count: 3,
    date: "2024-04-11",
    customers: ["2345678901", "3456789012", "4567890123"],
  },
  // Add more entries for each day...
  {
    count: 1,
    date: "2024-04-12",
    customers: ["5678901234"],
  },
  {
    count: 2,
    date: "2024-04-13",
    customers: ["6789012345", "7890123456"],
  },
  {
    count: 3,
    date: "2024-04-14",
    customers: ["7890123456", "8901234567", "9012345678"],
  },
];

const LineChart = ({ data }) => {
  customers.sort((a, b) => new Date(a.date) - new Date(b.date));
  const chartRef = useRef(null);
  const options = {
    series: [
      {
        name: data?.name,
        data: data.map((value) => value.count) || [],
      },
    ],
    chart: {
      height: 350,
      type: "line",
      toolbar: {
        show: false, // Hide toolbar (including zoom option)
      },
      background: "transparent",
    },
    stroke: {
      width: 5,
      curve: "smooth",
    },
    xaxis: {
      type: "datetime",
      categories: data.map((value) => value.date),
      tickAmount: 10,
      labels: {
        formatter: function (value, timestamp, opts) {
          return opts.dateFormatter(new Date(timestamp), "dd MMM");
        },
      },
    },
    // title: {
    //   text: "Forecast",
    //   align: "left",
    //   style: {
    //     fontSize: "16px",
    //     color: "#666",
    //   },
    // },
    fill: {
      type: "gradient",
      gradient: {
        shade: "dark",
        gradientToColors: ["#FDD835"],
        shadeIntensity: 1,
        type: "horizontal",
        opacityFrom: 1,
        opacityTo: 1,
        stops: [0, 50, 100, 100, 100],
      },
    },
    grid: {
      show: false, // Hide grid lines
    },
  };

  return (
    <div className='sales-forecast-chart'>
      <Chart
        options={options}
        series={options.series}
        type='line'
        height={350}
        ref={chartRef}
      />
    </div>
  );
};

export default LineChart;
