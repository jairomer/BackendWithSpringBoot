# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.3.0-SNAPSHOT/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.3.0-SNAPSHOT/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.3.0-SNAPSHOT/reference/htmlsingle/index.html#web)
* [Rest Repositories](https://docs.spring.io/spring-boot/docs/3.3.0-SNAPSHOT/reference/htmlsingle/index.html#howto.data-access.exposing-spring-data-repositories-as-rest)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.3.0-SNAPSHOT/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing JPA Data with REST](https://spring.io/guides/gs/accessing-data-rest/)
* [Accessing Neo4j Data with REST](https://spring.io/guides/gs/accessing-neo4j-data-rest/)
* [Accessing MongoDB Data with REST](https://spring.io/guides/gs/accessing-mongodb-data-rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

## Service Lifecycle

### Use Case

Checkout the [DESIGN.md](./docs/DESIGN.md) document to know what this is doing.

There is also an [OpenAPI description](./openapi/openapi.yaml) for this service.

### Development

**Compile**
```bash
mvn compile
```

**Launch unit tests and application tests**
```bash
mvn test
```

**Launch unit tests and run**
```bash
mvn clean spring-boot:test-run
```

**Run**
```bash
mvn clean spring-boot:run
```

**Measure code coverage**
Code coverage reports created with jacoco need to be visualized via third party tools such as SonarQube.
```bash
mvn jacoco:report
```

### DevSec with SonarQube

To execute this command you will need a running instance of sonarqube on your development machine.

```bash
 mvn clean verify sonar:sonar \                                         
  -Dsonar.projectKey=BackendWithSpringBoot \
  -Dsonar.projectName='BackendWithSpringBoot' \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.token=<your project token>
```

### Deploy

TODO

### Monitoring

TODO
