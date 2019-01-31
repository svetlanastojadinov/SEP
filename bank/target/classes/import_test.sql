insert into client_account(pan,security_code,card_holder_name,expiration_date,available_funds,reserved_funds) values ('4381310987654321','1234','Petar Petrovic',{ts '2020-11-29 18:47:52.69'},10000,0);
insert into client_account(pan,security_code,card_holder_name,expiration_date,available_funds,reserved_funds) values ('4381319876543210','2345','Marko Markovic',{ts '2020-11-29 18:47:52.69'},10000,0);
insert into client_account(pan,security_code,card_holder_name,expiration_date,available_funds,reserved_funds) values ('4183401234567890','3456','Jovan Jovanovic',{ts '2020-11-29 18:47:52.69'},10000,0);
insert into client_account(pan,security_code,card_holder_name,expiration_date,available_funds,reserved_funds) values ('4381311234567890','3456','Milan Milanovic',{ts '2020-11-29 18:47:52.69'},10000,0);

insert into client_merchant(merchant_id,merchant_password,username,client_account_pan) values ('pera','pera','Petar Petrovic','4381310987654321');
insert into client_merchant(merchant_id,merchant_password,username,client_account_pan) values ('mile','mile','Milan Milanovic','4381311234567890');

insert into transaction(id, merchant_id, merchant_password, merchant_order_id, merchant_timestamp, amount) values(1000000000,'pera','pera',1,{ts '2018-11-29 18:47:52.69'}, 99);