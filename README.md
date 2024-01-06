![Project Logo](https://www.genesys.com/media/meta-logo-Etraveli.png)

# Etraveli Refactoring Java Project

This project focuses on generating rental records for customers. The goal is to calculate the amount owed by customers
and the earned frequent points based on their rental activities.

## Table of Contents

- [Introduction](#introduction)
- [Purpose](#Purpose)
- [Features of this project](#features-of-this-project)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Technologies Used](#technologies-used)
    - [Installation](#installation)
- [Endpoints](#endpoints)

## Introduction

Refactoring Java Project facilitates the management of customers, movies, and rental records, providing a comprehensive
solution for tracking and analyzing rental activities. It emphasizes flexibility by offering endpoints for creating,
updating, and retrieving information related to customers, movies, and rentals. The application is designed to be a
central hub for efficiently managing rental records and providing valuable insights into customer activities.

## Purpose

The primary purpose of this project is to:

- Aggregate and present statistical data for a Movie rentals.
- Create, update, and find customer information, including details such as name, contact information, etc.
- Create, update, and find movie information, encompassing attributes like title, genre, release date, etc.
- Create and retrieve rental records, associating movies with customers and capturing rental duration and related
  details.
- Implement logic to calculate the amount owed by customers based on their rental activities.

    - Consider different pricing models for various types of movies (e.g., regular, new release, children's).
    - Factor in rental duration and any applicable charges.
    - Award bonus points for specific conditions, such as renting new releases for more than two days.

## Features of this project

1. **Scalability and Flexibility:**
    - The chosen solution is designed with scalability and flexibility in mind, accommodating potential future
      enhancements and the inclusion of additional features.


2. **Swagger API Documentation:**
    - The inclusion of Swagger API documentation enhances the developer experience, providing clear and interactive
      documentation for API endpoints.


3. **Custom Exception Handling:**
    - Implement custom exceptions to capture and handle application-specific errors and unexpected conditions.


4. **Gradle Build System:**
    - The use of the Gradle build system simplifies project management, dependency resolution, and builds, contributing
      to a more efficient development process.


5. **Postgres Integration:**
    - Leveraging PostgreSQL for database integration provides a robust and scalable solution tailored for storing
      Customer, Movie, and Movie rental data. PostgreSQL, a feature-rich relational database management system, ensures
      reliability and flexibility to support current and future data management needs.


6. **Open Source Libraries:**
    - Leveraging open-source libraries, such as Spring Boot and Springdoc, ensures that the project benefits from a
      community-driven ecosystem, regular updates, and proven solutions.


7. **Clear Project Structure:**
    - The project adheres to a clear and organized structure, facilitating collaboration, maintenance, and future
      development.

## Getting Started

To set up and run the refactoring-java project locally on your machine, follow these steps:

### Prerequisites

Make sure you have the following installed on your machine:

- Java Development Kit (JDK) 17 or later
- Gradle
- Postgresql

### Technologies Used

The Refactoring Java project is built using the following technologies:

- Java: Version 17
- Spring Boot: Version 3.2.0
- JUnit
- Swagger: API documentation tool
- Postgresql: Relational SQL database
- Gradle: Build and dependency management tool
- Postman
- Intellij Idea
- slf4j Logger

### Installation

1. **Clone the Repository:**

   ```bash
   https://github.com/etraveli/refactoring-java/pull/71

- Open application.yml file and change the Database information.


- Navigate to the Project Directory using CMD or Terminal:
    - cd refactoring-java


- Build the Project:
    - gradle build


- Run the jar file:
    - java -jar build/libs/refactoring-java-0.0.1-SNAPSHOT.jar

2. **Access to the Swagger API Documentation.**

      ```bash
      http://localhost:8081/swagger-ui/index.html

## Endpoints

### Customer Endpoints

**Create Customer**

- **Endpoint:** `/api/v1/customer`
- **Method:** POST
- **Description:** Create Customer.
- **Example:** `{
  "name": "string",
  "birthYear": 0,
  "idNumber": "string"
  }`

*Create Customer output*

![Refactoring-Java](/src/main/resources/static/CreateCustomer.png)

**Update Customer**

- **Endpoint:** `/api/v1/customer`
- **Method:** PUT
- **Description:** Update Customer.
- **Example:** `{  "customerId": 2,
  "name": "Sahan Ekanayake UPDATE",
  "birthYear": 1990,
  "idNumber": "19890102-1234 UPDATE"
  }`

*Update Customer output*

![Refactoring-Java](/src/main/resources/static/UpdateCustomer.png)

**Get Customer by ID**

- **Endpoint:** `/api/v1/customer/{customerId}`
- **Method:** GET
- **Description:** Get customer details by ID.

*Get Customer By ID output*

![Refactoring-Java](/src/main/resources/static/GetCutomerById.png)

**Get All Customers**

- **Endpoint:** `/api/v1/customers`
- **Method:** GET
- **Description:** Get All customer details.

*Get All Customers output*

![Refactoring-Java](/src/main/resources/static/GetAllCustomers.png)

### Movie Endpoints

**Create Movie**

- **Endpoint:** `/api/v1/movie`
- **Method:** POST
- **Description:** Create Movie.
- **Example:** `{
  "movieCode": "F005",
  "title": "Tangled",
  "code": "CHILDRENS"
  }`

*Create Movie output*

![Refactoring-Java](/src/main/resources/static/CreateMovie.png)

**Update Movie**

- **Endpoint:** `/api/v1/mmovie`
- **Method:** PUT
- **Description:** Update Movie.
- **Example:** `{
  "movieId": 5,
  "movieCode": "F005",
  "title": "Chicken Run Dawn of the Nugget",
  "code": "NEW"
  }`

*Update Movie output*

- MovieCode can't update
  ![Refactoring-Java](/src/main/resources/static/UpdateMovie.png)

**Get Movie by Code**

- **Endpoint:** `/api/v1/movie/{movieCode}`
- **Method:** GET
- **Description:** Get Movie details by ID.

*Get Movie By ID output*

![Refactoring-Java](/src/main/resources/static/GetCutomerById.png)

**Get All Movies**

- **Endpoint:** `/api/v1/movies`
- **Method:** GET
- **Description:** Get All Movies details.

*Get All Movies output*

![Refactoring-Java](/src/main/resources/static/GetAllMovies.png)

### Movie Rental Endpoints

**Create movie rental**

- **Endpoint:** `/api/v1/movie-rental`
- **Method:** POST
- **Description:** Create Movie rental.
- **Example:** `{"customerId": 2,
  "movieCode": "F005",
  "days": 3
  }`

*Create Movie rental output*

![Refactoring-Java](/src/main/resources/static/MovieRentalForCustomer.png)

**Get movie rental information by customer ID**

- **Endpoint:** `/api/v1/movie-rental/customer/{customerId}`
- **Method:** GET
- **Description:** Get movie rental information by customer ID.

*Get movie rental information by customer ID output*

![Refactoring-Java](/src/main/resources/static/MovieRentalStatementForCustomer.png)

### <font color="#ff3333"> Expected output</font>

![Refactoring-Java](/src/main/resources/static/ExpectedOutPut.png)
