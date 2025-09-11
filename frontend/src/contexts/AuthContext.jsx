import { useState } from 'react';
import { AuthContext } from './AuthContextProvider';

export function AuthProvider({ children }) {
  // Simple mock authentication state
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [user, setUser] = useState(null);

  const login = (email, password) => {
    // Mock login logic - in real app, this would call an API
    if (email && password) {
      setIsAuthenticated(true);
      setUser({ email, name: 'Demo User' });
      return true;
    }
    return false;
  };

  const logout = () => {
    setIsAuthenticated(false);
    setUser(null);
  };

  const register = (email, password, name) => {
    // Mock registration logic
    if (email && password && name) {
      setIsAuthenticated(true);
      setUser({ email, name });
      return true;
    }
    return false;
  };

  const value = {
    isAuthenticated,
    user,
    login,
    logout,
    register
  };

  return (
    <AuthContext.Provider value={value}>
      {children}
    </AuthContext.Provider>
  );
}