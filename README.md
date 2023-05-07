# Refactoring Java

The code creates an information slip about movie rentals.
Rewrite and improve the code after your own liking.

Think: you are responsible for the solution, this is a solution you will have to put your name on.


## Handing in the assignment

Reason how you have been thinking and the decisions you took. 
You can hand in the result any way you feel (git patch, pull-request or ZIP-file).
Note: the Git history must be included.


## To run the test:

```
javac src/*.java
java -cp src Main
```

## Code Refactoring Changes

### Assumptions
1. Movie Rental Data will be fetched from Configuration file i.e. application.properties.
2. Project will be used with Maven build tool.
3. Project uses SpringBoot framework.
4. Project is started as SpringBoot Application and then closes once validation completes.


### Changes:
    1. Packages are created to separate the functionalities.
    2. Interfaces are created for services to hide complexity.
    3. Movie rental data is read from application.yaml in MovieRentalConfiguration.
    4. Movie rental repository converts configuration data to Movie object using Object Mapper.
    5. Movie code is added as Enum based on movie code.
    6. Movie Rental costs i.e. rent amount and bonus are calculated for each rented movie.
        6.1 Amount and bonus is calculated using movie code and rent days.
        6.2 Separate classes are created to calculate amount and bonus.
        6.3 Amount is calculated with the help of classes of each movie code.        
    7. Movie Details service is created to fetch movie details from repository.
    8. Validation rules for customer data is created.
    9. Exception classes are created to handle valid input.
    10. Static text strings are moved to Constants.
    11. Test cases have been added to test project core functionalities.


### Project Structure (New):
    se.etravali.movie.rentals
        Main.class
        component.rent:
            MovieRent.interface
            NewMovieRent.class
            RegularMovieRent.class
            ChildrensMovieRent.class
        config
            MovieRentalConfiguration.class
        constants
            Constants.class
        exception
            InvalidCustomerException.class
            MovieRentalException.class
        model
            Customer.class
            Movie.class
            MovieCode.class
            MovieRental.class
            MovieRentalCosts.class
        repository 
            MovieRepository.class
        service
            calc                
                costs
                    FrequentEnterPointsService.interface
                    FrequentEnterPointsServiceImpl.class
                    RentalAmountService.interface
                    RentalAmountServiceImpl.class
                RentalCostsCalcService.interface
                RentalCostsCalcServiceImpl.class
            RentalInfo.class
            MovieDetailsService.interface
            MovieDetailsServiceImpl.class
        utils
            CustomerValidation.class

### Technical Workflow
    1. Customer details are validated using CustomerValidation class.
        1.1 Throws Exception in following cases: 
            1.1.1 Customer is null
            1.1.2 Customer name is empty/null
            1.1.3 Movie rental is empty/null
            1.1.4 Any of the movie is rented for 0 days or movie id is null.

    2. After successful validation, Result statement is initialised with customer name.

    3. Each movie rental is iterated to fetch individual rent cost and bonus.
        3.1 Costs and bonus is calculated using RentalCostsCalcServiceImpl.class
            3.1.1 Business Logic to calculate the rental cost is based on Movie Category and number of days.
            3.1.2 Rent is calculated using RentalAmountService.class
                3.1.2.1 It calculates rent based on Movie Category and initalises rent component based on category
                3.1.2.2 Each rent category gives rent based on number of days.
            3.1.3 Bonus is calculated using FrequentEnterPointsServiceImpl.class
        3.2 Movie rental costs object is created which stores movie rental, cost and bonus.

    4. Result statement is added for each movie rental with their cost and bonus.
    5. Total Cost and bonus is aggregated.
    6. Result statement adds total rental cost and bonus earned when all rental objects are iterated.
