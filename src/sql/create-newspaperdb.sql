/* DELETE 'newspaperdb' database*/
DROP SCHEMA IF EXISTS newspaperdb;
/* DELETE USER 'spq' AT LOCAL SERVER*/
DROP USER IF EXISTS 'spq'@'localhost';

/* CREATE 'messagesdb' DATABASE */
CREATE SCHEMA newspaperdb;

/* CREATE THE USER 'spq' AT LOCAL SERVER WITH PASSWORD 'spq' */
CREATE USER IF NOT EXISTS 'spq'@'localhost' IDENTIFIED BY 'spq';

GRANT ALL ON newspaperdb.* TO 'spq'@'localhost';
