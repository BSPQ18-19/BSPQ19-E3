/* TO-DO */
/* DELETE 'newspaperDB' database*/
DROP SCHEMA IF EXISTS newspaperDB;
/* DELETE USER 'spq' AT LOCAL SERVER*/
DROP USER IF EXISTS 'spq'@'localhost';

/* CREATE 'messagesDB' DATABASE */
CREATE SCHEMA newspaperDB;

/* CREATE THE USER 'spq' AT LOCAL SERVER WITH PASSWORD 'spq' */
CREATE USER IF NOT EXISTS 'spq'@'localhost' IDENTIFIED BY 'spq';

GRANT ALL ON newspaperDB.* TO 'spq'@'localhost';
