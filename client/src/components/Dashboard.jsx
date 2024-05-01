import { CiMenuKebab } from "react-icons/ci";
import ApexLineChart from "./ApexLineChart";
import LineChart from "./LineChart";
import BarChart from "./BarChart";
import BubbleChart from "./BubbleChart";
import TodaysOrders from "./TodaysOrders";
import TotalSales from "./TotalSales";
import { MdArrowDownward, MdArrowUpward } from "react-icons/md";
import PendingOrders from "./PendingOrders";
import Orders from "./Orders";
import { orders } from "./constant.js";
import { useState } from "react";
import DummyTable from "./DummyTable";
// import { DataTableDemo } from "./DataTableDemo";

const Dash1 = () => {
  return (
    <>
      <div className='flex min-h-[200px] p-4 w-full md:w-[33%]  rounded-md justify-between bg-gray-200 text-black '>
        <div className='flex flex-col  items-start justify-between'>
          <div className='text-start'>
            <h4 className='font-semibold'>Total Sales & Cost</h4>
            <p className='text-xs px-1 text-gray-500'>Last 7 days</p>
          </div>
          <div className='text-start '>
            <h4 className='font-bold pl-1 text-2xl'>$350K</h4>
            <p className=' text-gray-500 text-xs'>
              <span className='text-green-600'>🔝8.56k</span> vs last 7 days
            </p>
          </div>
        </div>
        <div className='w-full max-w-[65%] h-full '>
          <ApexLineChart />
        </div>
      </div>
    </>
  );
};
const Dash2 = () => {
  return (
    <div className='w-full h-full rounded-md py-2 bg-red-200'>
      <div className='flex justify-start items-center'>
        <div className='min-h-10 min-w-10  bg-red-700 rounded-full mx-1'></div>
        <div className='w-[25%] flex flex-col justify-center items-start'>
          <h4 className='font-semibold'>16.5K</h4>
          <p className='text-xs text-start text-gray-500'>United States</p>
        </div>
        <div className='w-[45%] flex items-center m-2 relative'>
          <div className='min-w-[100%] absolute bg-blue-300 h-2 z-10 rounded-full'></div>
          <div className='min-w-[100px] absolute bg-blue-800 z-20 h-2 rounded-full'></div>
        </div>

        <p className=' w-[20%] text-sm  text-green-500'>
          <span>🔝</span> 25.8%
        </p>
      </div>
    </div>
  );
};

