# reactive-webclient
Servicio encargado de consultar una entidad cliente consumiendo otro servicio rest de manera reactiva utilizando webClient

## Tecnologías

### Java 11
Versión de Java 11 para desarrollo de aplicaciones en lenguaje de programación Java https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html

### Springboot 2.0
Componente que permite crear aplicaciones autocontenidas que permiten ejecutarse en su mismo proceso https://spring.io/projects/spring-boot

### Webflux con endpoints funcionales:
Framework de spring que implementa el modelo reactivo. La versión de entpoints funcionales permite la implementación del crontrolador en el modelo funcional apalancado en routes y handles https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html

### Gradle
Gradle ayuda a los equipos a construir, automatizar, testear y entregar mejor software y de manera rápida. https://gradle.org/

### Lombok
Libreria que permite automatizar mediante anotaciones la escritura de getters, setters y constructores en tiempo de compilación https://projectlombok.org/

### Sandbox
Herramienta para mockear servicios rest. https://getsandbox.com/

## Requisitos
1. Tener un IDE instalado (eclipse, intellij, netbeans, etc)
2. Abrir una cuenta en Sandbox
3. Mockear servicios de entidad cliente

## Ejercicio
Se requiere un servicio que consulte y almacene una entidad cliente llamando un servicio rest.

## Solución
Se crea un servicio rest reactivo con endpoints funcionales que consulta y almacena la entidad cliente llamando un servicio rest simulado con getsandbox usando el cliente web reactivo webClient

## Estructura de la solución

Paquete: co.com.gcode.reactivewebclient

### Clases
Cliente: Clase entidad que representa un cliente de una institución. Esta anotada con lombok para su simplicidad. Sus 

atributos:
- documentId
- documentType
- name
- mdmKey

Application: Clase principal del microservicio que se encarga de lanzar springboot

RestClientConfig: Clase que tiene el bean de configuración del webclient donde se configuran las propiedades y cabeceras del endpoint a consultar

Route: Clase encargada de recibir las peticiones rest y redireccionarlas al correspondiente handler que resolverá la peticion. Equivale al controlador en el modelo reactivo orientado a anotaciones

Handler: Clase encargada de procesar la operación de consulta y guardado en redis.

## Endpoints del servicio

### Consulta entidad cliente
http://localhost:8081/client/{documentId}

body: none

### Guardar entidad cliente
http://localhost:8081/client

body:
```json
{
    "documentId" : "1053796889",
    "documentType" : "CC",
    "name" : "Alejo Botero",
    "mdmKey": "9999999999999999"
}
```
### Servicio rest consumido
https://dry-butterfly-4551.getsandbox.com:443/client/{documentId}

https://dry-butterfly-4551.getsandbox.com:443/client
