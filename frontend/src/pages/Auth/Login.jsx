import { useState } from 'react';
import { Link, useNavigate, useLocation } from 'react-router-dom';
import { useAuth } from '../../hooks/useAuth';
import AuthLayout from '../../layouts/AuthLayout';
import { Button, TextInput, Card } from '../../components/common';
import { Mail, LockKeyhole } from 'lucide-react';

export default function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [userType, setUserType] = useState('customer'); // 'customer' or 'supplier'
  const { login } = useAuth();
  const navigate = useNavigate();
  const location = useLocation();

  const from = location.state?.from?.pathname || '/dashboard';

  const handleSubmit = (e) => {
    e.preventDefault();
    // Demo authentication - any email/password combination works
    // In a real app, userType would be sent to the login API
    if (login(email, password, userType)) {
      navigate(from, { replace: true });
    }
  };

  return (
    <AuthLayout>
      <div className="flex items-center justify-center py-12 px-4 pt-28">
        <div className="max-w-md w-full space-y-8 p-8">
          <Card variant="transparent" className="border-emerald-500/10">
            <div className="text-center mb-6">
              <h1 className="text-4xl font-bold mb-4">Login</h1>
              <p className="text-gray-400">Sign in to access your dashboard</p>
            </div>

            <form onSubmit={handleSubmit} className="space-y-6">
              
              <div>
                <label htmlFor="email" className="block text-sm font-medium text-gray-300 mb-2">
                  Email
                </label>
                <TextInput
                  id="email"
                  type="email"
                  value={email}
                  leftIcon={<Mail />}
                  onChange={(e) => setEmail(e.target.value)}
                  placeholder="Enter your email"
                  variant="dark"
                  required
                />
              </div>

              <div>
                <label htmlFor="password" className="block text-sm font-medium text-gray-300 mb-2">
                  Password
                </label>
                <TextInput
                  id="password"
                  type="password"
                  value={password}
                  leftIcon={<LockKeyhole />}
                  onChange={(e) => setPassword(e.target.value)}
                  placeholder="Enter your password"
                  variant="dark"
                  required
                />
              </div>

              <div>
                <label htmlFor="userType" className="block text-sm font-medium text-gray-300 mb-2">
                  Login as
                </label>
                <select
                  id="userType"
                  value={userType}
                  onChange={(e) => setUserType(e.target.value)}
                  className="block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-green-500 focus:border-green-500 bg-gray-900 text-white"
                  required
                >
                  <option value="customer">Customer</option>
                  <option value="supplier">Supplier</option>
                </select>
              </div>


              <Button
                type="submit"
                variant="primary"
                size="md"
                className="w-full bg-emerald-500 hover:bg-emerald-600"
              >
                Login
              </Button>
            </form>

            <div className="text-center space-y-4 mt-3">
              <div className="space-y-2">
                <p className="text-sm text-gray-300 mt-3">
                  Don't have an account?{' '}
                  <Link to="/register" className="text-emerald-500 hover:underline">
                    Register
                  </Link>
                </p>
              </div>
            </div>
          </Card>
        </div>
      </div>
    </AuthLayout>
  );
}
