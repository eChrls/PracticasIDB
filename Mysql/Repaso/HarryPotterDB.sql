create database if not exists hogwarts;

use hogwarts;

create table if not exists estudiante(
Dni int(11),
Nombre varchar(20),
Apellido varchar(20),
Casa varchar(20),
Edad decimal(3,0),-- esto es lo mas absurdo que he visto en mi vida
Sexo char(1),
primary key (Dni)
);

create table if not exists asignatura(
ID_AS varchar(3), -- me duelen los ojos de ver como id un puto varchar
Nombre varchar(30),
Curso int(11),
Tipo varchar(9),
CTeoria decimal(4,1),
CPracticas decimal(4,1),
Caracter varchar(15),
primary key (ID_AS)
);

create table if not exists matricula(
 ID_AS varchar(3),
 Dni int(11),
 calificacion int(11),
 fecha date,
 primary key (ID_AS,Dni),
 foreign key (ID_AS) references asignatura(ID_AS),
 foreign key (Dni) references estudiante(Dni)
);

insert into estudiante(Dni,Nombre,Apellido,Casa,Edad,Sexo) values
(1asignaturaasignaturaasignaturaestudiantematriculaestudiante,"Ron","Weasley","Gryffindor",null,'M'),
(2,"Harry","Potter","Gryffindor",null,'M'),
(3,"Hermione",null,"Gryffindor",null,'F'),
(4,"Colin","Creevey","Gryffindor",12,'M'),
(5,"Draco","Malfoy","Slytherin",20,'M'),
(6,"Cho","Chan","Ravenclaw",19,'F'),
(7,"Cedric","Diggory","Hufflepuff",21,'M');

insert into asignatura(ID_AS,Nombre,Curso,Tipo,Cteoria,Cpracticas,Caracter) values
("HDM","Historia de la magia",1,"Obliga",12.3,11.9,"Binns"),
("CMA", "Cuidados de Criaturas Magicas",2,"Semestral",4.2,19.6,"Hagrid"),
("POC","pociones",2,"Obliga",5.6,17.5,"Snape"),
("ESM", "Estudios Muggles",4,"Semestral",14.3,8.7,"Burbage"),
("TRA", "Transformaciones",3,"Obliga",12.3,23.6,"McGonagall");

insert into matricula(ID_AS,Dni,calificacion,fecha) values
("HDM",1,4,"2022-02-10"),
("HDM",2,6,"2022-02-10"),
("HDM",3,10,"2022-02-10"),
("HDM",4,5,"2022-02-10"),
("HDM",5,2,"2022-02-10"),
("HDM",6,1,"2022-02-10"),
("HDM",7,7,"2022-02-10"),
("CMA",1,7,"2023-02-11"),
("CMA",2,10,"2023-02-11"),
("CMA",7,10,"2023-02-11"),
("POC",1,1,"2022-02-10"),
("POC",2,5,"2022-02-10"),
("POC",3,10,"2022-02-10"),
("POC",4,5,"2022-02-10"),
("POC",5,7,"2022-02-10"),
("POC",6,3,"2022-02-10"),
("POC",7,5,"2022-02-10"),
("ESM",3,10,"2024-02-10"),
("TRA",1,5,"2022-02-10"),
("TRA",2,8,"2022-02-10"),
("TRA",3,10,"2022-02-10"),
("TRA",4,4,"2022-02-10"),
("TRA",5,2,"2022-02-10"),
("TRA",6,7,"2022-02-10"),
("TRA",7,4,"2022-02-10");









