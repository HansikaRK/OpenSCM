import React from 'react';
import AuthNavBar from '../components/common/AuthNavBar';

const AuthLayout = ({ children }) => {
  return (
    <div className="min-h-screen bg-black text-white">
      <AuthNavBar />
      <main>
        {children}
      </main>
    </div>
  );
};

export default AuthLayout;