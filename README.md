# Karting Winner Application

This Spring Boot application is designed to determine the winner of a karting race based on lap times. By analyzing the fastest lap, the application provides details of the winning kart. This project makes use of various Spring Boot starters and dependencies to facilitate its functionality.

## Dependencies:

- **spring-boot-starter-data-jpa**: Provides features to easily persist data using JPA and Hibernate.
  
- **spring-boot-starter-web**: Spring Boot's starter for building web applications, including RESTful APIs.
  
- **commons-csv**: Apache Commons library for reading and writing CSV files.

- **h2**: A fast in-memory database. Useful for development and testing purposes.

- **spring-boot-starter-test**: Spring Boot's starter for testing, which provides classes to test the Spring components with JUnit.

- **lombok**: A library that helps reduce boilerplate code in Java, providing annotations for common tasks like getters, setters, and constructors.

- **junit-jupiter-api & junit-jupiter-engine**: JUnit 5 (Jupiter) provides a foundation for developer-side testing on the JVM.

- **mockito-junit-jupiter**: Mockito integration with JUnit 5 (Jupiter).

- **hamcrest-core & hamcrest-library**: Libraries providing matchers for testing in JUnit.

## Project Overview:

The application primarily operates by reading CSV data files to fetch the lap times of different kart racers. It then computes the fastest lap and identifies the winning kart based on this. The result, which includes details like kart number, lap start time, lap number, and the fastest lap time, is presented in a tabular form via a web interface.

## Setup and running:

1. **Navigate to Project Directory**: 

        cd karting

2. **Build the Project**:


        mvn clean install

3. **Run the Application**:

        mvn spring-boot:run


Upon successful execution, you can access the application at `http://localhost:8080/`.

    Endpoints:
        To start the race you should use:   /start-from-file 
        To get the winner you should use:   /winner

- **An example of the .csv file to be used is already in place under the resources folder ( src/main/resources/karttimes.csv ).**
- **You should replace that one with other with the same name, if you want to use other files.**

## Unit tests:

Each file has a *Test.java file to cover the basic features. 
These can be found under the test folder (src/test/...).

To run the unit tests run:

    mvn test

---
### Note:
Make sure you have the necessary environment setup (Java 17 and Maven) for the smooth execution of the application.


---
By following the above steps, you should have the Karting Winner Application up and running. You can then interact with the provided endpoints to retrieve the winner details from the karting race.