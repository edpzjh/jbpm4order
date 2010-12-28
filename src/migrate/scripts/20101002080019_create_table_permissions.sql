--// create table permissions
-- Migration SQL that makes the change goes here.
create table permissions(
	id int(11) NOT NULL auto_increment,
	permission varchar(50),

	created_by varchar(20),
	created_at datetime,
	updated_by varchar(20),
	updated_at datetime,
	PRIMARY KEY  (id)
);


--//@UNDO
-- SQL to undo the change goes here.
drop table permissions;

