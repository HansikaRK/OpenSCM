import React from "react";

const Button = ({
  children,
  onClick,
  type = "button",
  variant = "primary",
  size = "md",
  className = "",
  disabled = false,
  ...props
}) => {

  const base = "inline-flex items-center justify-center font-semibold shadow transition focus:outline-none focus:ring-1 focus:ring-offset-1";
    
  const variants = {
    primary: 'bg-gradient-to-r from-green-500 to-green-600 text-white hover:from-green-600 hover:to-green-700 focus:ring-green-500',
    secondary: 'bg-black text-white hover:bg-gray-900 focus:ring-gray-700',
    outline: 'border-2 border-green-500 text-green-700 hover:bg-green-50 focus:ring-green-500',
    ghost: 'text-gray-700 hover:text-green-600 hover:bg-gray-100',
    danger: 'bg-gradient-to-r from-red-500 to-red-600 text-white hover:from-red-600 hover:to-red-700 focus:ring-red-500',
    light: 'bg-white text-green-600 border border-green-500 hover:bg-green-50 focus:ring-green-500',
  };

  const sizes = {
    sm: 'px-4 py-2 text-sm rounded-lg',
    md: 'px-6 py-2.5 text-base rounded-xl',
    lg: 'px-8 py-3 text-lg rounded-2xl',
    xl: 'px-12 py-4 text-xl rounded-2xl',
  };

  return (
    <button
      type={type}
      onClick={onClick}
      disabled={disabled}
      {...props}
      className={`
        ${base}
        ${variants[variant]}
        ${sizes[size]}
        disabled:opacity-50 disabled:cursor-not-allowed
        ${className}
      `}
    >
      {children}
    </button>
  );
};

export default Button;
