import React, { useState } from "react";

const NewRequestForm = ({ onRequestCreated }) => {
  const [clientName, setClientName] = useState("");
  const [requestType, setRequestType] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();

    const newRequest = { clientName, requestType };

    fetch("http://localhost:8080/requests", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(newRequest),
    })
      .then((res) => res.json())
      .then((data) => {
        onRequestCreated(data); // notify parent to refresh list
        setClientName(""); ///*input fields are cleared so the
        setRequestType(""); //form is ready for a new entry*/
      })
      .catch((err) => console.error(err));
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        type="text"
        placeholder="Client Name"
        value={clientName}
        onChange={(e) => setClientName(e.target.value)}
        required
      />
      <input
        type="text"
        placeholder="Request Type"
        value={requestType}
        onChange={(e) => setRequestType(e.target.value)}
        required
      />
      <button type="submit">Create Request</button>
    </form>
  );
};

export default NewRequestForm;
