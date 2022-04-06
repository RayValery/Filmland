INSERT INTO customer (email, password) VALUES
     ('vsalivon.filmland@gmail.com', '$2a$10$qwVplsiHBqn/KDB8BAPUPueaxcP5dgtzdkFIAA.hIt1D0cL2p7kC.'),
     ('ochornyi.filmland@gmail.com', '$2a$10$qwVplsiHBqn/KDB8BAPUPueaxcP5dgtzdkFIAA.hIt1D0cL2p7kC.');

INSERT INTO film_category (name, price, available_content) VALUES
      ( 'American series', 6.0, 10 ),
      ( 'Ukrainian films', 4.0, 30 );

INSERT INTO SUBSCRIPTION (START_DATE, payment_date, REMAINING_CONTENT, FILM_CATEGORY_ID) VALUES
    ('2022-03-05 00:00:00', '2022-04-06 13:51:00', 8, 1);

INSERT INTO customer_subscription (customer_id, subscription_id) VALUES
    ( 1, 1 )