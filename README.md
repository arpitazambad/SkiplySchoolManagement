# Skipsy Project

This project contains two microservices: **Student** and **Receipt**. Both microservices are part of the **Skipsy** system and are built using **Spring Boot**. 

## Folder Structure

Spring - Student fee management

This project includes implementation of creating students, collecting fee for specific students and viewing their receipts.

Requirements : Maven, jdk 23

Running any service : mvn spring-boot:run

Service Description :

student - creates students, collects fees and stores the receipt. Added appropriate validation wherever needed. Implemented using feign client, circuit breaker, global exceptions, integration test cases spring jpa and in memory H2 database

receipt - store and view receipt functionality implemented. Implemented using feign client, circuit breaker, global exceptions, spring jpa, integration test cases and in memory H2 database

Requests: Sample requests for each service available in postman collection file


Swagger :

STUDENT-MANAGEMENT-SERVICE - http://localhost:8081/swagger-ui.html

RECEIPT-SERVICE - http://localhost:8083/swagger-ui.html
