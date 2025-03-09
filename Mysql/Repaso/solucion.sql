-- Ejercicio1
select e.Dni, IF(e.Apellido is null,"",CONCAT(e.Nombre," ",e.Apellido)) "Nombre completo",e.Casa,e.Sexo  
from hogwarts.estudiante e
where e.Edad is NULL 
order by e.Nombre ,e.Apellido;

-- Ejercicio 2
select e.Nombre, IF (e.Apellido is null, "Desconocido", e.Apellido) Apellido , e.Casa 
from hogwarts.estudiante e 
where e.Nombre like "H%" OR LENGTH(e.Nombre) = 5; 

-- Ejercicio 3
select e.Nombre, e.Apellido 
from hogwarts.estudiante e inner join hogwarts.matricula m on m.Dni =e.Dni INNER join hogwarts.asignatura a on a.ID_AS = m.ID_AS
where e.Edad BETWEEN 19 and 20 and a.ID_AS in ("HDM","DMA","POC")
GROUP by e.Nombre,e.Apellido;

-- Ejercicio 4
select e.Nombre, e.Apellido 
from hogwarts.estudiante e inner join hogwarts.matricula m on m.Dni =e.Dni INNER join hogwarts.asignatura a on a.ID_AS = m.ID_AS
where e.Sexo = 'F' and a.Tipo ="Semestral"

-- Ejercicio 5 
select e.Dni, e.Nombre, e.Apellido, a.ID_AS, a.Nombre, m.calificacion 
from hogwarts.estudiante e inner join hogwarts.matricula m on m.Dni =e.Dni INNER join hogwarts.asignatura a on a.ID_AS = m.ID_AS
where m.calificacion <5;

-- Ejercicio 6
SELECT count(*)
from hogwarts.estudiante e inner join hogwarts.matricula m on m.Dni =e.Dni INNER join hogwarts.asignatura a on a.ID_AS = m.ID_AS
where e.Casa in ("Gryffindor","Hufflepuff") and a.Tipo ="Obliga" and a.Curso <> 1;

-- Ejercicio 7
select e.Nombre, e.Apellido 
from hogwarts.estudiante e inner join hogwarts.matricula m on m.Dni =e.Dni INNER join hogwarts.asignatura a on a.ID_AS = m.ID_AS
where m.calificacion =(select MAX(m.calificacion) from hogwarts.matricula m);

-- Ejercio 8
select e.Casa, ROUND(AVG(m.calificacion),0) as "Nota media"
from hogwarts.estudiante e inner join hogwarts.matricula m on m.Dni =e.Dni INNER join hogwarts.asignatura a on a.ID_AS = m.ID_AS
where YEAR(m.fecha) BETWEEN 2023 and 2024 and m.calificacion >4
group by e.Casa 
order by "Nota media" desc ;

-- Ejercicio 9
select e.Dni, CONCAT(e.Nombre," ",e.Apellido) as "estudiantes", SUM(a.CTeoria+a.CPracticas) "creditos aprobados"
from hogwarts.estudiante e inner join hogwarts.matricula m on m.Dni =e.Dni INNER join hogwarts.asignatura a on a.ID_AS = m.ID_AS
where m.calificacion >4
GROUP by e.Dni 
ORDER  by SUM(a.CTeoria+a.CPracticas) desc;

-- Ejercicio 10

select IF(e.Apellido is null,"",CONCAT(e.Nombre," ",e.Apellido)) "Estudiante", a.Nombre, m.calificacion 
from hogwarts.estudiante e left join hogwarts.matricula m on m.Dni =e.Dni left join hogwarts.asignatura a on a.ID_AS = m.ID_AS
order by e.Nombre, m.calificacion ;







