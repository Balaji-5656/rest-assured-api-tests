# REST Assured API Test Automation

API test automation framework using **Java, REST Assured, TestNG and Maven**, written against the public
[JSONPlaceholder](https://jsonplaceholder.typicode.com) REST API.

## What is tested
- GET single resource, GET list, GET with query parameters
- POST (create), PUT (update), DELETE
- Negative scenarios: non-existent resource and invalid endpoint (404)
- Assertions on status codes, JSON fields, and response size

## Tech stack
Java 11+, REST Assured 5.4.0, TestNG 7.9.0, Maven

## How to run
```
mvn clean test
```
Test reports are generated in `target/surefire-reports/`.

## Project structure
```
src/test/java/api/PostsApiTest.java   -> test cases
testng.xml                            -> test suite
pom.xml                               -> dependencies
```
