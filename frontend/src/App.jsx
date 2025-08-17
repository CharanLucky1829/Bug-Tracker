import React, { useState, useEffect } from "react";
import axios from "axios";
import BugList from "./components/BugList.jsx";
import BugForm from "./components/BugForm.jsx";
import Register from "./components/Register.jsx";
import Login from "./components/Login.jsx";

const api = import.meta.env.VITE_API_BASE_URL;

export default function App() {
  const [bugs, setBugs] = useState([]);
  const [users, setUsers] = useState([]);
  const [token, setToken] = useState(localStorage.getItem("token") || null);

  const loadData = async () => {
    if (!token) return;

    const bugsRes = await axios.get(`${api}/bugs`, {
      headers: { Authorization: `Bearer ${token}` },
    });

    const usersRes = await axios.get(`${api}/users`, {
      headers: { Authorization: `Bearer ${token}` },
    });

    setBugs(bugsRes.data);
    setUsers(usersRes.data);
  };

  useEffect(() => {
    loadData();
  }, [token]);

  const handleSave = async (bug) => {
    await axios.post(`${api}/bugs`, bug, {
      headers: { Authorization: `Bearer ${token}` },
    });
    loadData();
  };

  const handleDelete = async (id) => {
    await axios.delete(`${api}/bugs/${id}`, {
      headers: { Authorization: `Bearer ${token}` },
    });
    loadData();
  };

  // if no token -show Register -Login page
  if (!token) {
    return (
      <div className="container my-4">
        <h1>🐞 Bug Tracker</h1>
        <div className="row">
          <div className="col-md-6">
            <Register />
          </div>
          <div className="col-md-6">
            <Login onLogin={setToken} />
          </div>
        </div>
      </div>
    );
  }

  // if logged in
  return (
    <div className="container my-4">
      <h1>🐞 Bug Tracker</h1>
      <button
        className="btn btn-danger mb-3"
        onClick={() => {
          localStorage.removeItem("token");
          setToken(null);
        }}
      >
        Logout
      </button>
      <BugForm onSave={handleSave} users={users} />
      <hr />
      <BugList bugs={bugs} onDelete={handleDelete} />
    </div>
  );
}