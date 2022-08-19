-- noinspection SqlNoDataSourceInspectionForFile

-- noinspection SqlDialectInspectionForFile

INSERT INTO MOVIE (id, title, code)
VALUES ('F001', 'You have got mail', 'regular');
INSERT INTO MOVIE (id, title, code)
VALUES ('F002', 'Matrix', 'regular');
INSERT INTO MOVIE (id, title, code)
VALUES ('F003', 'Cars', 'childrens');
INSERT INTO MOVIE (id, title, code)
VALUES ('F004', 'Fast & Furious X', 'new');

INSERT INTO CUSTOMER (id, customer_name)
VALUES ('CUX001', 'C. U. Stomer');

INSERT INTO MOVIE_RENTAL (id, movie_id, days, customer_id)
VALUES ('MR001', 'F001', 3, 'CUX001');
INSERT INTO MOVIE_RENTAL (id, movie_id, days, customer_id)
VALUES ('MR002', 'F002', 1, 'CUX001');
