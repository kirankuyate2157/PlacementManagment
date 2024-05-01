import React from "react";
import Chart from "react-apexcharts";

const ApexLineChart = () => {
  const options = {
    series: [
      {
        name: "series1",
        data: [31, 40, 28, 51, 42, 109, 100],
      },
      {
        name: "series2",
        data: [11, 32, 45, 32, 34, 52, 41],
      },
    ],
    chart: {
      height: 350,
      type: "area",
      toolbar: {
        show: false, // Hide toolbar (including zoom option)
      },
      background: "transparent", // Set background to transparent
    },
    dataLabels: {
      enabled: false,
    },
    stroke: {
      curve: "smooth",
    },
    xaxis: {
      type: "datetime",
      categories: [
        "2018-09-19T00:00:00.000Z",
        "2018-09-19T01:30:00.000Z",
        "2018-09-19T02:30:00.000Z",
        "2018-09-19T03:30:00.000Z",
        "2018-09-19T04:30:00.000Z",
        "2018-09-19T05:30:00.000Z",
        "2018-09-19T06:30:00.000Z",
      ],
    },
    // tooltip: {
    //   enabled: false, // Disable tooltip
    // },
    yaxis: {
      show: false, // Hide Y-axis
    },
    annotations: {
      xaxis: [
        {
          x: 0,
          x2: 6,
          borderColor: "transparent",
          label: {
            text: "series1",
            style: {
              color: "#fff",
              background: "#00E396",
            },
          },
        },
        {
          x: 0,
          x2: 6,
          borderColor: "transparent",
          label: {
            text: "series2",
            style: {
              color: "#fff",
              background: "#FEB019",
            },
          },
        },
      ],
    },
    grid: {
      show: false, // Hide grid lines
    },
  };

  return (
    <div className='w-full h-full' style={{ background: 'transparent' }}>
      {/* Chart will be rendered here */}
      <Chart options={options} series={options.series} type='area' height={320} />
    </div>
  );
};

export default ApexLineChart;
