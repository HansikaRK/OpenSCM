# OpenSCM Frontend - Routing Documentation

## Overview

The OpenSCM frontend application uses React Router DOM for client-side routing. This document explains how the routing system is structured and how navigation works throughout the application.

## Routing Architecture

### Main Routing Structure

The application uses a hierarchical routing structure:

```
src/
├── main.jsx                 # Entry point with BrowserRouter
├── routes/
│   ├── index.js            # Route exports
│   ├── PublicRoutes.jsx    # Public/unauthenticated routes
│   ├── AppRoutes.jsx       # Main application routes
│   └── ProtectedRoutes.jsx # Authenticated routes (future)
└── pages/
    ├── LandingPage.jsx     # Home/landing page
    ├── Login.jsx           # Login page
    └── Register.jsx        # Registration page
```

## Route Configuration

### 1. Main Entry Point (`main.jsx`)

```jsx
import { BrowserRouter as Router } from 'react-router-dom';
import { AuthProvider } from './contexts/AuthContext';
import { AppRoutes } from './routes';

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <AuthProvider>
      <Router>
        <AppRoutes />
      </Router>
    </AuthProvider>
  </StrictMode>,
)
```

The main entry point wraps the entire application with:
1. `AuthProvider` - Provides authentication context
2. `BrowserRouter` - Enables client-side routing  
3. `AppRoutes` - Main route configuration

## Authentication System

### Authentication Context (`AuthContext.jsx`)

Provides mock authentication functionality:

```jsx
const { isAuthenticated, user, login, logout, register } = useAuth();
```

**Features:**
- Mock login/register (any credentials work)
- Authentication state management
- User session handling

### Protected Route Component (`ProtectedRoute.jsx`)

Handles route protection logic:

```jsx
import { Navigate, useLocation } from 'react-router-dom';
import { useAuth } from '../contexts/AuthContext';

export default function ProtectedRoute({ children }) {
  const { isAuthenticated } = useAuth();
  const location = useLocation();

  if (!isAuthenticated) {
    return <Navigate to="/login" state={{ from: location }} replace />;
  }

  return children;
}
```

**Functionality:**
- Checks authentication status
- Redirects to login if not authenticated
- Preserves intended destination
- Returns to original route after login

### 2. Public Routes (`PublicRoutes.jsx`)

Handles unauthenticated routes (legacy - now integrated into AppRoutes):

```jsx
const PublicRoutes = () => {
    return (
        <Routes>
            <Route path="/" element={<LandingPage />} />
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />
        </Routes>
    );
};
```

### 3. Main App Routes (`AppRoutes.jsx`)

Now handles both public and protected routes:

```jsx
function AppRoutes() {
  return (
    <Routes>
      {/* Public Routes */}
      <Route path="/" element={<LandingPage />} />
      <Route path="/login" element={<Login />} />
      <Route path="/register" element={<Register />} />
      
      {/* Protected Routes */}
      <Route 
        path="/dashboard" 
        element={
          <ProtectedRoute>
            <Dashboard />
          </ProtectedRoute>
        } 
      />
    </Routes>
  );
}
```

**Available Routes:**
- `/` - Landing page (public)
- `/login` - User login page (public)
- `/register` - User registration page (public)
- `/dashboard` - Main dashboard (protected)

### 4. Protected Routes (`ProtectedRoutes.jsx`)

Alternative implementation for grouping protected routes:

```jsx
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
        </Routes>
    );
};
```

## Navigation Implementation

### 1. Programmatic Navigation

Components use the `useNavigate` hook for programmatic navigation:

```jsx
import { useNavigate } from 'react-router-dom';

const navigate = useNavigate();

// Navigate to a specific route
const handleLoginClick = () => {
    navigate('/login');
};
```

### 2. Navigation Component

The main navigation (`src/components/common/Navigation.jsx`) provides:

- **Logo/Brand**: Links to home page (`/`)
- **Login Button**: Navigates to `/login`
- **Register Button**: Navigates to `/register`

```jsx
const handleLoginClick = () => {
    navigate('/login');
};

const handleRegisterClick = () => {
    navigate('/register');
};
```

### 3. Landing Page Navigation

#### Hero Section Buttons:
- **Start Free Trial**: Navigates to `/register`
- **Watch Demo**: Placeholder for demo functionality

#### CTA Section Buttons:
- **Start Your Free Trial**: Navigates to `/register`
- **Schedule Demo**: Placeholder for demo scheduling

### 4. Conditional Navigation

Navigation components adapt based on authentication state:

```jsx
const { isAuthenticated, logout } = useAuth();

{isAuthenticated ? (
  <>
    <Button onClick={() => navigate('/dashboard')}>Dashboard</Button>
    <Button onClick={handleLogout}>Logout</Button>
  </>
) : (
  <>
    <Button onClick={() => navigate('/login')}>Login</Button>
    <Button onClick={() => navigate('/register')}>Register</Button>
  </>
)}
```

## Authentication Flow

### User Registration Flow
1. User clicks "Register" or "Start Free Trial"
2. Navigates to `/register`
3. Fills out registration form
4. On success: Automatically logged in and redirected to `/dashboard`

