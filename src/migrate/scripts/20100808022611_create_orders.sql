--// create orders
-- Migration SQL that makes the change goes here.
create table orders(
	id int(11) NOT NULL auto_increment,
	name varchar(20),
	note varchar(200),
	
	created_by varchar(20),
	created_at datetime,
	updated_by varchar(20),
	updated_at datetime,
	PRIMARY KEY  (id)
)ENGINE=InnoDB default charset=utf8;


--//@UNDO
-- SQL to undo the change goes here.
drop table orders;

