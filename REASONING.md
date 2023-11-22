* All logic are inside RentalInfo.java. So before refactoring too much I would like to add some more tests around that\
    part of the code.\
    I start with unit test because they are easy, but in a larger code base I would try to add more covering tests like\
    integration, function, end-to-end and so on.\
    Converting the project to maven makes it easier to add JUnit test framework.
  * Just writing the tests I found a null pointer exception and that you can rent movies with negative days.\
  assuming I'm not fixing functionality, so I decided to only fix the null pointer exception.
  * After writing some tests I realised it would be easier to write better test if we had a Statement object instead of\
  a string returned as result. Now we can easier check specific variables inside the object instead of trying to\
  a string. It also allows us to use the Statement class instead of creating error-prune and hard-to-read\
  multiline strings.
