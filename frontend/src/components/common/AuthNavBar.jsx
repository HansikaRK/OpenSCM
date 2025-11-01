import React from 'react';
import { Kanban } from 'lucide-react';
import { Link } from 'react-router-dom';
import Button from "./Button";

const AuthNavBar = () => {
  return (
    <nav className="fixed top-0 w-full bg-gray-950/95 backdrop-blur-md border-b border-emerald-700/10 shadow-[0_4px_30px_rgba(16,185,129,0.1)] z-50">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex justify-between items-center h-16">
          {/* Logo */}
          <Link to="/" className="flex items-center space-x-2">
            <div className="w-9 h-9 rounded-lg flex items-center justify-center">
              <Kanban className="w-5 h-5 text-green-500" />
            </div>
            <span className="text-xl font-bold">
              <span className="text-white">Open</span>
              <span className="text-green-500">SCM</span>
            </span>
          </Link>

          <div className="hidden md:flex items-center space-x-8">
            {['Features', 'About', 'Pricing', 'Docs'].map((item) => (
              <span key={item} className="invisible">{item}</span>
            ))}
          </div>

            
          <div className="hidden md:flex items-center space-x-4 w-[200px]">
             <Link to="/">
                  <Button 
                    variant="secondary" 
                    size="sm" 
                    className="w-full bg-gray-800 hover:bg-gray-700"
                  >
                    Back to Home
                  </Button>
                </Link>
          </div>

          
        </div>
      </div>
    </nav>
  );
};

export default AuthNavBar;
