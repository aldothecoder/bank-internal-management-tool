import React, { useEffect, useState } from "react";
import "../styles.css";

const RequestList = () => {
  const [requests, setRequests] = useState([]);

  useEffect(() => {
    fetchRequests();
  }, []);

  const fetchRequests = () => {
    fetch("http://localhost:8080/requests")
      .then((res) => res.json())
      .then((data) => {
        // Sort by ID ascending
        const sorted = data.sort((a, b) => a.id - b.id);
        setRequests(sorted);
      })
      .catch((err) => console.error(err));
  };

  const updateStatus = (id, status) => {
    fetch(`http://localhost:8080/requests/${id}/status?status=${status}`, {
      method: "PATCH",
    })
      .then(() => fetchRequests())
      .catch((err) => console.error(err));
  };

  const toggleCompliance = (id, current) => {
    fetch(
      `http://localhost:8080/requests/${id}/compliance?complianceVerified=${!current}`,
      { method: "PATCH" }
    )
      .then(() => fetchRequests())
      .catch((err) => console.error(err));
  };

  const deleteRequest = (id) => {
    fetch(`http://localhost:8080/requests/${id}`, { method: "DELETE" })
      .then(() => fetchRequests())
      .catch((err) => console.error(err));
  };

  return (
    <div className="container">
      <h2>Client Requests</h2>
      {requests.length === 0 ? (
        <p>No requests yet.</p>
      ) : (
        <div className="request-table">
          {requests.map((req) => (
            <div key={req.id} className="request-row">
              <span>
                <strong>Name:</strong> {req.clientName}
              </span>
              <span>
                <strong>Type:</strong> {req.requestType}
              </span>
              <span>
                <strong>Status:</strong>{" "}
                <span className={`status ${req.status}`}>{req.status}</span>
              </span>
              <span>
                <strong>Compliance:</strong>{" "}
                {req.complianceVerified ? "Yes" : "No"}
              </span>
              <div className="button-group">
                <button onClick={() => updateStatus(req.id, "Approved")}>
                  Approve
                </button>
                <button onClick={() => updateStatus(req.id, "Denied")}>
                  Deny
                </button>
                <button
                  onClick={() =>
                    toggleCompliance(req.id, req.complianceVerified)
                  }
                >
                  Toggle Compliance
                </button>
                <button onClick={() => deleteRequest(req.id)}>Delete</button>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default RequestList;
