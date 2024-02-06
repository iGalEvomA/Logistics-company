INSERT INTO logistics_company.package (id, status, weight, price, date_of_payment, time_of_payment, sender_email, receiver_email, delivery_address_id, register_email, courier_email) VALUES
    ('1', 'delivering', '2.0', '5.6', null, null, 'petya@mail.com', 'minka@mail.com', '2', 'petarpetrov1@mail.com', 'pencho@mail.com'),
    ('2', 'delivering', '6.0', '12.8', null, null, 'minka@mail.com', 'yasen@mail.com', '3', 'ivanivanov@mail.com', 'stoyan@mail.com'),
    ('3', 'paid', '1.6', '4.8', '2024-01-24', '16:20:36', 'stoyanka@mail.com', 'martin@mail.com', '1', 'petarpetrov1@mail.com', 'marin@mail.com'),
    ('4', 'paid', '3.4', '7.3', '2024-02-01', '09:16:52', 'lyubo@mail.com', 'martin@mail.com', '4', 'maria@mail.com', 'pencho@mail.com'),
    ('5', 'paid', '8.2', '16.8', '2024-01-24', '16:20:36', 'yasen@mail.com', 'petya@mail.com', '4', 'maria@mail.com', 'mincho@mail.com');