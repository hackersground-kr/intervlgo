import React, { useEffect, useState } from "react";
import axios from "axios";
import Home from "./first/home";


function App() {
  const [hello, setHello] = useState("");

  useEffect(() => {
    axios
      .post("/api/user/join")
      .then((response) => setHello(response.data))
      .catch((error) => console.log(error));
  }, []);

  return (
    <div><Home/>
    </div>
  );
}

export default App;
