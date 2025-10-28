import React from 'react';
import { Package, TrendingUp, Shield, Globe, Users, BarChart3 } from 'lucide-react';

const FeaturesSection = () => {
  const features = [
    {
      icon: <Package className="w-8 h-8" />,
      title: "Inventory Management",
      description: "Real-time tracking of stock levels, automated reordering, and warehouse optimization."
    },
    {
      icon: <TrendingUp className="w-8 h-8" />,
      title: "Demand Forecasting",
      description: "AI-powered predictions to optimize inventory and reduce waste across your supply chain."
    },
    {
      icon: <Globe className="w-8 h-8" />,
      title: "Global Logistics",
      description: "Multi-modal shipping management with real-time tracking and route optimization."
    },
    {
      icon: <Shield className="w-8 h-8" />,
      title: "Risk Management",
      description: "Identify and mitigate supply chain risks with advanced analytics and alerts."
    },
    {
      icon: <Users className="w-8 h-8" />,
      title: "Supplier Network",
      description: "Manage relationships, performance metrics, and communications in one platform."
    },
    {
      icon: <BarChart3 className="w-8 h-8" />,
      title: "Analytics & Reports",
      description: "Comprehensive dashboards and custom reports for data-driven decisions."
    }
  ];

  return (
    <section id="features" className="py-20 px-4 sm:px-6 lg:px-8">
      <div className="max-w-7xl mx-auto">
        <div className="text-center mb-16">
          <h2 className="text-4xl md:text-5xl font-bold mb-4">Powerful Features</h2>
          <p className="text-xl text-gray-400 max-w-2xl mx-auto">
            Everything you need to manage your supply chain efficiently and effectively
          </p>
        </div>

        <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-8">
          {features.map((feature, index) => (
            <div
              key={index}
              className="bg-gray-900/50 border border-gray-800 rounded-xl p-6 hover:border-emerald-500/50 transition-all transform hover:-translate-y-1"
            >
              <div className="text-emerald-500 mb-4">
                {feature.icon}
              </div>
              <h3 className="text-xl font-bold mb-2">{feature.title}</h3>
              <p className="text-gray-400">{feature.description}</p>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
};

export default FeaturesSection;