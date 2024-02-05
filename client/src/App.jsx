import { useState } from "react";
import "./App.css";
import Sidebar from "./components/Sidebar";
import Navbar from "./components/Navbar";
import TopBar from "./components/TopBar";

function App() {
  return (
    <div className='flex dark p-0 m-0 w-[100vw] h-[100vh]'>
      <Sidebar />
      <div className='w-full'>
      <TopBar/>
      <Navbar/>
      <h2>hjbcebjhebj</h2>
      </div>
    </div>
  );
}

export default App;
