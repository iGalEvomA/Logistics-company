CREATE TABLE IF NOT EXISTS package (
    id int AUTO_INCREMENT PRIMARY KEY,
    status VARCHAR(225),
    weight DOUBLE NOT NULL,
    price DOUBLE NOT NULL,
    date_of_payment DATE,
    time_of_payment TIME,
    sender_email VARCHAR(100) NOT NULL,
    FOREIGN KEY(sender_email) REFERENCES customer(email),
    receiver_email VARCHAR(100) NOT NULL,
    FOREIGN KEY(receiver_email) REFERENCES customer(email),
    delivery_address_id int NOT NULL,
    FOREIGN KEY(delivery_address_id) REFERENCES address(id),
    register_email VARCHAR(100) NOT NULL,
    FOREIGN KEY(register_email) REFERENCES employee(email),
    courier_email VARCHAR(100) NOT NULL,
    FOREIGN KEY(courier_email) REFERENCES employee(email)
);