### User Login Flow
1. User clicks "Login" button
2. Navigates to `/login`
3. Enters credentials (any email/password works in demo)
4. On success: Redirected to intended page or `/dashboard`

### Protected Route Access Flow
1. User tries to access protected route (e.g., `/dashboard`)
2. `ProtectedRoute` component checks authentication
3. If not authenticated:
   - Saves intended destination
   - Redirects to `/login`
   - After login, redirects back to intended page
4. If authenticated: Shows protected content

### Logout Flow
1. User clicks "Logout" button
2. Authentication state cleared
3. Redirected to landing page (`/`)
4. Navigation updates to show Login/Register buttons

## Button Navigation System

### Common Button Component

The application uses a reusable `Button` component (`src/components/common/Button.jsx`) that accepts:

- `variant`: Styling variant (primary, secondary, cta, ctaSecondary)
- `size`: Button size (sm, md, lg)
- `onClick`: Click handler function
- `icon`: Whether to show arrow icon

### Navigation Flow

1. **Landing Page → Register**: 
   - Hero "Start Free Trial" button
   - CTA "Start Your Free Trial" button
   - Navigation "Register" button

2. **Landing Page → Login**:
   - Navigation "Login" button

3. **Cross-page Navigation**:
   - Login page has links to Register and Home
   - Register page has links to Login and Home

## Page Structure

### Landing Page Components

The landing page is broken down into modular components:

```
src/components/
├── common/                 # Reusable components
│   ├── Navigation.jsx     # Main navigation bar
│   ├── Footer.jsx         # Site footer
│   ├── Button.jsx         # Reusable button component
│   ├── FeatureCard.jsx    # Feature display card
│   └── StatCard.jsx       # Statistics display card
└── landing/               # Landing-specific components
    ├── HeroSection.jsx    # Main hero section
    ├── FeaturesSection.jsx # Features showcase
    ├── StatsSection.jsx   # Statistics section
    └── CTASection.jsx     # Call-to-action section
```

### Component Navigation Integration

Each component that needs navigation functionality:

1. Imports `useNavigate` from react-router-dom
2. Creates navigation handler functions
3. Passes handlers to Button components via `onClick` props

## Future Routing Enhancements

### Planned Routes (when implemented):

**Protected Routes:**
- `/dashboard` - Main dashboard ✅ **(Implemented)**
- `/profile` - User profile management
- `/settings` - Application settings
- `/inventory` - Inventory management
- `/analytics` - Analytics dashboard
- `/suppliers` - Supplier management

**Public Routes:**
- `/demo` - Product demonstration
- `/contact` - Contact page
- `/about` - About page
- `/pricing` - Pricing information

### Authentication Integration ✅ **(Implemented)**

Current implementation includes:

1. **ProtectedRoutes** ✅ - Wraps authenticated pages
2. **Route guards** ✅ - Redirects unauthenticated users
3. **Context/State management** ✅ - Handles auth state with React Context
4. **Conditional navigation** ✅ - Based on auth status

### Advanced Features (Future)

1. **Role-based Access Control (RBAC)**:
   - Admin-only routes
   - User permission checks
   - Feature-level permissions

2. **Route-level Code Splitting**:
   - Lazy loading for better performance
   - Dynamic imports for large pages

3. **Breadcrumb Navigation**:
   - Automatic breadcrumb generation
   - Deep linking support

4. **Route Animations**:
   - Page transition animations
   - Loading states

## Best Practices

### 1. Navigation Patterns
- Use `useNavigate()` for programmatic navigation
- Use `Link` for declarative navigation
- Keep navigation logic in component handlers

### 2. Route Organization
- Group related routes in separate route components
- Use descriptive route paths
- Implement proper route hierarchy

### 3. Component Structure
- Keep navigation components modular
- Separate navigation logic from UI logic
- Use reusable button components

## Troubleshooting

### Common Issues

1. **Invalid Hook Call Error**: 
   - Ensure react-router-dom is properly installed
   - Check for version compatibility with React

2. **Navigation Not Working**:
   - Verify BrowserRouter wraps the application
   - Check route path spelling
   - Ensure navigate functions are called correctly

3. **Page Not Found**:
   - Add catch-all route for 404 handling
   - Verify route definitions match navigation calls

### Dependencies

Required packages:
- `react-router-dom`: Client-side routing
- `react`: Core React library
- `react-dom`: React DOM rendering

Current versions in package.json:
- react-router-dom: ^7.8.2
- react: ^19.1.1
- react-dom: ^19.1.1

## Testing Navigation

To test the routing system:

1. Start the development server: `npm run dev`
2. Navigate to `http://localhost:5173`
3. Test each navigation button/link
4. Verify URLs change correctly
5. Test browser back/forward buttons
6. Test direct URL access

## Additional Resources

- [React Router Documentation](https://reactrouter.com/)
- [React Navigation Patterns](https://react.dev/learn/escape-hatches#you-might-not-need-an-effect)
- [Client-side Routing Best Practices](https://web.dev/client-side-routing/)
