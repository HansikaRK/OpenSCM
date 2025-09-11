import { Routes, Route } from "react-router-dom";
import LandingPage from "../pages/LandingPage";

function AppRoutes() {
  return (
    <Routes>
      <Route path="/" element={<LandingPage />} />
      <Route path="/landing" element={<LandingPage />} />
      {/* Add more routes here as needed */}
    </Routes>
  );
}

export default AppRoutes;
