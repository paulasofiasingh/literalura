# Literalura: Aplicación de Consola para Gestión de Libros y Autores

## Descripción
**Literalura** es una aplicación de consola desarrollada en Java que permite gestionar un catálogo de libros y autores. Puedes buscar libros en una API externa, registrarlos en una base de datos y consultar información como libros registrados, autores vivos en un año específico y más.

## Requisitos
- **Java** 11 o superior
- **PostgreSQL** instalado y configurado
- Dependencias gestionadas por **Spring Boot** y **Maven**

## Instalación
1. Clona este repositorio en tu máquina local:
   
   git clone https://github.com/paulasofiasingh/literalura
   
2. Accede al directorio del proyecto:
   
cd literalura

3. Configura la base de datos PostgreSQL:
   
Crea una base de datos llamada literalura:

Código SQL

CREATE DATABASE literalura;

4. Configura las credenciales de acceso en el archivo application.properties:

spring.datasource.url=jdbc:postgresql://localhost/literalura

spring.datasource.username=tu_usuario

spring.datasource.password=tu_contraseña

5. Ejecuta el siguiente comando para compilar y ejecutar la aplicación:

mvn spring-boot:run

## Uso
Cuando inicias la aplicación, se mostrará el siguiente menú:

<img width="278" alt="image" src="https://github.com/user-attachments/assets/cc4c5ea9-76db-4a8a-ba20-4c3e5f59735b">

Selecciona una de las opciones ingresando el número correspondiente.

## Opciones del menú
Buscar libro por título:
Escribe el título del libro que deseas buscar. Si el libro está disponible, se registrará en la base de datos y se mostrará la información.

Listar libros registrados:
Muestra todos los libros registrados en la base de datos.

Listar autores registrados:
Muestra todos los autores registrados, junto con sus libros asociados.

Listar vivos en un determinado año:
Ingresa un año, y la aplicación mostrará los autores que estaban vivos durante ese periodo.

Listar libros por idioma:
Ingresa el código de idioma (por ejemplo, en para inglés o es para español) para listar los libros registrados en ese idioma.

Salir:
Termina la ejecución de la aplicación.

## Ejemplo de uso
Inicia la aplicación y selecciona la opción 1.
Ingresa el título de un libro, por ejemplo: Moby Dick.
La aplicación buscará y registrará el libro en la base de datos si está disponible.
Selecciona la opción 3 para listar los autores registrados y verificar si el autor del libro aparece en la base de datos.

## Contacto
Para preguntas o sugerencias, contactame:
Autor: Paula Singh
Email: paulasofiasingh@gmail.com
¡Gracias por usar Literalura! 📚
