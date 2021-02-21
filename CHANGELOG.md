##Refactored changes

- Added `enum` type for the Movie codes, so it can be used consistently in the entire project.
- Changed a variable and method names to be more meaningful
- Separated the rental amount and bonus point computation from the `getStatement` so it has a single responsibility
- Append specific error messages so user is aware of how the record was handled
- Made the `movies` hashMap a constant. 
- Added comments
- Added specific test cases for the `getStatment` method
- Moved all classes o the java directory, so they are separated from the tests


####Good to have but not implemented :

There were a few refactoring possibilities which were not implemented as I wasnt sure to what level the refactoring had to be done. The below changes could have altered the structure of the original code, hence left not implemented. 

- `movies` hashMap could have been separated out as Repo class.
- moving the `MovieCode` specific computations from `RentalService` class to the `MovieCode` enum class. 
