#Refactored

1. Created skeleton for the application
   1. Models representing domain layer
   2. enums representing enums
   3. services representing service layer
   4. Data's which are initialized on service layer should be taken from repository layer
2. Introduced MovieCode parameterized enum to be used as dynamic configuration for MovieRental amount and bonus 
3. Converting the logic of creation of information slip with the dynamic configuration.
4. In this case MovieCode is an enum class, but as well can be done as it own model, and there can be a flow of changing it on fly by the admin user

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
