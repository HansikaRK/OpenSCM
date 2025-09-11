export default function StatCard({ number, label }) {
  return (
    <div className="group hover:scale-105 transition-transform">
      <div className="text-4xl font-bold text-green-400 mb-2 group-hover:text-green-300">
        {number}
      </div>
      <div className="text-gray-400">{label}</div>
    </div>
  );
}
