# Herramientas y dependencias del `pom.xml`

## Base de datos
- Este proyecto contiene aprox. **42k registros de productos** aleatorios y **50k de registro de ordenes**, que se crean al momento de iniciar el proyecto por primera vez con flyway.

## Ejecutar

Para ejecutar el proyecto con:

```shell
#> ./mvnw spring-boot:run -Dspring-boot.run.profiles=local
```

Se puede luego visualizar el swagger en la direccion

[Link]http://localhost:9090/swagger-ui/index.html

## Funcionamiento

# Batch

El proceso de batch se ejecuta con cron __@Scheduled(cron = "0/20 * * * * *")__ cada 20 segundos se puede ver que se escogen los primeros 10 registros
en status "PENDING" y se pasan a status "COMPLETE"

Se puede chequear el cambio haciendo una query y viendo como van reduciendo

''select count(*) from purchase_order po where po.status = 'PENDING';''

# Event

En la consola se puede chequear como se recibe un evento del tipo __@Scheduled(cron = "0/20 * * * * *")__ cada 20 segundos:

```
Receiving event topic:{"purchaseOrderId": "kafka_order_14518", "customerId": "kafka_customer_21077",.................
```

O hacer querys a purchaseOrderId empezando con 'kafka'

''select * from purchase_order po where po.purchase_order_id like 'kafka%';''

# Sonar

Este proyecto tiene un quality gate de A

![plot](sonar.png)

# Docker

En la carpeta scripts, se encuentran los scripts para crear docker, pararlo o ejecutarlo.

```
#scripts> ./createDocker.sh
#scripts> ./startDocker.sh

```

Nota: previo a esto, los contenedores del compose.yml, ya deben existir con docker-compose up -d
El perfil usado dentro del docker es 'docker'

## Herramientas y dependencias clave

### 1. **Spring Boot**
Varias dependencias (`spring-boot-starter`) indican que este proyecto utiliza **Spring Boot** para construir una aplicación web y empresarial. Las principales son:
- `spring-boot-starter-web`: Para aplicaciones web RESTful.
- `spring-boot-starter-logging`: Para manejo de logs.
- `spring-boot-starter-data-jpa`: Para la integración con JPA y bases de datos relacionales.
- `spring-boot-starter-batch`: Para procesamiento de lotes (batch).
- `spring-boot-actuator`: Para monitoreo y métricas de la aplicación.
- `spring-boot-docker-compose`: Para la integración con Docker.

### 2. **Apache Kafka**
La presencia de `spring-kafka`, `kafka-avro-serializer` y `avro` indica que el proyecto está utilizando **Apache Kafka** para la mensajería, junto con **Avro** para la serialización de datos.

### 3. **Flyway**
- `flyway-core` y `flyway-database-postgresql`: Se utilizan para la migración y versionado de la base de datos, asegurando que los cambios se apliquen de manera ordenada.

### 4. **Hibernate & Caching**
- `hibernate-jcache` junto con `com.github.ben-manes.caffeine.jcache` configuran una estrategia de caching mediante **Hibernate** y **Caffeine** como proveedor de caché.

### 5. **MapStruct**
- `mapstruct` y `mapstruct-processor`: Se usan para generar automáticamente código de mapeo entre clases, simplificando la conversión de DTOs a entidades.

### 6. **Lombok**
- `lombok` y `lombok-mapstruct-binding`: **Lombok** se utiliza para reducir la escritura de código repetitivo y se integra con **MapStruct** para mejorar el proceso de mapeo.

### 7. **SpringDoc OpenAPI**
- `springdoc-openapi-starter-webmvc-ui`: Permite la generación automática de documentación de API siguiendo el estándar **OpenAPI/Swagger**.

### 8. **Zalando Problem**
- `problem-spring-web` y `jackson-datatype-problem`: Facilitan el manejo de errores y excepciones de manera estructurada siguiendo el formato **RFC 7807 Problem Details**.

### 9. **ArchUnit**
- `archunit-junit5`: Se utiliza para realizar pruebas arquitectónicas y asegurar que se sigan reglas arquitectónicas dentro del código.

### 10. **Apache Avro**
- `avro-maven-plugin`: Plugin que permite la generación de clases Java a partir de esquemas **Avro**, usado en la integración con **Kafka**.

### 11. **Spotless**
- `spotless-maven-plugin`: Se utiliza para aplicar estilos de código y formatear el código automáticamente basado en reglas como **Google Java Format**.

### 12. **PostgreSQL**
- `postgresql`: Driver de base de datos que permite interactuar con una base de datos **PostgreSQL**.

### 13. **OpenAPI Generator**
- `openapi-generator-maven-plugin`: Genera clases de cliente y servidor a partir de un archivo OpenAPI, integrando el esquema de API con el proyecto.

### 14. **Apache Commons**
- `commons-text`: Utilizado para operaciones de manipulación de texto avanzadas.

### 15. **JUnit y Testing**
- `spring-boot-starter-test`, `spring-batch-test`: Dependencias para pruebas unitarias e integrales utilizando **JUnit**.
- `datafaker`: Generador de datos falsos para pruebas.

### 16. **Test containers**
- `com.playtika.testcontainers`: Para realizar pruebas de integracion con los componentes reales, esto permite unas pruebas más ajustadas al mundo real.

## Repositorios

- **Confluent Repository**: Es un repositorio necesario para las dependencias relacionadas con **Kafka** y el **serializador Avro**.

## Funcionamiento

## Conclusión

Este proyecto está configurado para ser una aplicación basada en **Spring Boot**, utilizando tecnologías avanzadas para procesamiento de datos, mensajería, mapeo de objetos, caché y generación automática de código. Se integra con Kafka, bases de datos relacionales (**PostgreSQL**) y está preparado para manejar migraciones de base de datos, pruebas automáticas y generar documentación API usando **OpenAPI**. Además, usa herramientas como **Lombok** y **Spotless** para mejorar la calidad y mantenimiento del código.
