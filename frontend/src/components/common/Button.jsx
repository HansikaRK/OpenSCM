import { ArrowRight } from 'lucide-react';

export default function Button({ 
  children, 
  variant = 'primary', 
  size = 'md', 
  icon = false, 
  className = '', 
  ...props 
}) {
  const baseClasses = 'font-medium rounded-lg transition-all duration-300 flex items-center justify-center';
  
  const variants = {
    primary: 'bg-green-500 hover:bg-green-600 text-black',
    secondary: 'border border-gray-600 hover:border-green-400 text-white',
    cta: 'bg-black text-white hover:bg-gray-900',
    ctaSecondary: 'border-2 border-black text-black hover:bg-black hover:text-white'
  };
  
  const sizes = {
    sm: 'px-4 py-2 text-sm',
    md: 'px-6 py-2',
    lg: 'px-8 py-4'
  };
  
  return (
    <button 
      className={`${baseClasses} ${variants[variant]} ${sizes[size]} ${className} group`}
      {...props}
    >
      {children}
      {icon && <ArrowRight className="ml-2 h-5 w-5 group-hover:translate-x-1 transition-transform" />}
    </button>
  );
}
