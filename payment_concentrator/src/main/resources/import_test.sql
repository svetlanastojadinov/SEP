insert into payment_method(id,payment_type) values (1,'CARD');
insert into payment_method(id,payment_type) values (2,'PAYPAL');
insert into payment_method(id,payment_type) values (3,'BITCOIN');

insert into article(title,merchant_id,price) values ('Title1','pera',0.01);
insert into article(title,merchant_id,price) values ('Title2','pera',1);
insert into article(title,merchant_id,price) values ('Title3','pera',3.20);
insert into article(title,merchant_id,price) values ('Title4','pera',5);

insert into magazine(title,issn,subscription,price,merchant_id) values ('Title1',10000001,'READER_PAYS', 50,'mile');
insert into magazine(title,issn,subscription,price,merchant_id) values ('Title2',10000002,'READER_PAYS',70,'mile');
insert into magazine(title,issn,subscription,price,merchant_id) values ('Title3',10000003,'READER_PAYS',80,'mile');

insert into merchant(merchant_id, merchant_password, bank_url) values ('pera','pera', '8083');
insert into merchant(merchant_id, merchant_password, bank_url) values ('mile','mile', '8083');