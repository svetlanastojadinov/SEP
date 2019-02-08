insert into client_account(pan,security_code,card_holder_name,expiration_date,available_funds,reserved_funds) values ('4381310987654321','1234','Petar Petrovic',{ts '2020-11-29 18:47:52.69'},10000,0);
insert into client_account(pan,security_code,card_holder_name,expiration_date,available_funds,reserved_funds) values ('4381319876543210','2345','Stefan Stefanovic',{ts '2020-11-29 18:47:52.69'},10000,0);
insert into client_account(pan,security_code,card_holder_name,expiration_date,available_funds,reserved_funds) values ('4381318765432109','3456','Milan Milanovic',{ts '2020-11-29 18:47:52.69'},10000,0);

insert into client_account(pan,security_code,card_holder_name,expiration_date,available_funds,reserved_funds) values ('4183401234567890','3456','Vladimir Jovicic',{ts '2020-11-29 18:47:52.69'},10000,0);
insert into client_account(pan,security_code,card_holder_name,expiration_date,available_funds,reserved_funds) values ('4183402345678901','3456','Ivan Ivanovic',{ts '2020-11-29 18:47:52.69'},10000,0);
insert into client_account(pan,security_code,card_holder_name,expiration_date,available_funds,reserved_funds) values ('4381313456789012','3456','Jovan Jovanovic',{ts '2020-11-29 18:47:52.69'},10000,0);

insert into client_merchant(merchant_id,merchant_password,username,client_account_pan) values ('pera','$2a$10$6LSbrt6YghsXu.AvvKEihuHfB/IlwL66QLz38VM5lpCQfb9oHQwie','Petar Petrovic','4381310987654321');
insert into client_merchant(merchant_id,merchant_password,username,client_account_pan) values ('milan','$2a$10$bVikXYQemT0hIWmBCZWueeixC4zrWFjsEljCWhMFr1pjZofbPDnmS','Milan Milanovic','4381318765432109');
insert into client_merchant(merchant_id,merchant_password,username,client_account_pan) values ('stefan','$2a$10$7wNWpb9ZVaFwjQ/fWfAX8e.Pw9fSJHPGi3V2nmuhT1kMUtUYNmPsm','Stefan Stefanovic','4381319876543210');

insert into transaction(id, merchant_id, merchant_password, merchant_order_id, merchant_timestamp, amount) values(1000000000,'pera','$2a$10$6LSbrt6YghsXu.AvvKEihuHfB/IlwL66QLz38VM5lpCQfb9oHQwie',1,{ts '2018-11-29 18:47:52.69'}, 99);