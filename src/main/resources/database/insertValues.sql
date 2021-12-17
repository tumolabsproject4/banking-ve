insert into user
values (null, 'login', '$2a$10$7L6flv6X.dtMgtEwcz8bBO82IPVn2OaPhlaGuVhW1FjV.lkuDyIry'); -- password

insert into bank(bank_name, address)
values ('Acba Credit Agricole Bank', '35/1 Arshakunyac Street, Yerevan 0026');

insert into employee
values (null, 'Vahan', 'Baldryan', '30', 320000.0, 'Proshyan 6', 'Marketing', true, 3,null),
       (null, 'Mariam', 'Mkhitaryan', '23', 255000.0, 'Baghramyan 8', 'Finances', true, 3,null),
       (null, 'Lusine', 'Melikyan', '44', 180000.0, 'Paruyr Sevak 106', 'HR', true, 4,null),
       (null, 'Lilya', 'Avetisyan', '23', 543000.0, 'Hanrapetutyan 8','PM', true, 4,null);
