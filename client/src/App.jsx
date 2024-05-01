import { useState } from "react";
import "./App.css";
import { Route, Routes } from "react-router";

import Sidebar from "./components/Sidebar";
import Navbar from "./components/Navbar";
import TopBar from "./components/TopBar";
import { TabsDemo } from "./components/Auth/Auth";
import { Toaster } from "@/components/ui/sonner";
import Dashboard from "./components/Dashboard";

const Home = () => {
  return (
    <>
      <Sidebar />
      <div className='w-full'>
        <TopBar />
        <Navbar />
        <Dashboard />
      </div>
    </>
  );
};
function App() {
  const a = true;
  return (
    <div className='flex dark p-0 m-0 w-[100vw] h-[100vh]'>
      <Toaster />

      <Routes>
        <Route path='/' element={<TabsDemo />} />
        <Route path='/home' element={<Home />} />
      </Routes>
    </div>
  );
}
export default App;
