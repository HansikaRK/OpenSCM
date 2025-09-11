import { Navigation, Footer } from '../components/common';
import { HeroSection, FeaturesSection, StatsSection, CTASection } from '../components/landing';

export default function LandingPage() {
  return (
    <div className="min-h-screen bg-black text-white overflow-hidden">
      <Navigation />
      <HeroSection />
      <FeaturesSection />
      <StatsSection />
      <CTASection />
      <Footer />
    </div>
  );
}