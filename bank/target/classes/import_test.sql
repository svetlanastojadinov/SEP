insert into client_account(pan,security_code,card_holder_name,expiration_date,available_funds,reserved_funds) values ('1123','123','123',{ts '2020-11-29 18:47:52.69'},10000,0);
insert into client_account(pan,security_code,card_holder_name,expiration_date,available_funds,reserved_funds) values ('1234','234','234',{ts '2020-11-29 18:47:52.69'},10000,0);
insert into client_account(pan,security_code,card_holder_name,expiration_date,available_funds,reserved_funds) values ('1456','456','456',{ts '2020-11-29 18:47:52.69'},10000,0);

insert into client_merchant(merchant_id,merchant_password,username,client_account_pan) values ('123','123','username','1123');

insert into transaction(merchant_id, merchant_password, merchant_order_id, merchant_timestamp, amount) values('123','123',1,{ts '2020-11-29 18:47:52.69'}, 99);