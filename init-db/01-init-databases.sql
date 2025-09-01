-- Create databases for each service
CREATE DATABASE openscm_auth;
CREATE DATABASE openscm_inventory; 
CREATE DATABASE openscm_order;
CREATE DATABASE openscm_supplier;


-- Grant all privileges to postgres user (already owner, but just to be explicit)
GRANT ALL PRIVILEGES ON DATABASE openscm_auth TO postgres;
GRANT ALL PRIVILEGES ON DATABASE openscm_inventory TO postgres;
GRANT ALL PRIVILEGES ON DATABASE openscm_order TO postgres;
GRANT ALL PRIVILEGES ON DATABASE openscm_suppliers TO postgres;

\connect openscm_suppliers;

-- Create supplier_types table (without description)
CREATE TABLE supplier_types (
    id SERIAL PRIMARY KEY,
    type_name VARCHAR(100) NOT NULL UNIQUE
);

-- Insert common supplier types
INSERT INTO supplier_types (type_name) VALUES
('Manufacturer'),
('Distributor'),
('Wholesaler'),
('Service Provider'),
('Retailer'),
('Raw Material Supplier'),
('Importer / Exporter'),
('Contract Manufacturer'),
('Logistics / Freight');