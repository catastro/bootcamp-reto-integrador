--1.- crear base de datos con el nombre bd-favorite

--2.- Ejecutar el script para crear la tabla
CREATE TABLE favorite (
	id INT NOT NULL identity(1,1) primary key,
	serviceId INT NOT NULL,
	type VARCHAR(50) NOT NULL ,
	name VARCHAR(100) NOT NULL ,
	status INT NULL DEFAULT NULL,
	createdBy VARCHAR(50) NULL DEFAULT NULL,
	createdDate DATETIME NULL DEFAULT NULL,
	lastModifiedBy VARCHAR(50) NULL DEFAULT NULL,
	lastModifiedDate DATE NULL DEFAULT NULL,
	version INT NULL DEFAULT NULL
);