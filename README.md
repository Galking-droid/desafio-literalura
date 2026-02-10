# LiterAlura - Challenge Oracle Next Education (ONE) 📚

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)

**LiterAlura** es un catálogo de libros interactivo desarrollado en Java con el framework Spring Boot. Este proyecto permite buscar libros a través de la API externa **Gutendex**, almacenar la información en una base de datos relacional (PostgreSQL) y gestionar los datos mediante una interfaz de consola dinámica.

## 🚀 Funcionalidades

El sistema cumple con los siguientes requerimientos técnicos:
1.  **Búsqueda por título:** Consulta la API Gutendex, registra el libro y su autor en la base de datos (evitando duplicados).
2.  **Listar libros:** Muestra todos los libros almacenados localmente.
3.  **Listar autores:** Muestra todos los autores registrados en el sistema.
4.  **Autores vivos en determinado año:** Filtra autores que se encontraban con vida en el año ingresado por el usuario.
5.  **Libros por idioma:** Filtra y contabiliza la cantidad de libros disponibles en un idioma específico (ES, EN, FR, PT).

## 🛠️ Tecnologías Utilizadas

- **Java 17:** Uso de Records y Programación Orientada a Objetos.
- **Spring Boot 4.0.2:** Framework principal para la lógica y gestión de dependencias.
- **Spring Data JPA:** Para el mapeo objeto-relacional (ORM) y persistencia de datos.
- **PostgreSQL:** Base de datos relacional para el almacenamiento de libros y autores.
- **Jackson:** Para el parseo de datos JSON provenientes de la API.
- **Lombok:** Para mantener un código limpio mediante anotaciones.

## ⚙️ Configuración del Entorno

Para ejecutar este proyecto, necesitarás configurar las siguientes variables de entorno en tu sistema o en el archivo `application.properties`:

- `DB_HOST`: Dirección de tu servidor de base de datos.
- `DB_USER`: Tu usuario de PostgreSQL.
- `DB_PASSWORD`: Tu contraseña de PostgreSQL.



## 📌 Cómo ejecutar el proyecto

1. Clona el repositorio:
   ```bash
   git clone https://github.com/Galking-droid/desafio-literalura.git
2. Crea una base de datos llamada literalura_db en PostgreSQL.

3. Importa el proyecto en tu IDE favorito (IntelliJ IDEA recomendado).

4. Configura las variables de entorno mencionadas arriba.

5. Ejecuta la clase DesafioLiteraluraApplication.

Desarrollado por Joseph Gama - 2026.