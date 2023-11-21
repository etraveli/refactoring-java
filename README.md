# ♾️ Refactoring Java - Daniel Crosby

## ▶️ To run the test

```
javac src/*.java
java -cp src Main
```

Additional tests have been created to test different scenarios.

```
java -cp src Test
```

## 📖 Definition of Refactoring

"restructure (the source code of an application or piece of software) so as to improve operation without altering functionality."

With this definition in mind, how the default test case in Main.java is built/run should not be changed in any way nor the expected ouput be modified. i.e things such as build tools and libraries should be avoided as that changes the expected functionality of the software. The only changes that happen should be within the internal workings/structure of the code.

This helps simulate a more real world scenario where projects can be very large and mature, where restarting them from scratch is generally not a good idea.

## ⚙️ Changes

The project has been refactored in a way to make it easier for future development or maintenance. It now makes use of things like abstract classes and interfaces to allow different components of the application to be swapped in/out via dependency injection.

### ✉️ Services

The application makes use of "service classes" that make is significantly easier to test due to logic being separated out into its related service. This has the additional effect of making migration from a monolithic based architecture to a microservice based architecture smoother if such a thing would be required.

Methods within services function as their name suggests and additionally throw exceptions with messages detailing if invalid arguments have been passed in. e.g. a `null` value was used.

#### 🎥 Movies Service

The movies service interface contains two methods `getMovieById` and `calculateRentPrice`. These are implemented by `LocalMoviesService` which stores all the data about available movies in memory.

#### 📃 Statement Service

The statement service contains one method `createStatement`. It is implemented by `LocalStatementService` that also relies on an implementation of the movies service. It uses the data supplied to it to construct the required `Statement` object that will contain all the necessary data. In a production system this object may be persisted in a database for future records.

### 📒 Data

#### 💲Handling Money

One of the main changes to how data is handled within the application is the calculations for money. This has been done by replacing the `double` based values to `BigDecimal`. Floating point numbers will lead to inaccuracy in math calculations which is somthing that must be avoided when working with real money. The big decimal handles decimal point math accurately and avoids any of these issues.

#### 📘 Read Only Data

The basic data classes `Movie` and `MovieRental` have been converted into `records` as they are read only and it is unnecessary for them to be full classes.

#### ⛔ Removed

The `Customer` class has been removed as it should not be handling what movies the customer wants to rent and the only remaining information in this class is the customers name.

## 🔨 Possible future changes outside of refactoring

### ➕ Additional services

Additional services could be created such as one for `users` to manage users and keep track of their history of rental statements. The existing serivices can be expanded to allow for more operations such as creating a movie.

### 🛠️ Using a build tool

A tool such as gradle or maven would simplify the build, execution, testing, and deployment of this application. One was not used in this assesment as using one would put any work done outside the of scope of refactoring.

### 💻 GUI

A graphical user interface e.g a web based one would improve the interation with the application and its services.

### 📦Package structure

An improved package structure for this project in its curent state could look like this.

```
src/main/java/
├─ Main.java
├─ Test.java
├─ data/
| ├─ Movie.java
| ├─ MovieCode.java
| ├─ MovieRental.java
| ├─ Statement.java
├─ services/
| ├─ LocalMoviesService.java
| ├─ MoviesService.java
| ├─ LocalStatementService.java
| ├─ StatementService.java
```

This would separate out unrelated classes and files into a more a modular layout but doing this requires changing the java class path i.e how the software is run.
