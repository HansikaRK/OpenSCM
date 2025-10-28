import React from 'react';
import { LandingNavBar, Footer } from '../components/landing';

const LandingLayout = ({ children }) => {
  return (
    <div className="min-h-screen bg-black text-white">
      <LandingNavBar />
      <main>
        {children}
      </main>
      <Footer />
    </div>
  );
};

export default LandingLayout;