import { useState } from 'react';
import { Link, useNavigate, useLocation } from 'react-router-dom';
import { useAuth } from '../hooks/useAuth';

export default function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const { login } = useAuth();
  const navigate = useNavigate();
  const location = useLocation();

  const from = location.state?.from?.pathname || '/dashboard';

  const handleSubmit = (e) => {
    e.preventDefault();
    // Demo authentication - any email/password combination works
    if (login(email, password)) {
      navigate(from, { replace: true });
    }
  };

  return (
    <div className="min-h-screen bg-black text-white flex items-center justify-center">
      <div className="max-w-md w-full space-y-8 p-8">
        <div className="text-center">
          <h1 className="text-4xl font-bold mb-4">Login</h1>
          <p className="text-gray-400 mb-6">Sign in to access your dashboard</p>
        </div>
        
        <form onSubmit={handleSubmit} className="space-y-6">
          <div>
            <label htmlFor="email" className="block text-sm font-medium text-gray-300 mb-2">
              Email
            </label>
            <input
              id="email"
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              className="w-full px-3 py-2 bg-gray-900 border border-gray-600 rounded-lg text-white focus:outline-none focus:border-green-400"
              placeholder="Enter your email"
              required
            />
          </div>
          
          <div>
            <label htmlFor="password" className="block text-sm font-medium text-gray-300 mb-2">
              Password
            </label>
            <input
              id="password"
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              className="w-full px-3 py-2 bg-gray-900 border border-gray-600 rounded-lg text-white focus:outline-none focus:border-green-400"
              placeholder="Enter your password"
              required
            />
          </div>
          
          <button
            type="submit"
            className="w-full bg-green-500 hover:bg-green-600 text-black font-medium py-2 px-4 rounded-lg transition-colors"
          >
            Sign In
          </button>
        </form>
        
        <div className="text-center space-y-4">
          <p className="text-gray-400 text-sm">
            Demo: Use any email and password to login
          </p>
          <div className="space-y-2">
            <Link 
              to="/" 
              className="block bg-gray-800 hover:bg-gray-700 text-white font-medium px-6 py-2 rounded-lg transition-colors"
            >
              Back to Home
            </Link>
            <Link 
              to="/register" 
              className="block border border-gray-600 hover:border-green-400 text-white font-medium px-6 py-2 rounded-lg transition-colors"
            >
              Don't have an account? Register
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
}
