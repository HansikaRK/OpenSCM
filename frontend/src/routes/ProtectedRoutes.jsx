import { Routes, Route } from "react-router-dom";
import Dashboard from "../pages/Dashboard";
import ProtectedRoute from "../components/common/ProtectedRoute";

const ProtectedRoutes = () => {
    return (
        <Routes>
            <Route 
                path="/dashboard" 
                element={
                    <ProtectedRoute>
                        <Dashboard />
                    </ProtectedRoute>
                } 
            />
            {/* Add more protected routes here as needed */}
        </Routes>
    );
};

export default ProtectedRoutes;
