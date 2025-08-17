import React, { useState } from "react";
import { LogIn } from "lucide-react";
import './Auth.css';


function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const handleLogin = async (e) => {
    e.preventDefault();
    setError("");

    try {
      const response = await fetch("http://localhost:8080/api/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, password }),
      });

      if (response.ok) {
        const data = await response.json();
        localStorage.setItem("token", data.token);
        alert("✅ Login successful");
        window.location.href = "/dashboard";
      } else {
        setError("❌ Invalid credentials");
      }
    } catch (err) {
      console.error("Login error:", err);
      setError("Something went wrong. Please try again.");
    }
  };

  return (
    <div className="flex items-center justify-center min-h-screen bg-gradient-to-br from-purple-50 to-purple-100">
      <div className="bg-white p-8 rounded-2xl shadow-xl w-full max-w-md">
        <div className="flex items-center justify-center mb-6">
          <LogIn className="w-10 h-10 text-purple-600 mr-2" />
          <h2 className="text-2xl font-bold text-gray-800">Login</h2>
        </div>

        {error && (
          <p className="text-red-600 text-center mb-3 font-medium">{error}</p>
        )}

        <form onSubmit={handleLogin} className="space-y-4">
          <input
            type="email"
            placeholder="Enter email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
            className="w-full p-3 border rounded-lg focus:ring-2 focus:ring-purple-400 focus:outline-none"
          />
         <input
  type="password"
  placeholder="Enter password"
  value={password}
  onChange={(e) => setPassword(e.target.value)}
  required
  className="auth-input w-full p-3 border rounded-lg focus:ring-2 focus:ring-purple-400 focus:outline-none"
/>

<button
  type="submit"
  className="auth-btn login"
>
  Login
</button>
        </form>

        <p className="text-sm text-center mt-4 text-gray-600">
          Don’t have an account?{" "}
          <a href="/register" className="text-purple-600 hover:underline">
            Register
          </a>
        </p>
      </div>
    </div>
  );
}

export default Login;
