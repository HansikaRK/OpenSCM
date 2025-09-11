import { useNavigate } from 'react-router-dom';
import Button from '../common/Button';

export default function CTASection() {
  const navigate = useNavigate();

  const handleStartTrialClick = () => {
    navigate('/register');
  };

  const handleScheduleDemoClick = () => {
    // You can implement demo scheduling functionality here
    console.log('Schedule Demo clicked');
  };

  return (
    <section className="px-6 py-20 bg-gradient-to-r from-green-600 to-green-500">
      <div className="max-w-4xl mx-auto text-center">
        <h2 className="text-4xl font-bold text-black mb-6">
          Ready to Transform Your Supply Chain?
        </h2>
        <p className="text-xl text-black opacity-80 mb-8">
          Join hundreds of companies already using OpenSCM to optimize their operations
        </p>
        <div className="flex flex-col sm:flex-row gap-4 justify-center">
          <Button variant="cta" size="lg" onClick={handleStartTrialClick}>
            Start Your Free Trial
          </Button>
          <Button variant="ctaSecondary" size="lg" onClick={handleScheduleDemoClick}>
            Schedule Demo
          </Button>
        </div>
      </div>
    </section>
  );
}
