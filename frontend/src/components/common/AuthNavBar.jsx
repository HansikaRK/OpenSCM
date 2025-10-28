import React from 'react';
import { Kanban } from 'lucide-react';
import { Link } from 'react-router-dom';

const AuthNavBar = () => {
  return (
    <nav className="fixed top-0 w-full bg-black/95 backdrop-blur-md border-b border-emerald-700/10 shadow-[0_4px_30px_rgba(16,185,129,0.1)] z-50">
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

          {/* Middle (kept empty to match landing spacing) */}
          <div className="hidden md:flex items-center space-x-8" />

          
        </div>
      </div>
    </nav>
  );
};

export default AuthNavBar;
