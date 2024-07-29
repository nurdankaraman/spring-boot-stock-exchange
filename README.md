# StockExchange Application Explanation
StockExchange is a simple stock exchange application. 
There are api(s) that allows users to create, update, delete, and view stock or stockExchange information. 
The application is built using Spring Boot and H2 in-memory database.

# Testing the application
Added only one test data for creating a stockExchange.For further testing, you can add more data by postman easily.
```\src\main\resources\postmanCollection``` folder contains the postman collection file for API.

# Running the application
**Prerequisites**
Java Development Kit (JDK): should be installed.
Maven: Apache Maven should be installed on your machine.

To run the application, you can use the following command:
```mvn clean install``` 
```mvn spring-boot:run```

Running tests in a Maven project is straightforward:
```mvn test```

Ports: Spring Boot (default is 8080) should not in use by another application.
could be changed in application.properties file.
server
    port: 8080 # change port number here.
