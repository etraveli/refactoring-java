# Code analysis & thoughts behind refactoring
* __Movie__
	- This seems to be the atomic entity in the whole application. 
	- For the purpose of categorizing movies, i thought 'category' is a better name than 'code'. Also, i think an _enum_ for this is well suited.
	- Category might change when the movie has been sometime in the market, so i was tempted to add a setter but then i have to consider the fact that this change can happen in the middle of an onging rental thus requiring a date based rental calculation. So i did not.
	- I am assuming movie rental refers to a physical CD pack so the 'movieId' used in _MovieRental_ should be an identifier field in _Movie_ to cover for different copies of the same movie. This can be disputed though in the sense that product code and product serial have different purpose.
	
* __MovieRental__
	- Here i used the _Movie_ object in question instead of the 'movieId' hoping that it will depict the whole-part relation of the rental record with the movie. But then again not really necessary if we are not thinking from object-relational mapping angle (i like ORM!).
	- I thought that rental amount is part of the rental record, so i added the rental calculation here.
	- I was debating if the bonus (frequenter point) should be a part as well since rentals generate bonus, but then decided to keep it with _Customer_ since customer keeps the bonus.

* __Customer__
	- I added a customer id hoping in all practicality that this data be saved in some database and we could definitely use an identifier then.
	- Also, i added a method to calculate the bonus based on the rental since bonus is a property of customer (or rather a membership in classical sense). This also brings me to the question as to wherether this class should be refactored to a customer entity with profile data and a membership entity that maps customer to rentals. But then i chose to keep it simple.

* __RentalInfo__
	- I thought this should only perform reporting job, so tried to keep calculations (business logic) out of this mostly. I debated if i should write separate utility clases for the calculations but then decided to write methods in _MovieRental_ and _Customer_ classes. Also the 'statement()' method can be static here.

* __MovieStore__
	- I only introduced this class to depict that we need to have some kind of data store to run CRUD operations while running a practical application. A similar approach could have been taken for _Customer_ as well, but i thought i'd keep it simple.

* __Main__
	- This seems to be a classic contender for unit test case. I kept this untouched as much as possible since this is where we check if the rest of the code works the same after refactoring. But, should this be remodelled into a proper test suit? How far am i expected to work on this?
	
	
# Some more thoughts and questions
* Refactoring usually works within the bounds of the code that is implemented to achieve a certain goal. Its not same as bug-fixing or enhancements. It is more of breaking the existing code into more clear segregation of duties, better re-usability of components and a more readable structure while doing the exactly same thing. May be also create a few opening for extensibility.
* In that light above, i was in self-debate as to how much i should work on refactoring the existing code. I tried to keep it simple and pragmatic, keeping away from my imaginations.
* But some thoughts still ran:
	- Shall i write a service layer, a dao layer to support CRUD?
	- What if i add a web layer to enable a REST bases interaction with the system?
	- Should bonus be redeemable? Then should it be a property instead of an instant calculation?
	- How about a in-memory database and JPA implementation as well?
	- Shall i write junit based test cases?
	- Why not restructure the whole code base to support maven build?
	- Why not use lombok to make the entity beans look nice.
	- How about a fancy template processor to output the formatted rental info?