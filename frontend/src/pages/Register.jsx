import { Link } from 'react-router-dom';

export default function Register() {
  return (
    <div className="min-h-screen bg-black text-white flex items-center justify-center">
      <div className="text-center">
        <h1 className="text-4xl font-bold mb-4">Register Page</h1>
        <p className="text-gray-400 mb-6">This page is under construction</p>
        <div className="space-y-4">
          <Link 
            to="/" 
            className="block bg-green-500 hover:bg-green-600 text-black font-medium px-6 py-2 rounded-lg transition-colors"
          >
            Back to Home
          </Link>
          <Link 
            to="/login" 
            className="block border border-gray-600 hover:border-green-400 text-white font-medium px-6 py-2 rounded-lg transition-colors"
          >
            Already have an account? Login
          </Link>
        </div>
      </div>
    </div>
  );
}
