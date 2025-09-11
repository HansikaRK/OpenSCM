import { BarChart3, Truck, Package, Shield, Users, CheckCircle } from 'lucide-react';
import FeatureCard from '../common/FeatureCard';

export default function FeaturesSection() {
  const features = [
    {
      icon: BarChart3,
      title: "Real-time Analytics",
      description: "Get instant insights into your supply chain performance with advanced analytics and reporting tools."
    },
    {
      icon: Truck,
      title: "Logistics Tracking",
      description: "Track shipments, manage deliveries, and optimize routes with our comprehensive logistics platform."
    },
    {
      icon: Package,
      title: "Inventory Management",
      description: "Maintain optimal stock levels, automate reordering, and reduce carrying costs with smart inventory control."
    },
    {
      icon: Shield,
      title: "Risk Management",
      description: "Identify potential disruptions early and implement contingency plans to maintain business continuity."
    },
    {
      icon: Users,
      title: "Supplier Network",
      description: "Manage supplier relationships, track performance, and ensure quality standards across your network."
    },
    {
      icon: CheckCircle,
      title: "Compliance Tracking",
      description: "Ensure regulatory compliance and maintain quality standards with automated tracking and reporting."
    }
  ];

  return (
    <section id="features" className="px-6 py-20 bg-gray-900">
      <div className="max-w-7xl mx-auto">
        <div className="text-center mb-16">
          <h2 className="text-4xl font-bold mb-4">Powerful Features</h2>
          <p className="text-xl text-gray-400">Everything you need to manage your supply chain efficiently</p>
        </div>
        
        <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-8">
          {features.map((feature, index) => (
            <FeatureCard 
              key={index}
              icon={feature.icon}
              title={feature.title}
              description={feature.description}
            />
          ))}
        </div>
      </div>
    </section>
  );
}
