CREATE TABLE IF NOT EXISTS package (
    id int PRIMARY KEY,
    status VARCHAR(225),
    weight DOUBLE,
    price DOUBLE,
    date_of_payment DATE,
    time_of_payment TIMESTAMP,
    sender_email VARCHAR(100),
    FOREIGN KEY(sender_email) REFERENCES customer(email),
    receiver_email VARCHAR(100),
    FOREIGN KEY(receiver_email) REFERENCES customer(email),
    delivery_address_id int,
    FOREIGN KEY(delivery_address_id) REFERENCES address(id),
    register_email VARCHAR(100),
    FOREIGN KEY(register_email) REFERENCES employee(email),
    courier_email VARCHAR(100),
    FOREIGN KEY(courier_email) REFERENCES employee(email)
);