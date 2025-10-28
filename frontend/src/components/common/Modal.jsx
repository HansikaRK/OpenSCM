import React, { useEffect } from "react";
import { X } from "lucide-react";

const Modal = ({
  isOpen,
  onClose,
  title,
  children,
  size = "md",
  variant = "default",
  className = "",
  closeOnOverlay = true,
  ...props
}) => {
  
  useEffect(() => {
    const handleKeyDown = (e) => {
      if (e.key === "Escape" && isOpen) onClose?.();
    };
    document.addEventListener("keydown", handleKeyDown);
    return () => document.removeEventListener("keydown", handleKeyDown);
  }, [isOpen, onClose]);

  if (!isOpen) return null;

  
  const base =
    "relative rounded-2xl shadow-xl transition-all bg-white flex flex-col overflow-hidden";

 
  const variants = {
    default: "bg-white text-gray-900",
    dark: "bg-gray-900 text-white border border-gray-800",
    light: "bg-gray-50 text-gray-900",
  };

  
  const sizes = {
    sm: "w-full max-w-sm",
    md: "w-full max-w-lg",
    lg: "w-full max-w-2xl",
  };

  return (
    <div
      className="fixed inset-0 z-50 flex items-center justify-center p-4"
      aria-modal="true"
      role="dialog"
    >
      {/* Backdrop */}
      <div
        className="fixed inset-0 bg-black/50 backdrop-blur-sm transition-opacity"
        onClick={closeOnOverlay ? onClose : undefined}
      />

      {/* Modal Content */}
      <div
        {...props}
        className={`
          ${base}
          ${variants[variant]}
          ${sizes[size]}
          ${className}
          animate-fadeIn
        `}
      >
        {/* Header */}
        {(title || onClose) && (
          <div className="flex items-center justify-between px-6 py-4 border-b border-gray-200">
            {title && (
              <h2 className="text-lg font-semibold">{title}</h2>
            )}
            {onClose && (
              <button
                onClick={onClose}
                className="text-gray-400 hover:text-gray-600 focus:outline-none"
              >
                <X size={20} />
              </button>
            )}
          </div>
        )}

        {/* Body */}
        <div className="px-6 py-4">{children}</div>
      </div>
    </div>
  );
};

export default Modal;
