import React from 'react';
import logo from './logo.svg';
import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Flights from "./components/Flights";
import UserFlights from "./components/UserFlights";

function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
            <Route path="/flights" element={<Flights />} />
            <Route path="/userflights" element={<UserFlights />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
