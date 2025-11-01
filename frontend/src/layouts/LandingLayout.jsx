import React from 'react';
import { LandingNavBar, Footer } from '../components/landing';

const LandingLayout = ({ children }) => {
  return (
    <div className="min-h-screen bg-gray-950 text-white">
      <LandingNavBar />
      <main>
        {children}
      </main>
      <Footer />
    </div>
  );
};

export default LandingLayout;