![banner Foro Hub Alura AAF](https://github.com/freedox10/challenge-forohub-api-rest/blob/main/img/foro-hub-alura2.webp?raw=true)
# Challenge ForoHub
 ## Alura - ONE - AAF
### Back End G6:  Java + SpringBoot - API Rest 

![Badge Release](https://img.shields.io/badge/Release%20Date:-Julio2024-blue)
![Badge Java](https://img.shields.io/badge/Java:-17-blue)
![Badge Java](https://img.shields.io/badge/Spring%20Boot:-3.3.1-blue)
![Badge Finalizado](https://img.shields.io/badge/Status:-Finalizado-blue)

### Proyecto
**Aplicación API Rest para el manejo de funcionalidades BackEnd de un Foro.**

_**Especificaciones:**_ https://trello.com/c/HGMm7NzW/6-sobre-el-desaf%C3%ADo
### Descripción
Un  **foro**  es un espacio donde todos los participantes de una plataforma pueden plantear sus preguntas sobre determinados  **tópicos**. Aquí en  **Alura Latam**, los estudiantes utilizan el foro para sacar sus dudas sobre los cursos y proyectos en los que participan. Este lugar mágico está lleno de mucho aprendizaje y colaboración entre estudiantes, profesores y moderadores.

Ya sabemos para qué sirve el foro y conocemos su aspecto, ¿pero sabemos cómo funciona detrás de escena? Es decir, ¿dónde se almacenan las informaciones? ¿Cómo se tratan los datos para relacionar un  **tópico**  con una  **respuesta**, o cómo se relacionan los  **usuarios**  con las respuestas de un tópico?

Este es nuestro desafío, en él vamos a replicar este proceso a nivel de back end y, para eso,  **crearemos una API REST usando Spring**.

📋 Nuestra API se centrará específicamente en los  **tópicos**, y debe permitir a los usuarios:
-   Crear un nuevo tópico;
-   Mostrar todos los tópicos creados;
-   Mostrar un tópico específico;
-   Actualizar un tópico;
-   Eliminar un tópico.

Es lo que normalmente conocemos como  **CRUD***  (CREATE: crear, READ: consultar, UPDATE: actualizar, DELETE: eliminar).

### Objetivo 🚀
Crear una **API REST** con las siguientes funcionalidades:

1.  **API** con rutas implementadas siguiendo las mejores prácticas del modelo **REST**;
2.  Validaciones realizadas según las reglas de negocio;
3.  Implementación de una base de datos relacional para la persistencia de la información;
4.  Servicio de autenticación/autorización para restringir el acceso a la información.

### Tecnologias 🛠️
- **Java 17** para el desarrollo con ayuda del IDE de **Intellij**
- **Spring Boot** para la creación de la API Rest
- **MySQL** servidor de bases de datos
- **Insomnia** para las respectivas pruebas a la API. 

| Dependencias |  |
|--|--|
| Spring Data JPA: | Persistencia de datos en almacenes SQL con Java Persistence API utilizando Spring Data e Hibernate. |
| Flyway Migration: | Control de versiones de su base de datos para que pueda migrar desde cualquier versión (incluida una base de datos vacía) a la última versión del esquema. |
| Java-JWT-Auth0: | Le permite decodificar, verificar y generar JWT, JSON Web Tokens. |
| SpringDoc OpenAPI: | ayuda a automatizar la generación de documentación API utilizando proyectos de Springboot. |

### Funcionalidades :hammer:
Las mismas estan creadas alrededor de cinco grupos;
- `Usuario`: CRUD; crea, consulta, actualiza, elimina.
- `Curso`: CRUD; crea, consulta, actualiza, elimina.
- `Tópico`: CRUD; crea, consulta, actualiza, elimina + consulta extendida.
- `Respuestas`: CRUD; crea, consulta, actualiza, elimina + consulta extendida.
- `Autenticación`: proceso de autenticación de usuario.

![Imagen del menú de Códigos](https://github.com/freedox10/challenge-forohub-api-rest/blob/main/img/Doc-ForoHubAlura_01.webp?raw=true)

#### Detalles:

![Imagen del menú de Códigos](https://github.com/freedox10/challenge-forohub-api-rest/blob/main/img/Doc-ForoHubAlura_03.webp?raw=true)
![Imagen del menú de Códigos](https://github.com/freedox10/challenge-forohub-api-rest/blob/main/img/Doc-ForoHubAlura_05.webp?raw=true)
#### Modo de Uso: Autenticación
Llamar a la función REST "/login" con los datos de la misma en el cuerpo en formato Json.
_Nota: para la clave se usa la encriptación Bcrypt la cual se almacena en la BD pero en el cuerpo del Json se le manda clave sin encriptar.
![Imagen del menú de Códigos](https://github.com/freedox10/challenge-forohub-api-rest/blob/main/img/Doc-ForoHubAlura_07.webp?raw=true)

Al ejecutar, si verifica la autenticación, devuelve un JWToken.
![Imagen del menú de Códigos](https://github.com/freedox10/challenge-forohub-api-rest/blob/main/img/Doc-ForoHubAlura_09.webp?raw=true)

Este JWToken se utiliza para autorizar todas las demás llamadas de la aplicación.
En este caso se ingresa en la interfaz de swagger en  Authorize.
![Imagen del menú de Códigos](https://github.com/freedox10/challenge-forohub-api-rest/blob/main/img/Doc-ForoHubAlura_11.webp?raw=true)

Si es correcto se muestra de esta forma y queda predeterminado para todas las demás llamadas.
![Imagen del menú de Códigos](https://github.com/freedox10/challenge-forohub-api-rest/blob/main/img/Doc-ForoHubAlura_13.webp?raw=true)

Ejemplo de llamada personalizada:
![Imagen del menú de Códigos](https://github.com/freedox10/challenge-forohub-api-rest/blob/main/img/Doc-ForoHubAlura_15.webp?raw=true)

devuelve lista de los tópicos que coincidan con el estado dado, en este ejemplo "SOLUCIONADO".
![Imagen del menú de Códigos](https://github.com/freedox10/challenge-forohub-api-rest/blob/main/img/Doc-ForoHubAlura_17.webp?raw=true)


### Recorrido Cronológico
>2024-06-22
>>_Iniciando creación de Foro Hub Alura Api RestLiteralura como desafío al trayecto de formación Backend de Oracle Next Education y Alura Cursos.
Analizo los requisititos del Challenge, busco las herramientas necesarias, implemento primeros pasos de mi aplicación java con Spring Initializr._
>
>2024-06-23
>>_Creación de la **Base de Datos** y sus distintas tablas con migrations de Flyway._
>
>>_Creación y puesta a punto de método de **Autenticación** para mejorar la seguridad de la aplicación._
>
>2024-06-24
>>_Creación modelo **Usuario**, funciones en services, controles (REST) como controllers, transferencias de datos como DTOs, repositorios como repository._
>
>>2024-06-25
>>_Creación modelo **Curso**, funciones en services, controles (REST) como controllers, transferencias de datos como DTOs, repositorios como repository._
>
>>_Creación modelo **Tópico**, funciones en services, controles (REST) como controllers, transferencias de datos como DTOs, repositorios como repository, validaciones según reglas de negocio en entradas de datos e integridad._
>
>>_Trabaja función listar Tópico, se agrega funcionalidad mostrar tópico mas respuestas._
>
>>2024-06-26
>>_Creación modelo **Respuesta**, funciones en services, controles (REST) como controllers, transferencias de datos como DTOs, repositorios como repository._
>
>>2024-06-27
>>_Mejora funciones Tópico,  agrega función listar tópicos por estado, función eliminar solo lo marca como Estado.CERRADO._
>
>>2024-06-28
>>_Mejora funciones Respuesta,  mejoras varias._
>
>>2024-06-29
>>_Agrega función Respuesta,  agrega función listar respuestas por solución._
>
>>2024-06-30
>>_Optimiza funciones, revisión y corrección integral de las mismas.
Crea **Documentación** con swagger implementando springdoc-openAPI.
Crea **Test** de comprobación del controlador de Tópico
**Versión Operable**._

### Consideraciones finales
Quiero agradecer a los profesores que dictaron estos cursos los cuales me resultaron muy enriquecedores en mi formación también agradecer a los encargados de Alura Cursos y de Oracle Next Education por posibilitarme el acceso a este fascinante y emocionante mundo de la programación java web, desde mi posición me siento muy muy satisfecho y me comprometo aún mas a seguir aprendiendo y aplicando estos saberes increíbles.
_Con calma pero con prisa_ espero haber cumplido los objetivos propuestos. Un gran saludo para todos.

## Autor AAF ✒️