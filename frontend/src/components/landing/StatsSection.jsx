import StatCard from '../common/StatCard';

export default function StatsSection() {
  const stats = [
    { number: "500+", label: "Companies Trust Us" },
    { number: "99.9%", label: "Uptime Guarantee" },
    { number: "$2.5M+", label: "Cost Savings Generated" },
    { number: "24/7", label: "Support Available" }
  ];

  return (
    <section className="px-6 py-20 bg-black">
      <div className="max-w-7xl mx-auto">
        <div className="grid md:grid-cols-4 gap-8 text-center">
          {stats.map((stat, index) => (
            <StatCard 
              key={index}
              number={stat.number}
              label={stat.label}
            />
          ))}
        </div>
      </div>
    </section>
  );
}
