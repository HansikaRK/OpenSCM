import { Package } from 'lucide-react';
import { Link, useNavigate } from 'react-router-dom';
import Button from './Button';

export default function Navigation() {
  const navigate = useNavigate();

  const handleLoginClick = () => {
    navigate('/login');
  };

  const handleRegisterClick = () => {
    navigate('/register');
  };

  return (
    <nav className="relative z-50 px-6 py-4">
      <div className="max-w-7xl mx-auto flex items-center justify-between">
        <Link to="/" className="flex items-center space-x-3">
          <div className="w-10 h-10 bg-green-500 rounded-xl flex items-center justify-center">
            <Package className="h-6 w-6 text-black" />
          </div>
          <span className="text-2xl font-bold text-white">OpenSCM</span>
        </Link>
        <div className="hidden md:flex items-center space-x-8">
          <a href="#features" className="hover:text-green-400 transition-colors">Features</a>
          <a href="#about" className="hover:text-green-400 transition-colors">About</a>
          <a href="#contact" className="hover:text-green-400 transition-colors">Contact</a>
          <div className="flex items-center space-x-3">
            <Button 
              variant="secondary" 
              size="sm" 
              onClick={handleLoginClick}
            >
              Login
            </Button>
            <Button 
              variant="primary" 
              size="sm" 
              onClick={handleRegisterClick}
            >
              Register
            </Button>
          </div>
        </div>
      </div>
    </nav>
  );
}
