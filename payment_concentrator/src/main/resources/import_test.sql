insert into payment_method(id,payment_type) values (1,'CARD');
insert into payment_method(id,payment_type) values (2,'PAYPAL');
insert into payment_method(id,payment_type) values (3,'BITCOIN');

insert into article(title,author_username) values ('Title1','username1');
insert into article(title,author_username) values ('Title2','username2');
insert into article(title,author_username) values ('Title3','username3');
insert into article(title,author_username) values ('Title4','username4');

insert into magazine(title,issn,subscription) values ('Title1',10000001,'READER_PAYS');
insert into magazine(title,issn,subscription) values ('Title2',10000002,'READER_PAYS');
insert into magazine(title,issn,subscription) values ('Title3',10000003,'READER_PAYS');

insert into order_table(payment_type, price, date_of_transaction,payer_username, executed) values ('CARD',100,{ts '2018-11-29 18:47:52.69'},'buyer1',0);
insert into order_table(payment_type, price, date_of_transaction,payer_username, executed) values ('PAYPAL',100,{ts '2018-11-28 18:47:52.69'},'buyer2',1);
insert into order_table(payment_type, price, date_of_transaction,payer_username, executed) values ('BITCOIN',100,{ts '2018-11-27 18:47:52.69'},'buyer3',0);
insert into order_table(payment_type, price, date_of_transaction,payer_username, executed) values ('CARD',100,{ts '2018-11-26 18:47:52.69'},'buyer1',0);

