import React from 'react';
import LandingLayout from '../../layouts/LandingLayout';
import {
  HeroSection,
  StatsSection,
  FeaturesSection,
  CTASection
} from '../../components/landing';

export default function OpenSCMLanding() {
  return (
    <LandingLayout>
      <HeroSection />
      <StatsSection />
      <FeaturesSection />
      <CTASection />
    </LandingLayout>
  );
}