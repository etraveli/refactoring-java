INSERT INTO Customer(id, name, loyalty_points)
VALUES(1, 'John', 5),
	  (2, 'Harry', 15);

INSERT INTO Movie (id, title, genre, rental_Price)
VALUES (1, 'Super Man', 'Action', 20.00),
       (2, 'Inception', 'Sci-Fi', 15.00),
       (3, 'The Shawshank Redemption', 'Drama', 18.00);

INSERT INTO MovieRental (id, rental_Amount, no_Of_Days, customer_id, movie_id)
VALUES (1, 25.00, 3, 1, 1),
       (2, 30.00, 5, 2, 2),
       (3, 35.00, 5, 2, 3);

select c.id, c.name, c.loyalty_points, m.title, m.genre, m.rental_price,
 r.no_of_days, r.rental_amount
from Customer c, Movie m, MovieRental r
where c.id = r.customer_id
and r.movie_id = m.id;