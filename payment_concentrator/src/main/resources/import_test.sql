insert into role (id,name) values (1,'USER');
insert into role (id,name) values (2,'ADMIN');

-- username == password
insert into user (id,username,role_id,password,email,name,surname) values (1,'vladimir',1,'$2a$10$oP2Vbqb0JvjYB9soV9yL2e6409UfcEkC91KLY9kDhCTNrNxugXfCG','vlada.jova@yahoo.com','Vladimir','Jovicic');
insert into user (id,username,role_id,password,email,name,surname) values (2,'milan',1,'$2a$10$bVikXYQemT0hIWmBCZWueeixC4zrWFjsEljCWhMFr1pjZofbPDnmS','milan@milan.com','Milan','Milanovic');
insert into user (id,username,role_id,password,email,name,surname) values (3,'ivan',1,'$2a$10$5Ti6Xe7AdH0Poe5h9z3S3.QjKEAScAoEIXKisSUavEzM9XIP8D1B2','ivan@ivan.com','Ivan','Ivanovic');
insert into user (id,username,role_id,password,email,name,surname) values (4,'pera',2,'$2a$10$6LSbrt6YghsXu.AvvKEihuHfB/IlwL66QLz38VM5lpCQfb9oHQwie','pera@pera.com','Petar','Petrovic');
insert into user (id,username,role_id,password,email,name,surname) values (5,'stefan',2,'$2a$10$7wNWpb9ZVaFwjQ/fWfAX8e.Pw9fSJHPGi3V2nmuhT1kMUtUYNmPsm','stefan@stefan.com','Stefan','Stefanovic');


insert into payment_method(id,payment_type) values (1,'CARD');
insert into payment_method(id,payment_type) values (2,'PAYPAL');
insert into payment_method(id,payment_type) values (3,'BITCOIN');

insert into article(title,user_id,author) values ('Title1',1,'vladimir');
insert into article(title,user_id,author) values ('Title2',1,'vladimir');
insert into article(title,user_id,author) values ('Title3',2,'milan');
insert into article(title,user_id,author) values ('Title4',2,'nilan');

insert into magazine(title,issn,subscription,user_id,author) values ('Title1',10000001,'READER_PAYS',1,'vladimir');
insert into magazine(title,issn,subscription,user_id,author) values ('Title2',10000002,'READER_PAYS',2,'milan');
insert into magazine(title,issn,subscription,user_id,author) values ('Title3',10000003,'READER_PAYS',3,'ivan');

insert into order_table(payment_type, price, date_of_transaction,merchant_id, executed) values ('CARD',100,{ts '2018-11-29 18:47:52.69'},'buyer1',0);
insert into order_table(payment_type, price, date_of_transaction,merchant_id, executed) values ('PAYPAL',100,{ts '2018-11-28 18:47:52.69'},'buyer2',1);
insert into order_table(payment_type, price, date_of_transaction,merchant_id, executed) values ('BITCOIN',100,{ts '2018-11-27 18:47:52.69'},'buyer3',0);
insert into order_table(payment_type, price, date_of_transaction,merchant_id, executed) values ('CARD',100,{ts '2018-11-26 18:47:52.69'},'buyer1',0);

