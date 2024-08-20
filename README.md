# Refactoring Java

The code creates an information slip about movie rentals.
Rewrite and improve the code after your own liking.

Think: you are responsible for the solution, this is a solution you will have to put your name on.


## Handing in the assignment

Reason how you have been thinking and the decisions you took. 
You can hand in the result any way you feel (git patch, pull-request or ZIP-file).
Note: the Git history must be included.

## Solution 
Below are the steps on my thought process, the reasoning behind it is 
pretty standard for a refactoring process: 
Make it work, Make it beautiful and organized, Then make it fast.

1. Understand the code 
	- It's a Movie rental statement generator for Customers.
	Contains classes organized by Customer, Movie, MovieRental,
	RentalInfo.
2. Understand and list the issues in the codebase.
    - Unreadable
	- Tests are not properly organized
	- Classes are unorganized
	- Not using SOLID principles
	- Not using best practices
	- Not using production grade ecosystem
	- Other potential improvements: Code smells, Linting, Improve speed/performance

3. Iterate on fixing the issues so that the codebase will be 
production grade.


## More refactoring ideas

   - Better robust error handling using exceptions
   - More comprehensive test cases
   - Functional Programming idioms (not necessary)
   - Spring boot


## To run mvn test
```
mvn test
```
## To run the test:

```
cd src
javac main/java/customer/*.java main/java/movie/*.java main/java/rental/*.java Main.java
java Main
```
