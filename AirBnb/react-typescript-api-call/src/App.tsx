import React from 'react';
import logo from './logo.svg';
import './App.css';
import {BrowserRouter, Route, Routes, redirect} from "react-router-dom";
import LogIn from "./components/LogIn";
import Register from "./components/Register";
import UpdateProfile from './components/UpdateProfile';

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
            <Route path="/profile" element={<UpdateProfile />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
