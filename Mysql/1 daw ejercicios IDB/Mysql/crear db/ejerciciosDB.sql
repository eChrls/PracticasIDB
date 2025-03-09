CREATE database IF NOT EXISTS CONSERVATORIO;

USE CONSERVATORIO;

CREATE TABLE IF NOT EXISTS MUSICOS (
idMusico BIGINT PRIMARY KEY auto_increment,
dni	VARCHAR(10) UNIQUE NOT NULL,
nombre VARCHAR(50) UNIQUE NOT NULL,
apellidos VARCHAR (30) NOT NULL,
direccion VARCHAR (40) NOT NULL,
telefono VARCHAR (12) NOT NULL,
fecha_alta DATE DEFAULT (CURRENT_DATE)
);

CREATE TABLE IF NOT EXISTS COMPOSICIONES (
idComposicion BIGINT PRIMARY KEY auto_increment,
nombre VARCHAR (16),
tipo VARCHAR (14),
instrumental BOOLEAN
);

CREATE TABLE IF NOT EXISTS MOVIMIENTOS (
idMovimiento BIGINT auto_increment,
duracion NUMERIC,
idComposicion BIGINT,
primary key (idMovimiento, idComposicion),
FOREIGN KEY (idComposicion) REFERENCES COMPOSICIONES(idComposicion) ON DELETE NO ACTION ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS INTERPRETA (
fecha DATE,
cache numeric(6),
idComposicion bigint, idMovimiento bigint, idMusico bigint,
primary key (idMusico, idComposicion, idMovimiento, fecha),
FOREIGN KEY (idComposicion) REFERENCES COMPOSICIONES(idComposicion) ON DELETE NO ACTION ON UPDATE CASCADE,
FOREIGN KEY (idMovimiento, idComposicion) REFERENCES MOVIMIENTOS(idMovimiento, idComposicion) ON DELETE NO ACTION ON UPDATE CASCADE,
FOREIGN KEY (idMusico) REFERENCES MUSICOS (idMusico) ON DELETE NO ACTION ON UPDATE CASCADE
);
-- añadir campo localidad a musicos
ALTER TABLE musicos
ADD localidad VARCHAR (100);

-- eliminar campo tipo de composiciones
ALTER TABLE composiciones
DROP COLUMN tipo; 

-- modificar nombres del campo cache a importe_pago
ALTER TABLE interpreta
RENAME COLUMN cache TO importe_pago;

-- añadir indice al campo nombre en COMPOSICIONES
CREATE INDEX idx_nombre
ON composiciones(nombre);

-- añadir opcion MIXTA al campo instrumental
ALTER TABLE composiciones
MODIFY COLUMN instrumental ENUM('Si', 'No', 'Mixta');

-- añadir restricción al campo importe_pago
ALTER TABLE interpreta 
ADD CONSTRAINT chk_importe_pago
CHECK (importe_pago BETWEEN 100.50 AND 999.99);

-- Modificar la clave foránea para borrar en cascada
ALTER TABLE movimientos
DROP FOREIGN KEY fk_composiciones,
ADD CONSTRAINT fk_composiciones
FOREIGN KEY (composicion_id)
REFERENCES composiciones(idComposicion)
ON DELETE cascade;

-- Desactivar restricciones CHECK 
SET foreign_key_checks = 0;








