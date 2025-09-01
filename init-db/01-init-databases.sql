-- Create databases for each service
CREATE DATABASE openscm_auth;
CREATE DATABASE openscm_inventory; 
CREATE DATABASE openscm_order;
CREATE DATABASE openscm_supplier;


-- Grant all privileges to postgres user (already owner, but just to be explicit)
GRANT ALL PRIVILEGES ON DATABASE openscm_auth TO postgres;
GRANT ALL PRIVILEGES ON DATABASE openscm_inventory TO postgres;
GRANT ALL PRIVILEGES ON DATABASE openscm_order TO postgres;
GRANT ALL PRIVILEGES ON DATABASE openscm_supplier TO postgres;
