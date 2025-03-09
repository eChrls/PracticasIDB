create database if not EXISTS carreras;

use carreras;

create table if not EXISTS piloto(
DNI smallint(5) unsigned,
name varchar(255),
apellidos varchar(255),
ciudad varchar(50),
primary key (DNI)
);

create table if not EXISTS vehiculo(
 id_vehiculo smallint(5),
 tipo varchar(50),
 marca varchar(50),
 modelo varchar(50),
 matricula varchar(50),
 fecha_fabricante year,
 primary key (id_vehiculo)
);

create table if not EXISTS competicion(
DNI smallint(5) unsigned,
id_vehiculo smallint(5),
circuito varchar(20),
fecha date,
posicion tinyint(3) unsigned,
tiempo float,
foreign key (DNI) references piloto(DNI),
foreign key (id_vehiculo) references vehiculo(id_vehiculo),
primary key (DNI,id_vehiculo,fecha)
);

insert into vehiculo(id_vehiculo,tipo,marca,modelo,matricula,fecha_fabricante) 
values (1,'coche','Subaru','Spec C','0603KGL',null),
(2,'coche','Volvo','142 GT','7975GHH',null), 
(3,'coche','Renault',null,'5673GFC',null), 
(4,'coche','Mitsubishi',null,'9877BML',1994),
(5,'coche','Mitsubishi','Starion ESI-Rn','6615HLL',1998),
(6,'motocicleta','BigNMotors','625DSX','7017BBK',2025),
(7,'motocicleta','BigNMotors','RX4 401','8128KFF',2025),
(8,'karts','KoopaKart','LR6','6904FMD',2012),
(9,'karts','KoopaKart','SIGMA DD2','7978FMK',2006),
(10,'karts','KoopaKart','SIGMA KZ','1093MLN',2021),
(11,'motocicleta','M&L Customs','NajiTY','8513KFL',2000);

insert into piloto(DNI,name,apellidos,ciudad)
values (1,'Max','Verstappen', 'Paises Bajos'),
(2,'Lando','Norris','Reino Unido'),
(3,'Gabriel','Bortoleto','Brasil'),
(4,'Isack','Hadjar','Francia'),
(5,'Jack','Doohan','Australia'),
(6,'Pierre','Gasly','Francia'),
(7,'Andrea','Kimi Antonelli','Italia'),
(8,'Fernando','Alonso','España'),
(9,'Charles',null,'Monaco'),
(10,'Lance','Stroll','Canada'),
(11,'Yuki',null,'Japon');

insert into competicion(DNI,id_vehiculo,circuito,fecha,posicion,tiempo)
values 
(1,1,'Rainbow Road','2025-01-11',3,28.53),
(2,2,'Royal Raceway','2025-01-11',8,57.20),
(3,3,'Silverstone','2025-01-11',2,34.56),
(4,4,'Nurburgring','2025-01-11',9,78.86),
(6,6,'Silverstone','2024-01-11',6,56.89),
(7,7,'Silverstone','2024-01-23',4,78.90),
(7,7,'Imola','2023-02-13',1,23.78),
(11,11,'Silverstone','2020-12-12',1,45.23),
(11,11,'Royal Raceway','2019-11-12',1,78.56),
(5,5,'Rainbow Road','2023-10-02',3,90.00),
(8,8,'Nurburgring','2019-06-12',8,126.78),
(9,9,'Royal Raceway','2018-04-03',6,34.90),
(10,10,'Imola','2020-05-09',3,67.89);












