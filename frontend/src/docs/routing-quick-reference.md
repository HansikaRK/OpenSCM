# Quick Routing Reference

## Current Routes

| Route | Component | Type | Description |
|-------|-----------|------|-------------|
| `/` | LandingPage | Public | Home/landing page with hero, features, and CTA |
| `/login` | Login | Public | User login page with authentication |
| `/register` | Register | Public | User registration page with authentication |
| `/dashboard` | Dashboard | Protected | Main dashboard (requires authentication) |

## Navigation Components

### Navigation Bar Buttons (Conditional)
**When NOT authenticated:**
- **Login Button** → `/login`
- **Register Button** → `/register`

**When authenticated:**
- **Dashboard Button** → `/dashboard`
- **Logout Button** → Logs out and redirects to `/`

### Landing Page Buttons
- **Start Free Trial** (Hero) → `/register`
- **Watch Demo** (Hero) → *Console log (placeholder)*
- **Start Your Free Trial** (CTA) → `/register`
- **Schedule Demo** (CTA) → *Console log (placeholder)*

### Cross-page Navigation
- **Login Page** → Links to `/register` and `/`
- **Register Page** → Links to `/login` and `/`
- **Dashboard Page** → Links to `/` and logout functionality

## Authentication Flow

### Protected Route Access
1. User tries to access `/dashboard`
2. If not authenticated → Redirected to `/login`
3. After successful login → Redirected back to `/dashboard`
4. If authenticated → Direct access to dashboard

### Authentication States
- **Unauthenticated**: Can access public routes only
- **Authenticated**: Can access all routes including protected ones

## Navigation Methods

### Programmatic Navigation
```jsx
import { useNavigate } from 'react-router-dom';

const navigate = useNavigate();
navigate('/login'); // Navigate to login page
```

### Declarative Navigation
```jsx
import { Link } from 'react-router-dom';

<Link to="/register">Register</Link>
```

### Protected Route Implementation
```jsx
import ProtectedRoute from '../components/common/ProtectedRoute';

<Route 
  path="/dashboard" 
  element={
    <ProtectedRoute>
      <Dashboard />
    </ProtectedRoute>
  } 
/>
```

## Authentication Context

### Using Authentication
```jsx
import { useAuth } from '../contexts/AuthContext';

const { isAuthenticated, user, login, logout, register } = useAuth();
```

### Demo Authentication
- **Login**: Any email/password combination works
- **Register**: Any name/email/password combination works
- **State**: Managed in React Context (mock implementation)

## File Locations

- **Routes**: `src/routes/AppRoutes.jsx` (main), `src/routes/PublicRoutes.jsx`, `src/routes/ProtectedRoutes.jsx`
- **Pages**: `src/pages/` (LandingPage, Login, Register, Dashboard)
- **Navigation**: `src/components/common/Navigation.jsx`
- **Button Component**: `src/components/common/Button.jsx`
- **Protected Route**: `src/components/common/ProtectedRoute.jsx`
- **Auth Context**: `src/contexts/AuthContext.jsx`

## Adding New Routes

### Public Route
1. Create new page component in `src/pages/`
2. Add route to `src/routes/AppRoutes.jsx`
3. Add navigation buttons/links as needed

### Protected Route
1. Create new page component in `src/pages/`
2. Wrap with `<ProtectedRoute>` in `src/routes/AppRoutes.jsx`
3. Add navigation for authenticated users
4. Update this documentation

## Dependencies

- `react-router-dom`: ^7.8.2
- `react`: ^19.1.1
