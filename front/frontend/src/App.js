import React, { Component, useState, useEffect } from "react";
import { Routes, Route, Link, BrowserRouter } from "react-router-dom";
import axios from "axios";
import Home from "./first/home";
import Singup from "./SingupScene/singup";

function App() {
  const [hello, setHello] = useState("");

  useEffect(() => {
    axios
      .post("/api/user/join")
      .then((response) => setHello(response.data))
      .catch((error) => console.log(error));
  }, []);

  return (
    <BrowserRouter>
      <div>
        <Routes>
          <Route path="/" exact={true} component={Home} />
          <Route path="/Singup" component={Singup} />
        </Routes>
        <Home />
      </div>
    </BrowserRouter>
  );
}

export default App;
