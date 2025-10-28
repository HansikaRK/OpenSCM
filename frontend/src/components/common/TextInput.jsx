import React from "react";

const TextInput = ({
  label,
  error,
  leftIcon,    
  rightIcon,   
  variant = "default",
  size = "md",
  className = "",
  disabled = false,
  ...props
}) => {
  
  const base =
    "w-full rounded-xl border focus:outline-none focus:ring-2 transition disabled:opacity-50 disabled:cursor-not-allowed";

  
  const variants = {
    default: "border-gray-300 focus:ring-green-500 focus:border-green-500 bg-white",
    outline: "border-2 border-green-500 focus:ring-green-600 focus:border-green-600 bg-white",
    filled: "bg-gray-100 border-gray-200 focus:bg-white focus:ring-green-500 focus:border-green-500",
    error: "border-red-500 focus:ring-red-500 focus:border-red-500 bg-white",
  };

  
  const sizes = {
    sm: "px-3 py-2 text-sm",
    md: "px-4 py-2.5 text-base",
    lg: "px-5 py-3 text-lg",
  };

 
  const inputPadding = `
    ${leftIcon ? "pl-10" : ""}
    ${rightIcon ? "pr-10" : ""}
  `;

  return (
    <div className={`flex flex-col space-y-1 ${className}`}>
      {label && (
        <label
          htmlFor={props.id}
          className="text-sm font-medium text-gray-700"
        >
          {label}
        </label>
      )}

      <div className="relative flex items-center">
        {/* Left icon */}
        {leftIcon && (
          <span className="absolute left-3 text-gray-400 flex items-center">
            {leftIcon}
          </span>
        )}

        {/* Input */}
        <input
          disabled={disabled}
          {...props}
          className={`
            ${base}
            ${variants[error ? "error" : variant]}
            ${sizes[size]}
            ${inputPadding}
          `}
        />

        {/* Right icon */}
        {rightIcon && (
          <span className="absolute right-3 text-gray-400 flex items-center">
            {rightIcon}
          </span>
        )}
      </div>

      {error && <p className="text-sm text-red-600">{error}</p>}
    </div>
  );
};

export default TextInput;
