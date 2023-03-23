import React from 'react';
import logo from './logo.svg';
import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Flights from "./components/Flights";

function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
            <Route path="/flights" element={<Flights />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
