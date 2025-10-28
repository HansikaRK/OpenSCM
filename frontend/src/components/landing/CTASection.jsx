import React from 'react';
import { Button } from '../common';

const CTASection = () => {
  return (
    <section className="py-20 px-4 sm:px-6 lg:px-8">
      <div className="max-w-4xl mx-auto">
        <div className="bg-gradient-to-r from-emerald-500/10 to-emerald-600/10 border border-emerald-500/20 rounded-2xl p-12 text-center">
          <h2 className="text-4xl font-bold mb-4">Ready to Transform Your Supply Chain?</h2>
          <p className="text-xl text-gray-400 mb-8">
            Join thousands of companies already using OpenSCM to optimize their operations
          </p>
          <Button 
            variant="primary" 
            size="lg" 
            className="bg-emerald-500 hover:bg-emerald-600 transition-all transform hover:scale-105"
          >
            Start Free Trial
          </Button>
        </div>
      </div>
    </section>
  );
};

export default CTASection;