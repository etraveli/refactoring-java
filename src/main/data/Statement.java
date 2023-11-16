package data;

import java.util.ArrayList;
import java.util.List;

public final class Statement {

  private final String customerName;
  private final List<String> entries;

  private double totalPrice;
  private int frequentPoints;

  public Statement(String customerName) {
    this.customerName = customerName;
    this.frequentPoints = 0;
    this.totalPrice = 0;
    entries = new ArrayList<>();
  }

  public boolean addEntry(String movieTitle, double rentPrice, boolean isTwoDayNewRelease) {
    totalPrice += rentPrice;
    frequentPoints = frequentPoints + (isTwoDayNewRelease ? 2 : 1);
    return entries.add("\t" + movieTitle + "\t" + rentPrice + "\n");
  }

  @Override
  public String toString() {
    return "Rental Record for " + customerName + "\n" +
        String.join("", entries) +
        "Amount owed is " + totalPrice + "\n" +
        "You earned " + frequentPoints + " frequent points\n";
  }

}
