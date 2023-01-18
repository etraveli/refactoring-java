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

# Solution Notes
I will go beyond the scope of strict refactoring by adding some new functionality and change input format. 

I will also leave the Main class mostly alone to be able to verify that everything is still working as it originally did.

Maven can now be used to build jar and test.
```
mvn clean install
java -cp target/refactor-1.0-SNAPSHOT.jar com.etraveli.refactoring.Main
```

## Todo

- ~~Add build tool.~~
    
  *Easier testing and building.*

- ~~Separate test and src.~~

  *Should never be together.*

- ~~Add tests to verify current functionality.~~

  *Tests for the categories **new** and **children** seems to be the most important parts missing.*

  *Tests for bonus points, renting new movie for three days or more gives one bonus point. Comment says two days though.*

  *Test for regular movies when renting for more than two days. Cost should stay the same for one and two days.*

  *Test for childrens movies when renting for more than three days. Cost should stay the same for one, two and three days*

- ~~Restructuring~~

  *Add som basic packages and sort out the different classes.*

- ~~Add some sort of movie db.~~
    
  *RentalInfo does not look like it should be concerned with available titles. Eventually this would be in an actual DB but we will have it in memory for now and just decouple it to make it easier if we ever want to add a database.*

- ~~Add Movie categories as enum or something similar.~~

  *Should not change, at least not often.*

- ~~Customer DB~~

  *Same as for movies, in memory for now*

- ~~Frequent points should be tied to customer and rental.~~
    
  *Points should be tied to customer and the specific rental, if the rules for point calculations change in the future the history probably shouldn't. Therefore we calculate points earned when a new rental is added.*

- ~~Customer ID~~

  *Name is a bad ID, sometimes they change, often they are the same. Could be personal id from customer or something generated and assigned. Leaning towards personal id since they are renting.*

- ~~Change rentalinfo input~~

  *Remove models from input and only accept Strings, I look at the rentalInfo class as the API and prefer to not have inner models accepted as input.*

- ~~Utility class for calcultating costs and points~~

  *Try to move the logic away from the api level.*

- ~~Add statement model~~

  *New statement model with a simple builder to make it easier to see what is happening when a statement it created.*

## Future improvements

- Config

  *Basic config, business rules as config for example cost and number of days for different categories.*

- Move the statement static strings into variables

  *Make it easier to change the static values, maybe move them into language files to allow for different languages.*

- CustomerServiceAPI

  *For handling customer data, like changing name, maybe add more data.*

- MovieServiceAPI

  *For handling the movie catalog.*

- Some way to handle input

  *Could be a CLI or a Rest api or something else. Depends on how this service is supposed to function*
