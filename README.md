# Movie-Rental-Service
~ SpringBoot rest webservice for movie rental ~


### Refactored the application (Movie Rental Service) - Changes
#### Here, I am more focused on removing hardcoded values, such as fees, and creating common methods for tasks like calculating rental amounts. With the current implementation, we can easily add new movie types and fees without requiring any modifications to the core logic of the application.
- Converted the code into SpringBoot, Maven based rest application
- Refactored package structure
- Handled errors and exceptions
- Added basic validations
- Added unit tests
- Added Swagger
- Used SonarLint to improve code quality
- Used Slf4j

### Used Technologies
- Java 17
- SpringBoot 3
- Maven
- Junit 5 and Mockito 3
- Swagger
- Postman
- SonarLint

### To run service
- mvn clean, build, install
- java -jar movie-rental-service-0.0.1-SNAPSHOT.jar

### Swagger url
- http://localhost:8081/movie-rental-service/swagger-ui/index.html#/

### Sample request

    curl --location 'http://localhost:8081/movie-rental-service/api/rental' \
    --header 'Content-Type: application/json' \
    --data '{
        "customerId": "1",
        "rentalLines": [
            {
                "movieId": "F001",
                "noOfDays": 2
            },
            {
                "movieId": "F002",
                "noOfDays": 1
            }
            ,
            {
                "movieId": "F003",
                "noOfDays": 1
            }
            ,
            {
                "movieId": "F004",
                "noOfDays": 3
            }
        
        ]
    }'

### Sample response

    {
        "customer": {
            "customerId": "1",
            "name": "C. U. Stomer"
        },
        "movieRentalResponseLines": [
            {
                "movieName": "You've Got Mail",
                "rental": 2,
                "frequentEnterPoints": 1
            },
            {
                "movieName": "Matrix",
                "rental": 2,
                "frequentEnterPoints": 1
            },
            {
                "movieName": "Cars",
                "rental": 1.5,
                "frequentEnterPoints": 1
            },
            {
                "movieName": "Fast & Furious X",
                "rental": 9,
                "frequentEnterPoints": 2
            }
        ],
        "totalRental": 14.5,
        "frequentEnterPoints": 5
    }

### Enhancement
- Can use database to store data
- Can add more endpoints, such as basic crud for movie and customer, update the movie code(New,Regular..)
- Can use more complex dto and data validations
- Can add more test cases
- Can implement JWT implementation to improve security
- Can add centralized logs using SpringBoot AOP

