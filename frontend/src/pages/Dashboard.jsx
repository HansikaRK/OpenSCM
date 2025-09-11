import { Link } from 'react-router-dom';

export default function Dashboard() {
  return (
    <div className="min-h-screen bg-black text-white">
      {/* Simple Navigation */}
      <nav className="bg-gray-900 p-4">
        <div className="max-w-7xl mx-auto flex items-center justify-between">
          <h1 className="text-xl font-bold text-green-400">OpenSCM Dashboard</h1>
          <div className="space-x-4">
            <Link 
              to="/" 
              className="text-gray-300 hover:text-white transition-colors"
            >
              Home
            </Link>
            <button className="bg-red-500 hover:bg-red-600 text-white px-4 py-2 rounded transition-colors">
              Logout
            </button>
          </div>
        </div>
      </nav>

      {/* Dashboard Content */}
      <div className="max-w-7xl mx-auto p-6">
        <div className="mb-8">
          <h2 className="text-3xl font-bold mb-4">Welcome to Your Dashboard</h2>
          <p className="text-gray-400">This is a protected route that requires authentication.</p>
        </div>

        {/* Dashboard Grid */}
        <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-6">
          <div className="bg-gray-900 rounded-lg p-6">
            <h3 className="text-xl font-semibold mb-2 text-green-400">Supply Chain Overview</h3>
            <p className="text-gray-400">Monitor your supply chain performance and metrics.</p>
          </div>
          
          <div className="bg-gray-900 rounded-lg p-6">
            <h3 className="text-xl font-semibold mb-2 text-green-400">Inventory Management</h3>
            <p className="text-gray-400">Track and manage your inventory levels in real-time.</p>
          </div>
          
          <div className="bg-gray-900 rounded-lg p-6">
            <h3 className="text-xl font-semibold mb-2 text-green-400">Analytics & Reports</h3>
            <p className="text-gray-400">View detailed analytics and generate reports.</p>
          </div>
          
          <div className="bg-gray-900 rounded-lg p-6">
            <h3 className="text-xl font-semibold mb-2 text-green-400">Supplier Network</h3>
            <p className="text-gray-400">Manage your supplier relationships and performance.</p>
          </div>
          
          <div className="bg-gray-900 rounded-lg p-6">
            <h3 className="text-xl font-semibold mb-2 text-green-400">Order Processing</h3>
            <p className="text-gray-400">Handle orders and track their processing status.</p>
          </div>
          
          <div className="bg-gray-900 rounded-lg p-6">
            <h3 className="text-xl font-semibold mb-2 text-green-400">Risk Management</h3>
            <p className="text-gray-400">Identify and mitigate potential supply chain risks.</p>
          </div>
        </div>

        {/* Example Note */}
        <div className="mt-12 p-6 bg-green-900 bg-opacity-30 border border-green-400 rounded-lg">
          <h4 className="text-lg font-semibold text-green-400 mb-2">Protected Route Example</h4>
          <p className="text-gray-300">
            This dashboard page demonstrates how protected routes work in the OpenSCM application. 
            In a real implementation, this page would only be accessible to authenticated users.
          </p>
        </div>
      </div>
    </div>
  );
}
