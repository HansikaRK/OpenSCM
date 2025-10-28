import React from "react";

const Card = ({
    children,
    variant = "default",
    size = "md",
    className = "",
    ...props
}) => {

    const base = "rounded-2xl shadow-md transition border bg-white overflow-hidden";

    const variants = {
        default: "border-gray-200 hover:shadow-lg",
        outline: "border-2 border-green-500 hover:shadow-lg",
        elevated: "shadow-lg hover:shadow-xl",
        subtle: "bg-gray-50 border-gray-100",
        dark: "bg-gray-900 text-white border-gray-800",
    };

    const sizes = {
        sm: "p-4 text-sm",
        md: "p-6 text-base",
        lg: "p-8 text-lg",
    };

    return (
    <div
      {...props}
      className={`
        ${base}
        ${variants[variant]}
        ${sizes[size]}
        ${className}
      `}
    >
      {children}
    </div>
  );
}

export default Card;