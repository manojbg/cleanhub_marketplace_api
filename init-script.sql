CREATE TABLE IF NOT EXISTS company (
    uuid UUID PRIMARY KEY,
    company_name VARCHAR(255),
    landing_page_route VARCHAR(255),
    state VARCHAR(50),
    quantity DOUBLE PRECISION,
    recovered_quantity DOUBLE PRECISION,
    contract_start_date DATE
    );

CREATE TABLE IF NOT EXISTS contracts (
    uuid UUID PRIMARY KEY,
    landing_page_route VARCHAR(255),
    created_at TIMESTAMP,
    start_date DATE,
    end_date DATE,
    period VARCHAR(255),
    quantity DOUBLE PRECISION,
    recovered_quantity DOUBLE PRECISION,
    is_fulfilled BOOLEAN
);