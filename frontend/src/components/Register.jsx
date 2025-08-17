import React, { useState } from "react";
import axios from "axios";
import { UserPlus } from "lucide-react"; // nice icon
import './Auth.css';

const api = import.meta.env.VITE_API_BASE_URL;

export default function Register() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [message, setMessage] = useState("");

  const handleRegister = async (e) => {
    e.preventDefault();
    try {
      const res = await axios.post(`${api}/auth/register`, { email, password });
      setMessage(res.data.message || "✅ User registered successfully");
      setEmail("");
      setPassword("");
    } catch (err) {
      setMessage(err.response?.data?.message || "❌ Registration failed");
    }
  };

  return (
    <div className="flex items-center justify-center min-h-screen bg-gradient-to-br from-blue-50 to-blue-100">
      <div className="bg-white p-8 rounded-2xl shadow-xl w-full max-w-md">
        <div className="flex items-center justify-center mb-6">
          <UserPlus className="w-10 h-10 text-blue-600 mr-2" />
          <h2 className="text-2xl font-bold text-gray-800">Create Account</h2>
        </div>

        <form onSubmit={handleRegister} className="space-y-4">
          <input
  type="email"
  placeholder="Enter email"
  value={email}
  onChange={(e) => setEmail(e.target.value)}
  required
  className="auth-input w-full p-3 border rounded-lg focus:ring-2 focus:ring-blue-400 focus:outline-none"
/>
          <input
            type="password"
            placeholder="Enter password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
            className="w-full p-3 border rounded-lg focus:ring-2 focus:ring-blue-400 focus:outline-none"
          />
          <button
  type="submit"
  className="auth-btn login"
>
  Register
</button>
        </form>

        {message && (
          <p
            className={`mt-4 text-center font-medium ${
              message.includes("✅") ? "text-green-600" : "text-red-600"
            }`}
          >
            {message}
          </p>
        )}

        <p className="text-sm text-center mt-4 text-gray-600">
          Already have an account?{" "}
          <a href="/login" className="text-blue-600 hover:underline">
            Login
          </a>
        </p>
      </div>
    </div>
  );
}
