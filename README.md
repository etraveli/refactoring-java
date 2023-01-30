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

## Changes made

1. Added the Customer, movie and rental details in the Main class into variables to maintain consistency, and make the code easier to read.
2. Created a MovieDataBase class to act as the database in this application which stores the movie details and the pricelist for movies based on their type.
3. Added a frequentEnterPoints field in the Customer class, overloaded the constructor in case there was a case where new customer was to be added with existing frequentPoints.
4. Made a function(addFrequentEnterPoints()) in the Customer class to add frequentEnterPoints earned in the latest transaction to the existing value.
5. Added setters in multiple classes, even though they are not required in the current scenario, it makes sense in the longrun.
In the RentalInfo class
6. Removed the hasmapfrom the class, so that the DB is separate from the logic of the application.
7. Fetched the price from the db and not within the if statements, the prices were hard coded, which does not make sense in terms of technical debt.
8. Changed the 3 if statements to a series of if else statements, reduces the number of decicsion stetemtens that need to be encountered within the loop.
9. Added the extra frequentEnterPoints point within the series of if statements for the "new" type of movies.
10. Adding the frequentEnterPoints to existing points of the customer.

The customer details must also be made to be fetcehd from the DB and not just instantiated in the main funciton as done here.
In addition to the changes mentioned, there could be more structered way of organising the projects files structure, by keeping seperate packages for the DB, service and logic.

Comments added in the code for every added modified or deleted line of code.
