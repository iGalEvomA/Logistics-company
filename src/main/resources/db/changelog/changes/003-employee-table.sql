CREATE TABLE IF NOT EXISTS employee (
    email VARCHAR(225) PRIMARY KEY,
    token1 VARCHAR(225),
    token2 VARCHAR(225),
    type VARCHAR(45),
    office_address_id int,
    FOREIGN KEY(office_address_id) REFERENCES address(id)
);