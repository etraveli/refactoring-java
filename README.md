# Movie Service

## Changes

- Convert the application into rest service
- Refactor the entity class
- Add error handling
- Add logs
- Add test cases

## Future Enhasment

- Use database to store data such as Customer,Movies,Rental Info
- Expose more endpoints
- Add endpoint security

## Technologies

- Java 17, Spring boot 3, Maven
- Junit 5/Mockito
- SonarLint & IntelliJ code quality checker
- Swagger

## Run service 
    java -jar movie-service-0.0.1.jar

## Sample Request & Response
Request

    curl --location 'http://localhost:8181/movie-service/api/order' \
    --header 'Content-Type: application/json' \
    --data '{
    "customerID": "001",
    "orderLineList": [
        {
            "movieCode": "F001",
            "rentDays": 1
        },
        {
            "movieCode": "F002",
            "rentDays": 2
        },
        {
            "movieCode": "F004",
            "rentDays": 3
        }
        ]
    }'

Response

    {
    "customer": {
        "customerID": "001",
        "customerName": "C. U. Stomer"
    },
    "rentOrderLineList": [
        {
            "movieCode": "F001",
            "movieName": "You've Got Mail",
            "rentDays": 1,
            "rentAmount": 2
        },
        {
            "movieCode": "F002",
            "movieName": "Matrix",
            "rentDays": 2,
            "rentAmount": 2
        },
        {
            "movieCode": "F004",
            "movieName": "Fast & Furious X",
            "rentDays": 3,
            "rentAmount": 9
        }
    ],
    "totalRental": 13,
    "frequentEnterPoints": 4
    }
    