const Dashboard = () => {
  const [lineData, setLineData] = useState(orders.customersData || []);
  const [lineTab, setLineTab] = useState(1);
  return (
    <div className='w-full flex flex-col h-full overflow-auto'>
      <div className='flex max-w-full max-h-[300px] flex-col sm:flex-row px-4 my-2 gap-3'>
        <TotalSales />
        <PendingOrders ordersData={orders.pendingOrders} />
      </div>

      <div className='flex max-w-full max-h-[300px] flex-col md:flex-row px-4 my-2 gap-3'>
        <Orders ordersData={orders.allOrders} />
        <Orders ordersData={orders.pendingOrders} />
        <Orders ordersData={orders.returnOrders} />
        {/* <Dash1 />
        <Dash1 /> */}
      </div>

      <div className='flex max-w-full flex-col md:flex-row px-4 py-2 gap-3'>
        <div className='px-4 flex w-full md:w-[60%]  h-full flex-col gap-4 py-3 rounded-md bg-gray-200 text-black '>
          <div className='text-start'>
            <h4 className='font-semibold'>Reports</h4>
            <p className='text-xs px-1 text-gray-500'>Last 7 days</p>
          </div>

          <div className='flex flex-col  rounded-md  p-2 w-full h-full '>
            <div className='flex gap-4'>
              <div
                className={`text-start cursor-pointer ${
                  lineTab == 1 ? "border-cyan-500  border-b-2" : ""
                } pb-1`}
                onClick={() => {
                  setLineTab(1), setLineData(orders.customersData);
                }}
              >
                <h4 className='font-semibold'>24K</h4>
                <p className='text-xs  text-gray-500'>Customers</p>
              </div>
              <div
                className={`text-start cursor-pointer ${
                  lineTab == 2 ? "border-cyan-500  border-b-2" : ""
                } pb-1`}
                onClick={() => {
                  setLineTab(2), setLineData(orders.productAnalytics);
                }}
              >
                <h4 className='font-semibold'>1.5K</h4>
                <p className='text-xs  text-gray-500'>Total Products</p>
              </div>
              <div
                className={`text-start cursor-pointer ${
                  lineTab == 3 ? "border-cyan-500  border-b-2" : ""
                } pb-1`}
                onClick={() => {
                  setLineTab(3), setLineData(orders.productStockData);
                }}
              >
                <h4 className='font-semibold'>1.5K</h4>
                <p className='text-xs  text-gray-500'>Stock Products</p>
              </div>
              <div
                className={`text-start cursor-pointer ${
                  lineTab == 4 ? "border-cyan-500  border-b-2" : ""
                } pb-1`}
                onClick={() => {
                  setLineTab(4), setLineData(orders.productOutOfStockData);
                }}
              >
                <h4 className='font-semibold'>1.5K</h4>
                <p className='text-xs  text-gray-500'>Out Of Stock</p>
              </div>
              <div
                className={`text-start cursor-pointer ${
                  lineTab == 5 ? "border-cyan-500  border-b-2" : ""
                } pb-1`}
                onClick={() => {
                  setLineTab(5), setLineData(orders.revenue);
                }}
              >
                <h4 className='font-semibold'>250K</h4>
                <p className='text-xs  text-gray-500'>Revenue</p>
              </div>
            </div>
            <div className='w-full p-2 mt-1 min-h-[300px] h-full '>
              <LineChart data={lineData} />
            </div>
          </div>
        </div>
        <div className='px-4 flex w-full  md:w-[40%]  h-full flex-col gap-4 py-3 rounded-md bg-gray-200 text-black '>
          <div className='flex flex-col  rounded-md  p-2 w-full h-full '>
            <div className='flex gap-4'>
              <div className='text-start '>
                <h4 className='font-semibold'>Users in last 30 minutes</h4>
                <h4 className='font-semibold'>16.5K</h4>
                <p className='text-xs  text-gray-500'>User per minutes</p>
              </div>
            </div>
            <div className='w-full rounded  m-1  h-full max-h-[150px] bg-blue-200'>
              <BarChart />
            </div>
            <div className='h-full w-full flex flex-col gap-2 '>
              <div className='flex h-full justify-between'>
                <h4 className='font-semibold text-lg text-start'>
                  Top Countries
                </h4>
                <h4 className='font-semibold text-lg text-start'>Sales</h4>
              </div>
              <Dash2 />
              <Dash2 />
              <Dash2 />
              {/* <Dash2 /> */}
            </div>
          </div>
        </div>
      </div>

      <div className='flex max-w-full h-full flex-col sm:flex-row px-4 py-2 gap-3'>
        <div className='flex flex-col min-h-[300px] p-4 w-full md:w-[42%]  rounded-md justify-between bg-gray-200 text-black '>
          <div className='flex pb-2 items-center justify-between'>
            <h4 className='font-semibold'>Top celling category</h4>
            <CiMenuKebab />
          </div>

          <div className='w-full  h-full '>
            <BubbleChart categoryData={orders.topCategories} />
          </div>
        </div>

        <div className='flex flex-col max-h-[300px] h-full  p-4 w-full md:w-[56%]  rounded-md justify-between bg-gray-200 text-black '>
          <div className='flex pb-2 items-center justify-between'>
            <h4 className='font-semibold'>Return Orders</h4>
            <h4 className='text-blue-500'>View All </h4>
          </div>
          <div className='w-full h-full bg-blue-200'>
            <DummyTable />{" "}
          </div>
        </div>
      </div>

      <div className='flex max-w-full h-full flex-col sm:flex-row px-4 py-2 gap-3'>
        <div className='flex flex-col min-h-[300px] h-full  p-4 w-full md:w-[56%]  rounded-md justify-between bg-gray-200 text-black '>
          <div className='flex pb-2 items-center justify-between'>
            <h4 className='font-semibold'>Return Orders</h4>
            <h4 className='text-blue-500'>View All </h4>
          </div>
          <div className='w-full h-full bg-blue-200'> </div>
        </div>

        <div className='flex flex-col min-h-[300px] p-4 w-full md:w-[42%]  rounded-md justify-between bg-gray-200 text-black '>
          <div className='flex pb-2 items-center justify-between'>
            <h4 className='font-semibold'>Top celling category</h4>
            <CiMenuKebab />
          </div>

          <div className='w-full  h-full bg-blue-200'></div>
        </div>
      </div>
      <div className='flex max-w-full h-full flex-col sm:flex-row px-4 py-2 gap-3'>
        <div className='flex flex-col min-h-[300px] p-4 w-full md:w-[42%]  rounded-md justify-between bg-gray-200 text-black '>
          <div className='flex pb-2 items-center justify-between'>
            <h4 className='font-semibold'>Todays Orders</h4>
            <CiMenuKebab />
          </div>

          <div className='w-full  h-full bg-blue-200'>
            <TodaysOrders />
          </div>
        </div>

        <div className='flex flex-col min-h-[300px] h-full  p-4 w-full md:w-[56%]  rounded-md justify-between bg-gray-200 text-black '>
          <div className='flex pb-2 items-center justify-between'>
            <h4 className='font-semibold'>Return Orders</h4>
            <h4 className='text-blue-500'>View All </h4>
          </div>
          <div className='w-full h-full bg-blue-200'> </div>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
