# Resumen del Repositorio

Este repositorio contiene una colección de ejercicios y prácticas realizados durante un período de prácticas en una empresa. El contenido abarca diferentes aspectos del desarrollo web y la gestión de bases de datos.

## Tipos de Ejercicios y Prácticas

### Desarrollo Front-End Básico

*   **Creación de interfaces de usuario:** Se incluyen ejemplos de creación de formularios web sencillos (login, contacto) utilizando HTML.
*   **Integración de contenido multimedia:** Hay ejemplos de cómo incluir contenido multimedia (en este caso, una canción) en una página web, ofreciendo también la posibilidad de descargar el archivo.

### Desarrollo Back-End con PHP

*   **Procesamiento de formularios:** Hay scripts PHP que probablemente gestionan el envío y procesamiento de datos desde formularios HTML (login y contacto).
*   **Gestión de descargas de archivos:**  Un script PHP se encarga de facilitar la descarga de un archivo multimedia.

### Desarrollo Back-End con Java (Spring Boot)

*   **Creación de una API REST:**  Se ha implementado una API REST utilizando Spring Boot para gestionar entidades relacionadas con información musical (músicos, composiciones, movimientos).
*   **Operaciones CRUD (Crear, Leer, Actualizar, Eliminar):** La API REST permite realizar operaciones CRUD sobre las entidades mencionadas, proporcionando endpoints para cada operación.
*   **Acceso y manipulación de datos:** La API utiliza repositorios para acceder a una base de datos y realizar consultas y modificaciones.
*   **Serialización y deserialización de datos:** Se utiliza `ObjectMapper` para convertir objetos Java a formato JSON y viceversa, facilitando la comunicación entre la API y el front-end.
*   **Controladores REST:** Los controladores manejan las peticiones HTTP y delegan la lógica de negocio a los servicios.
*   **Servicios:** Los servicios implementan la lógica de negocio y acceden a los repositorios para interactuar con la base de datos.
*   **Manejo de errores:** Se implementa manejo de errores básico para responder con códigos de estado HTTP adecuados en caso de fallos.

### Diseño y Gestión de Bases de Datos

*   **Creación de esquemas de bases de datos:** Se incluyen scripts SQL para crear bases de datos con múltiples tablas, definiendo relaciones entre ellas y claves primarias/foráneas.
*   **Modificación de esquemas de bases de datos:** Hay scripts SQL que demuestran cómo modificar la estructura de una base de datos existente, incluyendo:
    *   Añadir y eliminar columnas.
    *   Renombrar columnas.
    *   Añadir índices para mejorar el rendimiento de las consultas.
    *   Modificar tipos de datos de columnas.
    *   Añadir restricciones (constraints) para asegurar la integridad de los datos.
*   **Inserción de datos:** Se incluyen scripts SQL con sentencias `INSERT` para poblar las tablas de las bases de datos con datos de ejemplo.
*   **Modificación de claves foráneas:** Se incluyen scripts SQL para modificar las claves foráneas, como por ejemplo la opción de borrado en cascada.

## Enfoque General

Este repositorio refleja una experiencia práctica en la que se han abordado diferentes tecnologías y conceptos relacionados con el desarrollo web y la gestión de bases de datos.  Los ejercicios abarcan desde la creación de interfaces de usuario básicas hasta la implementación de APIs REST y el diseño de esquemas de bases de datos.
