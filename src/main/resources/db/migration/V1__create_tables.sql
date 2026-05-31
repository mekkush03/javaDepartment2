CREATE TABLE IF NOT EXISTS department (
    id            BIGSERIAL PRIMARY KEY,
    name          VARCHAR(255) NOT NULL,
    working_hours VARCHAR(50)  NOT NULL
);

CREATE TABLE IF NOT EXISTS product (
    id            BIGSERIAL PRIMARY KEY,
    name          VARCHAR(255)   NOT NULL,
    price         NUMERIC(10, 2) NOT NULL,
    department_id BIGINT REFERENCES department (id) ON DELETE CASCADE
);
