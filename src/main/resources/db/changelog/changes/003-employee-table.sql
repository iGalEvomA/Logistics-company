CREATE TABLE IF NOT EXISTS employee (
    email VARCHAR(225) PRIMARY KEY,
    name VARCHAR(225) NOT NULL,
    type VARCHAR(45) NOT NULL,
    office_address_id INT NOT NULL,
    FOREIGN KEY (office_address_id) REFERENCES address (id)
);