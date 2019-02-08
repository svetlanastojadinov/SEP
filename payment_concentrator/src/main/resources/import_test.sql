insert into role (id,name) values (1,'KORISNIK');
insert into role (id,name) values (2,'AUTOR');
insert into role (id,name) values (3,'UREDNIK');
insert into role (id,name) values (4,'ADMIN');

-- username == password
insert into user (id,username,role_id,password,email,name,surname) values (1,'vladimir',1,'$2a$10$oP2Vbqb0JvjYB9soV9yL2e6409UfcEkC91KLY9kDhCTNrNxugXfCG','vlada.jova@yahoo.com','Vladimir','Jovicic');
insert into user (id,username,role_id,password,email,name,surname) values (2,'milan',2,'$2a$10$bVikXYQemT0hIWmBCZWueeixC4zrWFjsEljCWhMFr1pjZofbPDnmS','milan2-SEP@milan.com','Milan','Milanovic');
insert into user (id,username,role_id,password,email,name,surname) values (3,'ivan',1,'$2a$10$5Ti6Xe7AdH0Poe5h9z3S3.QjKEAScAoEIXKisSUavEzM9XIP8D1B2','ivan-SEP@ivan.com','Ivan','Ivanovic');
insert into user (id,username,role_id,password,email,name,surname) values (4,'pera',2,'$2a$10$6LSbrt6YghsXu.AvvKEihuHfB/IlwL66QLz38VM5lpCQfb9oHQwie','pera-SEP@pera.com','Petar','Petrovic');
insert into user (id,username,role_id,password,email,name,surname) values (5,'stefan',2,'$2a$10$7wNWpb9ZVaFwjQ/fWfAX8e.Pw9fSJHPGi3V2nmuhT1kMUtUYNmPsm','stefan-SEP@stefan.com','Stefan','Stefanovic');
insert into user (id,username,role_id,password,email,name,surname) values (6,'sima',3,'$2a$10$vqpfUrrnjfS1yZtYMr2sEOCjfKRrpSHk.luTvO5gcsfRwGTju5p1e','sima2-SEP@sima.com','Sima','Simic');
insert into user (id,username,role_id,password,email,name,surname) values (7,'admin',4,'$2a$10$aUUi0OVjyXxCTzTl3jnCiuNKrpR4DW9vYBvaMpHuj76Q4JJwpySBC','vladimirjovicic95@yahoo.com','Admin','Admin');


insert into payment_method(id,payment_type) values (1,'CARD');
insert into payment_method(id,payment_type) values (2,'PAYPAL');
insert into payment_method(id,payment_type) values (3,'BITCOIN');

insert into membership (id,payed,pay_day) values (1,0,'2018-03-09');

insert into magazine(title,issn,subscription,user_id,author, price) values ('Filomat',93545180,'READER_PAYS',3,'pera', 50);
insert into magazine(title,issn,subscription,user_id,author, price) values ('Zbornik Medicinski pregled',99258105,'READER_PAYS',3,'stefan',70);
insert into magazine(title,issn,subscription,user_id,author,price,membership_id) values ('Zbornik Matice srpske za prirodne nauke',14509636,'OPEN_ACCESS',6,'sima',80,1);

insert into article(title,price, user_id, author,magazine_issn) values ('Detekcija mikotoksina putem različitih analitičkih metoda',0.01,4,'milan',14509636);
insert into article(title,price, user_id, author,magazine_issn) values ('Vrednovanje ciljeva upravljanja spomenicima prirode primenom smart i smarter metoda',1.00,4,'milan',14509636);
insert into article(title,price, user_id, author,magazine_issn) values ('Negativni joni i njihova uloga u razvoju nauke i tehnologije',3.20,2,'stefan',99258105);
insert into article(title,price, user_id, author,magazine_issn) values ('Neurorehabilitacija aleksije bez agrafije – prikaz slučaja.',2.50,2,'stefan',99258105);
insert into article(title,price, user_id, author,magazine_issn) values ('Lečenje plućne embolije',5.00,2,'stefan',99258105);
insert into article(title,price, user_id, author,magazine_issn) values ('Savremeni principi dijagnostike i lečenja reaktivnog artritisa',1.75,2,'stefan',99258105);
insert into article(title,price, user_id, author,magazine_issn) values ('Osteoid osteoma kod mladog sportiste',4.20,2,'stefan',99258105);
insert into article(title,price, user_id, author,magazine_issn) values ('Učestalost pojave aflatoksina b1 u namirnicama biljnog porijeka',1.20,2,'milan',14509636);
insert into article(title,price, user_id, author,magazine_issn) values ('Primena atr-ftir analize za određivanje fumonizina u kukuruzu',2.00,2,'milan',14509636);
insert into article(title,price, user_id, author,magazine_issn) values ('Akvizicioni parametri trostrukog kvadropola lc/ms određivanja citrinina',0.02,2,'milan',14509636);
insert into article(title,price, user_id, author,magazine_issn) values ('Antioksidativna svojstva sadnica soje inokulisanih sa Trichoderma asperellum',1.09,2,'milan',14509636);
insert into article(title,price, user_id, author,magazine_issn) values ('Hipernearni prstenovi sa defektom distributivnosti',4.20,2,'pera',93545180);
insert into article(title,price, user_id, author,magazine_issn) values ('Napomena o uzastopnim koeficijentima spiralnih funkcije',0.01,2,'pera',93545180);

insert into merchant(merchant_id, merchant_password, bank_url) values ('pera','$2a$10$6LSbrt6YghsXu.AvvKEihuHfB/IlwL66QLz38VM5lpCQfb9oHQwie', '8083');
insert into merchant(merchant_id, merchant_password, bank_url) values ('milan','$2a$10$bVikXYQemT0hIWmBCZWueeixC4zrWFjsEljCWhMFr1pjZofbPDnmS', '8083');
insert into merchant(merchant_id, merchant_password, bank_url) values ('stefan','$2a$10$7wNWpb9ZVaFwjQ/fWfAX8e.Pw9fSJHPGi3V2nmuhT1kMUtUYNmPsm', '8083');

insert into order_table(merchant_order_id, executed) values (1000000000,0);