import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../../hooks/useAuth';
import AuthLayout from '../../layouts/AuthLayout';
import { Button, TextInput, Card } from '../../components/common';

export default function Register() {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [documents, setDocuments] = useState([]);
  const [isSupplier, setIsSupplier] = useState(false);
  const { register } = useAuth();
  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
    // Demo registration - pass documents only if supplier mode
    const documentsToPass = isSupplier ? documents : [];
    if (register(email, password, name, documentsToPass)) {
      navigate('/dashboard');
    }
  };

  return (
    <AuthLayout>
      <div className="flex items-center justify-center py-12 px-4 pt-28">
        <div className="max-w-md w-full space-y-8 p-8">
          <Card variant="transparent" className="border-emerald-500/20">
            <div className="text-center mb-6">
              <h1 className="text-4xl font-bold mb-4">
                {isSupplier ? 'Register as Supplier' : 'Register'}
              </h1>
              <p className="text-gray-400">
                {isSupplier 
                  ? 'Create your supplier account to get started' 
                  : 'Create your account to get started'
                }
              </p>
            </div>

            {/* Registration type toggle */}
            <div className="mb-6">
              <div className="flex items-center justify-center space-x-4">
                <button
                  type="button"
                  onClick={() => setIsSupplier(false)}
                  className={`px-4 py-2 rounded-md text-sm font-medium transition-colors ${
                    !isSupplier 
                      ? 'bg-green-500 text-white' 
                      : 'bg-gray-700 text-gray-300 hover:bg-gray-600'
                  }`}
                >
                  Customer
                </button>
                <button
                  type="button"
                  onClick={() => setIsSupplier(true)}
                  className={`px-4 py-2 rounded-md text-sm font-medium transition-colors ${
                    isSupplier 
                      ? 'bg-green-500 text-white' 
                      : 'bg-gray-700 text-gray-300 hover:bg-gray-600'
                  }`}
                >
                  Supplier
                </button>
              </div>
            </div>

            <form onSubmit={handleSubmit} className="space-y-6">
              <div>
                <label htmlFor="name" className="block text-sm font-medium text-gray-300 mb-2">
                  Full Name
                </label>
                <TextInput
                  id="name"
                  type="text"
                  value={name}
                  onChange={(e) => setName(e.target.value)}
                  placeholder="Enter your full name"
                  variant="default"
                  required
                />
              </div>
          
              <div>
                <label htmlFor="email" className="block text-sm font-medium text-gray-300 mb-2">
                  Email
                </label>
                <TextInput
                  id="email"
                  type="email"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  placeholder="Enter your email"
                  variant="default"
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
                  onChange={(e) => setPassword(e.target.value)}
                  placeholder="Enter your password"
                  variant="default"
                  required
                />
              </div>

              {/* Document upload - only show for suppliers */}
              {isSupplier && (
                <div>
                  <label htmlFor="documents" className="block text-sm font-medium text-gray-300 mb-2">
                    Upload documents
                  </label>
                  <input
                    id="documents"
                    type="file"
                    accept=".pdf,.png,.jpg,.jpeg"
                    multiple
                    onChange={(e) => setDocuments(Array.from(e.target.files))}
                    className="block w-full text-sm text-gray-300 file:bg-emerald-700 file:text-white file:px-3 file:py-2 file:rounded-md"
                  />
                  {documents.length > 0 && (
                    <div className="mt-2 text-sm text-gray-300">
                      <div className="font-medium">Selected files:</div>
                      <ul className="mt-1 list-disc list-inside">
                        {documents.map((f, idx) => (
                          <li key={idx}>{f.name} <span className="text-xs text-gray-400">({Math.round(f.size/1024)} KB)</span></li>
                        ))}
                      </ul>
                    </div>
                  )}
                </div>
          )}

              <Button
                type="submit"
                variant="primary"
                size="md"
                className="w-full bg-emerald-500 hover:bg-emerald-600"
              >
                Create Account
              </Button>
              
            </form>

            <div className="text-center space-y-4 mt-3">
              <div className="space-y-2">
                <Link to="/">
                  <Button 
                    variant="secondary" 
                    size="md" 
                    className="w-full bg-gray-800 hover:bg-gray-700"
                  >
                    Back to Home
                  </Button>
                </Link>
                 <p className="text-sm text-gray-300 mt-2">
                  Already have an account?{' '}
                  <Link to="/login" className="text-emerald-500 hover:underline">
                    Login
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
