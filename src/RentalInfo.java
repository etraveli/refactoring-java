

public class RentalInfo {

  public String statement(Customer customer) {
    /*
     * Removed the hashmap from this file.
     * As it is not realistic to have something like a database and the logic(calculating the price) in the same class.
     */
      
    MovieDataBase db = new MovieDataBase();
    double totalAmount = 0;
    int frequentEnterPoints = 0;
    String result = "Rental Record for " + customer.getName() + "\n";
    for (MovieRental r : customer.getRentals()) {
      double thisAmount = db.getPrice(db.getMovie(r.getMovieId()).getCode());

      // determine amount for each movie
      /* Changed the 3 if conditions to an if else... condition.
       * A single movie could not satisfy more than one condition.
       * No point in checking all 3 conditions as only one movie gets tested in every iteration of the loop.  
       */
              if (db.getMovie(r.getMovieId()).getCode().equals("regular")) {
                  if (r.getDays() > 2) {
                    thisAmount = ((r.getDays() - 2) * 1.5) + thisAmount;
                  }
                }
                else if (db.getMovie(r.getMovieId()).getCode().equals("new")) {
                  thisAmount = r.getDays() * 3;
                  /* add bonus for a two day new release rental.
                   * Doing it within this condition itself instead of checking again for the condition outside of this if else statement
                   */
                  if(r.getDays() > 2)
                      frequentEnterPoints++;
                }
               
                else if (db.getMovie(r.getMovieId()).getCode().equals("childrens")) {
                  if (r.getDays() > 3) {
                    thisAmount = ((r.getDays() - 3) * 1.5) + thisAmount;
                  }
                }
      
      

      //add frequent bonus points
      frequentEnterPoints++;
      
      

      //print figures for this rental
      result += "\t" + db.getMovie(r.getMovieId()).getTitle() + "\t" + thisAmount + "\n";
      totalAmount += thisAmount;
    }
    /*
     * Adding the frequentEnterPoints earned in this transaction to the existing frequentEnterPoints.
     */
    customer.addFrequentEnterPoints(frequentEnterPoints);
    // add footer lines
    result += "Amount owed is " + totalAmount + "\n";
    result += "You earned " + frequentEnterPoints + " frequent points\n";
    

    return result;
  }
}
