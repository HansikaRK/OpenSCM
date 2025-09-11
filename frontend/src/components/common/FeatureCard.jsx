export default function FeatureCard({ icon: Icon, title, description }) {
  return (
    <div 
      className="group bg-black rounded-xl p-6 hover:bg-gray-800 transition-all duration-300 hover:scale-105 border border-gray-800 hover:border-green-400"
    >
      <div className="mb-4">
        <Icon className="h-12 w-12 text-green-400 group-hover:scale-110 transition-transform" />
      </div>
      <h3 className="text-xl font-semibold mb-3">{title}</h3>
      <p className="text-gray-400 group-hover:text-gray-300">{description}</p>
    </div>
  );
}
