create table if not exists check_types(
id uuid default gen_random_uuid() not null primary key,
type varchar(30) not null unique
);

insert into check_types(type) values('KRA'),('CRB'),('IPRS') on conflict(type) do nothing;
