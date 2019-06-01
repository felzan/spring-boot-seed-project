create table rating
(
	id int auto_increment,
	customer_id varchar(128) null,
	account_type int null,
	rating float not null,
	comment text null,
	type_id int null,
	constraint rating_pk
		primary key (id)
);

