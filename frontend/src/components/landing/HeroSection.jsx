import { useState, useEffect } from 'react';
import { ArrowRight, BarChart3, Truck } from 'lucide-react';
import { useNavigate } from 'react-router-dom';
import Button from '../common/Button';

export default function HeroSection() {
  const [isVisible, setIsVisible] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    setIsVisible(true);
  }, []);

  const handleStartTrialClick = () => {
    navigate('/register');
  };

  const handleWatchDemoClick = () => {
    // You can implement demo functionality here or navigate to a demo page
    console.log('Watch Demo clicked');
  };

  return (
    <section className="relative px-6 py-20">
      <div className="max-w-7xl mx-auto">
        <div className="grid lg:grid-cols-2 gap-12 items-center">
          <div className={`transform transition-all duration-1000 ${isVisible ? 'translate-x-0 opacity-100' : '-translate-x-10 opacity-0'}`}>
            <h1 className="text-5xl lg:text-6xl font-bold leading-tight mb-6">
              Streamline Your
              <span className="text-green-400 block">Supply Chain</span>
            </h1>
            <p className="text-xl text-gray-300 mb-8 leading-relaxed">
              Optimize operations, reduce costs, and increase visibility across your entire supply chain with our intelligent management platform.
            </p>
            <div className="flex flex-col sm:flex-row gap-4">
              <Button size="lg" icon onClick={handleStartTrialClick}>
                Start Free Trial
              </Button>
              <Button variant="secondary" size="lg" onClick={handleWatchDemoClick}>
                Watch Demo
              </Button>
            </div>
          </div>
          
          <div className={`transform transition-all duration-1000 delay-300 ${isVisible ? 'translate-x-0 opacity-100' : 'translate-x-10 opacity-0'}`}>
            <div className="relative">
              <div className="bg-gradient-to-br from-green-400 to-green-600 rounded-2xl p-1">
                <div className="bg-black rounded-xl p-8">
                  <div className="grid grid-cols-2 gap-4 mb-6">
                    <div className="bg-gray-900 rounded-lg p-4">
                      <div className="flex items-center mb-2">
                        <BarChart3 className="h-6 w-6 text-green-400 mr-2" />
                        <span className="text-sm font-medium">Analytics</span>
                      </div>
                      <div className="text-2xl font-bold text-green-400">↗ 24%</div>
                    </div>
                    <div className="bg-gray-900 rounded-lg p-4">
                      <div className="flex items-center mb-2">
                        <Truck className="h-6 w-6 text-green-400 mr-2" />
                        <span className="text-sm font-medium">Deliveries</span>
                      </div>
                      <div className="text-2xl font-bold text-green-400">1,247</div>
                    </div>
                  </div>
                  <div className="space-y-3">
                    <div className="flex items-center justify-between bg-gray-900 rounded-lg p-3">
                      <span className="text-sm">Inventory Status</span>
                      <div className="w-20 bg-gray-700 rounded-full h-2">
                        <div className="bg-green-400 h-2 rounded-full w-16"></div>
                      </div>
                    </div>
                    <div className="flex items-center justify-between bg-gray-900 rounded-lg p-3">
                      <span className="text-sm">Order Processing</span>
                      <div className="w-20 bg-gray-700 rounded-full h-2">
                        <div className="bg-green-400 h-2 rounded-full w-12"></div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div className="absolute -top-4 -right-4 w-20 h-20 bg-green-400 rounded-full opacity-20 animate-pulse"></div>
              <div className="absolute -bottom-4 -left-4 w-16 h-16 bg-green-400 rounded-full opacity-30 animate-pulse delay-300"></div>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
}
