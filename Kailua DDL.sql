DROP DATABASE IF EXISTS kailua;
CREATE DATABASE kailua;

USE kailua;

CREATE TABLE renter
(
driver_license_number	VARCHAR(8) 		PRIMARY KEY,
name					VARCHAR(255)	NOT NULL,
address					VARCHAR(255)	NOT NULL,
zip						VARCHAR(4)		NOT NULL,
city					VARCHAR(255)	NOT NULL,
mobile_phone			VARCHAR(8)		NOT NULL,
phone					VARCHAR(8),		
email					VARCHAR(255)	NOT NULL
); 

CREATE TABLE contract
(
contract_id				VARCHAR(255) 	NOT NULL,
from_date_time			DATE 			NOT NULL,
driver_licence_number	VARCHAR(8) 		NOT NULL,
registration_number		VARCHAR(7) 		NOT NULL,
to_date_time			DATE 			NOT NULL,
odometer_at_start		INTEGER(6) 		NOT NULL
);

CREATE TABLE car
(
registration_number			VARCHAR(7)		NOT NULL,
brand						VARCHAR(255)	NOT NULL,
model						VARCHAR(255)	NOT NULL,
fuel_type					VARCHAR(255)	NOT NULL,
odometer					INTEGER(6)		NOT NULL,
first_registration_mon_yr	DATE			NOT NULL,
rental_type					ENUM('luxury', 'family', 'sport')	NOT NULL
);

INSERT INTO renter
VALUES
('12345678', 'Lars Larsen', 'flot vej 5', '1234', 'A-town', '12345678', ' ', 'mail@mail.dk');