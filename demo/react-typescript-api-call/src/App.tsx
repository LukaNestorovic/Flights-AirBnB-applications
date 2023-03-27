import React from 'react';
import logo from './logo.svg';
import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Flights from "./components/Flights";
import UserFlights from "./components/UserFlights";
import LogIn from "./components/LogIn";
import Register from "./components/Register";
import FlightCreate from "./components/FlightCreate";
import Tickets from "./components/Tickets";


function App() {
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
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
