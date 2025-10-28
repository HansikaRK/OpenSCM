import React from 'react';
import { Zap, ArrowRight } from 'lucide-react';
import { Button } from '../common';

const HeroSection = () => {
  return (
    <section className="pt-32 pb-20 px-4 sm:px-6 lg:px-8">
      <div className="max-w-7xl mx-auto">
        <div className="text-center space-y-8">
          <div className="inline-flex items-center space-x-2 bg-emerald-500/10 border border-emerald-500/20 rounded-full px-4 py-2">
            <Zap className="w-4 h-4 text-emerald-500" />
            <span className="text-sm text-gray-400">Open Source Supply Chain Platform</span>
          </div>
          
          <h1 className="text-5xl md:text-7xl font-bold leading-tight">
            Streamline Your
            <span className="block text-emerald-500">Supply Chain</span>
          </h1>
          
          <p className="text-xl text-gray-400 max-w-3xl mx-auto">
            OpenSCM is a powerful, open-source supply chain management system designed to optimize your operations, reduce costs, and improve visibility across your entire supply network.
          </p>
          
          <div className="flex flex-col sm:flex-row justify-center gap-4 pt-4">
            <Button 
              variant="primary" 
              size="lg" 
              className="bg-emerald-500 hover:bg-emerald-600 transition-all transform hover:scale-105 flex items-center space-x-2"
            >
              <span>Get Started</span>
              <ArrowRight className="w-5 h-5" />
            </Button>
            <Button 
              variant="outline" 
              size="lg" 
              className="border-gray-700 hover:border-emerald-500/50 text-white"
            >
              View Demo
            </Button>
          </div>
        </div>
      </div>
    </section>
  );
};

export default HeroSection;