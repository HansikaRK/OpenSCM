# OpenSCM Frontend Documentation

## Project Overview

OpenSCM is a modern supply chain management platform built with React and Vite. This documentation covers the frontend application architecture, routing system, and component structure.

## Table of Contents

1. [Project Structure](#project-structure)
2. [Getting Started](#getting-started)
3. [Architecture](#architecture)
4. [Component System](#component-system)
5. [Routing System](./routing-guide.md)
6. [Styling](#styling)
7. [Development Guidelines](#development-guidelines)

## Project Structure

```
frontend/
├── public/                 # Static assets
│   └── vite.svg
├── src/
│   ├── assets/            # Application assets
│   │   └── react.svg
│   ├── components/        # Reusable components
│   │   ├── common/        # Shared components
│   │   │   ├── Button.jsx
│   │   │   ├── Navigation.jsx
│   │   │   ├── Footer.jsx
│   │   │   ├── FeatureCard.jsx
│   │   │   ├── StatCard.jsx
│   │   │   └── index.js
│   │   └── landing/       # Landing page components
│   │       ├── HeroSection.jsx
│   │       ├── FeaturesSection.jsx
│   │       ├── StatsSection.jsx
│   │       ├── CTASection.jsx
│   │       └── index.js
│   ├── docs/              # Documentation
│   │   ├── README.md
│   │   └── routing-guide.md
│   ├── hooks/             # Custom React hooks (future)
│   ├── layouts/           # Layout components (future)
│   ├── pages/             # Page components
│   │   ├── LandingPage.jsx
│   │   ├── Login.jsx
│   │   └── Register.jsx
│   ├── routes/            # Routing configuration
│   │   ├── AppRoutes.jsx
│   │   ├── PublicRoutes.jsx
│   │   ├── ProtectedRoutes.jsx
│   │   └── index.js
│   ├── utils/             # Utility functions (future)
│   ├── apis/              # API integration (future)
│   ├── index.css          # Global styles
│   └── main.jsx           # Application entry point
├── eslint.config.js       # ESLint configuration
├── index.html             # HTML template
├── package.json           # Dependencies and scripts
├── README.md              # Project README
└── vite.config.js         # Vite configuration
```

## Getting Started

### Prerequisites

- Node.js (v16 or higher)
- npm or yarn

### Installation

1. Clone the repository
2. Navigate to the frontend directory
3. Install dependencies:
   ```bash
   npm install
   ```
4. Start the development server:
   ```bash
   npm run dev
   ```

### Available Scripts

- `npm run dev` - Start development server
- `npm run build` - Build for production
- `npm run lint` - Run ESLint
- `npm run preview` - Preview production build

## Architecture

### Technology Stack

- **React 19.1.1** - UI library
- **Vite 7.1.2** - Build tool and dev server
- **React Router DOM 7.8.2** - Client-side routing
- **Tailwind CSS 4.1.13** - Utility-first CSS framework
- **Lucide React** - Icon library
- **ESLint** - Code linting

### Design Patterns

1. **Component Composition** - Breaking down complex UI into smaller, reusable components
2. **Container/Presentational Pattern** - Separating data logic from UI logic
3. **Modular Architecture** - Organizing code into logical modules and folders

## Component System

### Component Categories

#### 1. Common Components (`src/components/common/`)
Reusable components used across the application:

- **Button** - Flexible button component with variants and sizes
- **Navigation** - Main navigation bar with routing
- **Footer** - Site footer with links and branding
- **FeatureCard** - Card component for displaying features
- **StatCard** - Component for displaying statistics

#### 2. Landing Components (`src/components/landing/`)
Components specific to the landing page:

- **HeroSection** - Main hero area with CTA
- **FeaturesSection** - Features showcase grid
- **StatsSection** - Statistics display
- **CTASection** - Call-to-action section

#### 3. Page Components (`src/pages/`)
Top-level page components:

- **LandingPage** - Main landing page
- **Login** - User login page
- **Register** - User registration page

### Component Guidelines

1. **Single Responsibility** - Each component should have one clear purpose
2. **Props Interface** - Clear prop definitions with default values
3. **Composition over Inheritance** - Use composition to build complex components
4. **Consistent Naming** - Use PascalCase for components, camelCase for props

## Styling

### Tailwind CSS

The application uses Tailwind CSS for styling with a custom configuration:

- **Dark Theme** - Primary black background with green accents
- **Green Color Scheme** - Green-400, Green-500, Green-600 variants
- **Responsive Design** - Mobile-first responsive utilities
- **Custom Components** - Styled using Tailwind utility classes

### Color Palette

```css
/* Primary Colors */
--green-400: #4ade80
--green-500: #22c55e
--green-600: #16a34a

/* Grayscale */
--black: #000000
--gray-800: #1f2937
--gray-900: #111827
--white: #ffffff
```

### Responsive Breakpoints

- `sm`: 640px
- `md`: 768px
- `lg`: 1024px
- `xl`: 1280px

## Development Guidelines

### Code Style

1. **ESLint Configuration** - Follow the project's ESLint rules
2. **Consistent Formatting** - Use consistent indentation and spacing
3. **Import Organization** - Group imports by type (React, libraries, local)

### Best Practices

1. **Component Structure**:
   ```jsx
   // Imports
   import React from 'react';
   import { useNavigate } from 'react-router-dom';
   
   // Component
   export default function ComponentName() {
     // Hooks
     // State
     // Event handlers
     // Render
   }
   ```

2. **Props Destructuring**:
   ```jsx
   export default function Button({ 
     children, 
     variant = 'primary', 
     size = 'md', 
     ...props 
   }) {
     // Component logic
   }
   ```

3. **Event Handlers**:
   ```jsx
   const handleClick = () => {
     // Handler logic
   };
   ```

### Git Workflow

1. Create feature branches from `main`
2. Use descriptive commit messages
3. Test thoroughly before merging
4. Keep commits focused and atomic

## Future Enhancements

### Planned Features

1. **Authentication System** - User login/logout with JWT
2. **Dashboard** - Main application dashboard
3. **API Integration** - Backend API connectivity
4. **State Management** - Redux or Context API for global state
5. **Testing** - Unit and integration tests
6. **Internationalization** - Multi-language support

### Performance Optimizations

1. **Code Splitting** - Dynamic imports for better loading
2. **Image Optimization** - Optimized image loading
3. **Bundle Analysis** - Monitor and optimize bundle size
4. **Caching Strategy** - Implement proper caching

## Contributing

1. Follow the coding standards
2. Write clear commit messages
3. Test your changes thoroughly
4. Update documentation as needed
5. Submit pull requests for review

## Resources

- [React Documentation](https://react.dev/)
- [Vite Documentation](https://vitejs.dev/)
- [Tailwind CSS Documentation](https://tailwindcss.com/)
- [React Router Documentation](https://reactrouter.com/)
- [Lucide Icons](https://lucide.dev/)
