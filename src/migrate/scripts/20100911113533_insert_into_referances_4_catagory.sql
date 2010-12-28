--// insert into referances 4 catagory
-- Migration SQL that makes the change goes here.
INSERT INTO referances VALUES (301,'Catagory','Default','Default','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (302,'Catagory','Default','д╛хо','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());



--//@UNDO
-- SQL to undo the change goes here.
DELETE FROM referances WHERE ID = 301;
DELETE FROM referances WHERE ID = 302;

