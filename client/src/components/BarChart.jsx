import React from "react";
import Chart from "react-apexcharts";

const BarChart = () => {
  const data = [
    { x: "2019/01/01", y: 400 },
    { x: "2019/04/01", y: 430 },
    { x: "2019/07/01", y: 448 },
    { x: "2019/10/01", y: 470 },
    { x: "2020/01/01", y: 540 },
    { x: "2020/04/01", y: 580 },
    { x: "2020/07/01", y: 690 },
    { x: "2020/10/01", y: 690 },
  ];

  const options = {
    chart: {
      type: "bar",
      height: 380,
      toolbar: {
        show: false,
      },
    },
    plotOptions: {
      bar: {
        horizontal: false,
        columnWidth: "55%",
        endingShape: "rounded",
      },
    },
    dataLabels: {
      enabled: false,
    },
    xaxis: {
      type: "category",
      labels: {
        formatter: function (val) {
          return "Q" + Math.ceil((new Date(val).getMonth() + 1) / 3); // Calculate quarter from date
        },
        style: {
          fontSize: "10px",
          fontWeight: 700,
        },
      },
    },
    yaxis: {
      show: false,
    },
    grid: {
      show: false,
    },
    tooltip: {
      x: {
        formatter: function (val) {
          const date = new Date(val);
          const quarter = Math.ceil((date.getMonth() + 1) / 3);
          return `Q${quarter} ${date.getFullYear()}`;
        },
      },
    },
  };

  return (
    <div className='h-full w-full'>
      <Chart options={options} series={[{ data }]} type='bar' height={150} />
    </div>
  );
};

export default BarChart;
