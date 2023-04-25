import React from 'react';
import logo from './logo.svg';
import './App.css';
import {BrowserRouter, Route, Routes, redirect} from "react-router-dom";
import Flights from "./components/Flights";
import UserFlights from "./components/UserFlights";
import LogIn from "./components/LogIn";
import Register from "./components/Register";
import FlightCreate from "./components/FlightCreate";
import Tickets from "./components/Tickets";
import HomeUser from "./components/HomeUser";
import HomeAdmin from "./components/HomeAdmin";


function App() {
    var uloge = localStorage.getItem("role")
    if(uloge == null || !uloge.includes("ROLE_USER")){
        var redirectTo = redirect("/flights")
    }
  return (
    <>
      <BrowserRouter>
        <Routes>
            <Route index element={<LogIn />} />
            <Route path="/" element={<LogIn />}></Route>
            <Route path="/register" element={<Register />} />
            <Route path="/flights" element={<Flights />} />
            <Route path="/userflights" element={<UserFlights />} />
            <Route path="/createflights" element={<FlightCreate />} />
            <Route path="/tickets" element={<Tickets />} />
            <Route path="/userhome" element={<HomeUser />} />
            <Route path="/adminhome" element={<HomeAdmin />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
