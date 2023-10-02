# Movie-Rental-Service

~ SpringBoot and Rest services approach ~

### Refactor changes

- Orchestrated a SpringBoot Maven REST app with refined package structuring
- Nurtured robustness with thorough error handling and basic data validations
- Enhanced logging proficiency with Slf4j for seamless debugging
- Followed SOLID principles to achieve better modularity, flexibility and maintainability

### Technologies

- Java 17
- SpringBoot 3
- Maven
- In Memory H2 database
- SLF4J
- Postman

### To run the application

- mvn clean install (through IDE Maven window or command terminal)
- Run main() in MovieRentalServiceApplication.java class

### H2 Console URL

- http://localhost:8080/h2-console

### H2 Console Configuration

- Saved Settings: Generic H2 (Embedded)
- Setting Name: Generic H2 (Embedded)
- Driver Class: org.h2.Driver
- JDBC URL: jdbc:h2:mem:testdb
- User Name: etraveli
- Password: password

### Insert Queries

- Provided in data.sql under folder resources -> db

### Postman Request URL

~ Post Request ~

- http://localhost:8080/rental-service/rental/details

### Sample Request

    --body '{
    "customerId": "1",
    "rentalDetailsRequestList": [
        {
            "movieId": "1",
            "noOfDays": 2
        },
        {
            "movieId": "2",
            "noOfDays": 2
        },
        {
            "movieId": "3",
            "noOfDays": 3
        }
        ]
    }'

### Sample Response

    {
    "customer": {
        "id": 1,
        "name": "John"
    },
    "rentalLineDetailsResponse": [
        {
            "movieName": "Super Man",
            "rent": 1.5,
            "frequentEnterPoints": 1
        },
        {
            "movieName": "Inception",
            "rent": 2,
            "frequentEnterPoints": 1
        },
        {
            "movieName": "The Shawshank Redemption",
            "rent": 9,
            "frequentEnterPoints": 2
        }
    ],
    "totalRent": 12.5,
    "frequentEnterPoints": 4
    }

### Enhancements based on requirements
- Can implement more services to perform basic CRUD operations for Movie, Customer
- Can add more Movie Genres
- Can implement data and schema validators
- Can implement OAuth or other login functionalities to achieve security
- Can include docker, kubernetes for containerization
- Can implement Git Actions, CICD Pipelines
- Can use relational databases to achieve Data Integrity, Scalability and much more
- Can use YAML/properties files to store which data have scope for frequent changes

