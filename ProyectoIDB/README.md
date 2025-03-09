# ProyectoIDB - CRUD de Gestión de Datos

Este proyecto es una aplicación CRUD (Crear, Leer, Actualizar y Eliminar) desarrollada para la gestión de datos. 
Utiliza una arquitectura híbrida con un backend en Java (Spring Boot), un frontend con HTML, CSS y JavaScript, y una capa paralela en PHP para ciertas funcionalidades. 
Permite realizar las operaciones básicas sobre una base de datos MySQL.

## Funcionalidades Principales

*   **Crear:** Permite añadir nuevos registros a la base de datos a través de un formulario web.
*   **Leer:** Muestra los registros existentes en la base de datos en una tabla o lista.
*   **Actualizar:** Permite modificar los datos de los registros existentes.
*   **Eliminar:** Permite borrar registros de la base de datos.
*   **Interfaz de Usuario:** Diseño intuitivo y fácil de usar para interactuar con los datos.

## Tecnologías Utilizadas

*   **Frontend:**
    *   HTML: Estructura y contenido de las páginas web.
    *   CSS: Estilos visuales y presentación de la interfaz.
    *   JavaScript: Interactividad y manipulación del DOM en el navegador.
*   **Backend:**
    *   Java con Spring Boot: Lógica del servidor, API REST, gestión de la persistencia de datos.
*   **Capa Paralela:**
    *   PHP: Lógica específica para ciertas funcionalidades (explicadas más adelante).
*   **Base de Datos:**
    *   MySQL: Sistema de gestión de bases de datos relacional.

## Estructura del Proyecto

El proyecto se organiza de la siguiente manera:

*   `index.html`: Página principal con la interfaz del CRUD.
*   `css/`: Carpeta que contiene los archivos CSS para el estilo de la aplicación.
*   `js/`: Carpeta que contiene los archivos JavaScript para la interactividad.
*   `php/`: Carpeta que contiene los archivos PHP para funcionalidades específicas.
*   `src/main/java/`: Carpeta que contiene el código fuente de la aplicación Spring Boot. (Estructura típica de un proyecto Spring Boot).
*   `conexion.php`: Archivo que establece la conexión a la base de datos MySQL (utilizado por la capa PHP).
*   `[otros archivos PHP]`: Archivos PHP que implementan funciones específicas.
*   `[archivos Java]`: Controladores, servicios, modelos y repositorios de Spring Boot.

## Spring Boot - Backend en Java

El backend está construido con Java y el framework Spring Boot. Spring Boot proporciona las siguientes funcionalidades:

*   **API REST:** Expone endpoints REST para que el frontend (y la capa PHP) puedan interactuar con la base de datos.
*   **Gestión de la Persistencia:** Utiliza Spring Data JPA para facilitar la interacción con la base de datos MySQL.
*   **Controladores:** Manejan las peticiones HTTP y dirigen el flujo de la aplicación.
*   **Servicios:** Contienen la lógica de negocio de la aplicación.
*   **Modelos (Entidades):** Representan las tablas de la base de datos como objetos Java.
*   **Repositorios:** Interfaces que permiten acceder a los datos de la base de datos de forma sencilla.

**Consultas Típicas de Spring Boot:**

Spring Boot, a través de Spring Data JPA, utiliza consultas basadas en el nombre de los métodos (Query Methods) o JPQL (Java Persistence Query Language) para interactuar con la base de datos. Algunos ejemplos:

*   `findAll()`: Obtiene todos los registros de una tabla.
*   `findById(id)`: Obtiene un registro por su ID.
*   `save(entity)`: Guarda un nuevo registro o actualiza uno existente.
*   `deleteById(id)`: Elimina un registro por su ID.
*   Consultas personalizadas con JPQL (por ejemplo, para buscar registros que cumplan ciertos criterios).

## Base de Datos

Para poder ejecutar el proyecto localmente, es necesario importar la base de datos MySQL. Sigue estos pasos:

1.  Asegúrate de tener instalado un servidor MySQL (por ejemplo, XAMPP, WAMP o MAMP).
2.  Crea una base de datos vacía con el nombre que desees.
3.  Importa el archivo `[nombre_del_archivo].sql` (que deberías haber extraído de `mysql.rar`) a la base de datos que creaste. Puedes usar phpMyAdmin o la línea de comandos para importar el archivo.
4.  Modifica el archivo `conexion.php` para que coincida con los datos de tu servidor MySQL (nombre del servidor, usuario, contraseña y nombre de la base de datos).  Este archivo **solo** es utilizado por la capa PHP.
5.  Configura la conexión a la base de datos en el archivo `application.properties` o `application.yml` de tu proyecto Spring Boot.

## Instalación y Uso

1.  Clona este repositorio en tu máquina local.
2.  **Backend (Spring Boot):**
    *   Abre el proyecto en tu IDE (IntelliJ IDEA, Eclipse, etc.).
    *   Configura la conexión a la base de datos en `application.properties` o `application.yml`.
    *   Ejecuta la aplicación Spring Boot.
3.  **Frontend (HTML, CSS, JavaScript):**
    *   Copia los archivos del frontend a la carpeta `htdocs` de tu servidor web (si estás usando XAMPP, WAMP o MAMP).
    *   Abre tu navegador web y navega a la dirección `http://localhost/[nombre_de_la_carpeta_del_proyecto]/index.html`.
4.  **Capa PHP:**
    *   Asegúrate de que el servidor web esté configurado para ejecutar archivos PHP.
    *   Modifica el archivo `conexion.php` para que coincida con los datos de tu servidor MySQL.

## Capa PHP Funcional Paralela

La carpeta `php/` contiene código PHP que complementa la funcionalidad del backend en Spring Boot. Se ha creado como práctica para comprobar su funcionalidad.  Esta capa PHP podría ser utilizada para:

*   Procesar ciertas peticiones de forma más rápida o eficiente.
*   Implementar funcionalidades que no son críticas y no requieren la complejidad de Spring Boot.
*   Integrar con sistemas externos que requieren PHP.


## Contribución

Si deseas contribuir a este proyecto, siéntete libre de hacer un fork del repositorio y enviar tus pull requests.

