create sequence accounts_seq start with 1 increment by 50;
create sequence clients_seq start with 1 increment by 50;
create sequence transactions_seq start with 1 increment by 50;
create table accounts (account_type tinyint not null check (account_type between 0 and 1), initial_balance integer not null, client_entity_id bigint, client_id bigint, id bigint not null, account_number varchar(255) not null, primary key (id));
create table clients (age integer not null check ((age>=18) and (age<=150)), gender char(1) not null, status boolean not null, id bigint not null, address varchar(255) not null, client_id varchar(255) not null, id_number varchar(255) not null, name varchar(255) not null, password varchar(255) not null, phone_number varchar(255) not null, primary key (id));
create table transactions (amount integer not null, balance integer not null, transaction_type tinyint check (transaction_type between 0 and 1), account_id bigint, date timestamp(6) not null, id bigint not null, primary key (id));
alter table if exists accounts add constraint FKn8g49lbm7tbuali65q5cicrge foreign key (client_entity_id) references clients;
alter table if exists transactions add constraint FK20w7wsg13u9srbq3bd7chfxdh foreign key (account_id) references accounts;
