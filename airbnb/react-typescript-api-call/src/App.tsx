import React from 'react';
import logo from './logo.svg';
import './App.css';
import {BrowserRouter, Route, Routes, redirect} from "react-router-dom";
import LogIn from "./components/LogIn";
import Register from "./components/Register";
import UpdateProfile from './components/UpdateProfile';
import CreateSuite from './components/CreateSuite';
import Suites from './components/Suites';
import UpdateSuite from './components/UpdateSuite';
import SuitesGuest from "./components/SuitesGuest";
import ReservationsHost from "./components/ReservationsHost";
import ReservationsGuest from "./components/ReservationsGuest";
import HomeGuest from "./components/HomeGuest";
import HomeHost from "./components/HomeHost";

function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
            <Route index element={<LogIn />} />
            <Route path="/" element={<LogIn />}></Route>
            <Route path="/register" element={<Register />} />
            <Route path="/profile" element={<UpdateProfile />} />
            <Route path="/createsuites" element={<CreateSuite/>}/>
            <Route path="/suites" element={<Suites/>}/>
            <Route path="/updatesuite" element={<UpdateSuite/>}/>
            <Route path="/suitesguest" element={<SuitesGuest/>}/>
            <Route path="/reservationshost" element={<ReservationsHost/>}/>
            <Route path="/reservationsguest" element={<ReservationsGuest/>}/>
            <Route path="/homeguest" element={<HomeGuest />} />
            <Route path="/homehost" element={<HomeHost />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
