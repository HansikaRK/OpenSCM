import React, { useState } from 'react';
import { Menu, X, Kanban} from 'lucide-react';
import { Button } from '../common';

const LandingNavBar = () => {
  const [mobileMenuOpen, setMobileMenuOpen] = useState(false);

  const navigationItems = [
    { href: "#features", label: "Features" },
    { href: "#about", label: "About" },
    { href: "#pricing", label: "Pricing" },
    { href: "#docs", label: "Documentation" }
  ];

  return (
    <nav className="fixed top-0 w-full bg-black/95 backdrop-blur-md border-b border-emerald-700/10 shadow-[0_4px_30px_rgba(16,185,129,0.1)] z-50">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex justify-between items-center h-16">
          {/* Logo */}
          <div className="flex items-center space-x-2">
            <div className="w-9 h-9 rounded-lg flex items-center justify-center">
              <Kanban className="w-5 h-5 text-green-500" />
            </div>
            <span className="text-xl font-bold">
              <span className="text-white">Open</span>
              <span className="text-green-500">SCM</span>
            </span>
          </div>

          {/* Desktop Navigation */}
          <div className="hidden md:flex items-center space-x-8">
            {navigationItems.map((item) => (
              <a
                key={item.href}
                href={item.href}
                className="text-gray-400 hover:text-green-500 transition-colors"
              >
                {item.label}
              </a>
            ))}
          </div>

          {/* Auth Buttons */}
          <div className="hidden md:flex items-center space-x-4">
            <Button variant="secondary" size="sm" className="text-gray-400 hover:text-white">
              Login
            </Button>
            <Button variant="primary" size="sm" className="bg-emerald-500 hover:bg-emerald-600">
              Register
            </Button>
          </div>

          {/* Mobile Menu Button */}
          <button
            className="md:hidden text-gray-400"
            onClick={() => setMobileMenuOpen(!mobileMenuOpen)}
          >
            {mobileMenuOpen ? <X className="w-6 h-6" /> : <Menu className="w-6 h-6" />}
          </button>
        </div>
      </div>

      {/* Mobile Menu */}
      {mobileMenuOpen && (
        <div className="md:hidden bg-black border-t border-emerald-500/10">
          <div className="px-4 py-4 space-y-3">
            {navigationItems.map((item) => (
              <a
                key={item.href}
                href={item.href}
                className="block text-gray-400 hover:text-emerald-500 py-2"
                onClick={() => setMobileMenuOpen(false)}
              >
                {item.label}
              </a>
            ))}
            <div className="pt-4 border-t border-gray-800 space-y-2">
              <Button variant="ghost" size="sm" className="w-full text-gray-400 hover:text-white">
                Login
              </Button>
              <Button variant="primary" size="sm" className="w-full bg-emerald-500 hover:bg-emerald-600">
                Register
              </Button>
            </div>
          </div>
        </div>
      )}
    </nav>
  );
};

export default LandingNavBar;