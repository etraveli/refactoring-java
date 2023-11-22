* All logic are inside RentalInfo.java. So before refactoring too much I would like to add some more tests around that\
  part of the code.\
  I start with unit test because they are easy, but in a larger code base I would try to add more covering tests like\
  integration, function, end-to-end and so on.\
  Converting the project to maven makes it easier to add JUnit test framework.
  * Just writing the tests I found a null pointer exception and that you can rent movies with negative days.\
    assuming I'm not fixing functionality, so I decided to only fix the null pointer exception.
  * After writing some tests I realised it would be easier to write better test if we had a Statement object instead of\
    a string returned as result. Now we can easier check specific variables inside the object instead of trying to\
    parse a string. It also allows us to use the Statement class instead of creating error-prune and hard-to-read\
    multiline strings.
* Now when I have my tests I wanted to try to clean up RentalInfo. Starting with making creating movies less error prune
  * All movie ids are now enums with a test to make sure we add entries for each movie. We still need to null check\
    it though. Movie codes are now 'categories' and are also changed into enums. This prevents us from creating\
    duplicates, but also we don't have to manually enter the categories with strings.
  * After cleaning up RentalInfo and making it more explicit, we need to look into Statement because some of the\
    logic got moved over there.
  * When I was looking into doing some clean up with the hashmap in RentalInfo I realized I can redo it as enums\
    and that would make everything much cleaner, but it would make it harder to create some kind of runtime update\
    of the 'movie database'. Thus make the list of movies available unable to change. Which it sort of already is \
    and should probably be a database in a real application. I think I will add the enum in a later commit just because\
    it looks really neat :)
* Started adding tests for Statement. The first test I added was a parameterized test for testing several different\
  inputs to the getTotalAmount. Turns out it had floating point issues and I had to fix it with a BigDecimal conversion.\
  After finish the tests for Statement I refactored TitlePoints to a separate class and renamed it. It is a class\
  representing a pair of Title and Amount after all. Then I wrote some standard tests for it.
  * I also moved all classes that I see as entities/components to folder entity. Here lies classes that only holds data\
    and no logic. Except for Statement which calculates its total on the fly, but that's okay. If we decide to add a \
    database in the future it's good to have clean and small entities/components.
* Refactoring the hashmap of all movie entries into an enum inside the Movie class. This made it so that it cannot be \
  any miss-match with enum (key) and movie id (enum inside value), it also makes it so that you only have to enter \
  title and category once. You still have to null check the movie in RentalInfo, but it should be a little better.\
  I will look into better error handling.