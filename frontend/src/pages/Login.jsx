import { Link } from 'react-router-dom';

export default function Login() {
  return (
    <div className="min-h-screen bg-black text-white flex items-center justify-center">
      <div className="text-center">
        <h1 className="text-4xl font-bold mb-4">Login Page</h1>
        <p className="text-gray-400 mb-6">This page is under construction</p>
        <div className="space-y-4">
          <Link 
            to="/" 
            className="block bg-green-500 hover:bg-green-600 text-black font-medium px-6 py-2 rounded-lg transition-colors"
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
  );
}
