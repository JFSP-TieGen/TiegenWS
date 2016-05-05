DELETE FROM USERINFO;
DELETE FROM SERVICEPROVIDERINFO;
DELETE FROM RATINGS;
DELETE FROM BOOKINGS;
DELETE FROM BOOKMARKS;

INSERT IGNORE INTO RATINGS (id, userId, serviceId, bookingId, rating, review) VALUES(1, 1, 1, 2, 2.34, "hello there");
INSERT IGNORE INTO BOOKINGS (id, userId, serviceId, date) values(2, 1, 1, '2016-05-03');
insert ignore into bookmarks (id, userId, serviceId) values (2, 1, 1);
insert ignore into serviceproviderinfo(id, name, type, location, website, rating, price) values( 1, "foo", "bar", "bla", "web", 5.968, 99.99);
insert ignore into userinfo(id, uname, password) values (1, "kt", "password");


