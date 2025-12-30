import React, { useState } from "react";
import RequestList from "./components/RequestList.jsx";
import NewRequestForm from "./components/NewRequestForm.jsx";
import "./App.css";

function App() {
  const [refreshKey, setRefreshKey] = useState(0);

  const handleRequestCreated = () => {
    setRefreshKey((oldKey) => oldKey + 1); // triggers re-render of RequestList
  };

  return (
    <div>
      <h1>Bank Internal Management Tool</h1>
      <NewRequestForm onRequestCreated={handleRequestCreated} />
      <RequestList key={refreshKey} />
    </div>
  );
}

export default App;
