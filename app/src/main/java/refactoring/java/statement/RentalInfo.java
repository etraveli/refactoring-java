package refactoring.java.statement;

import refactoring.java.LoyaltyPointsCalculator;
import refactoring.java.MovieRepository;
import refactoring.java.PriceCalculator;
import refactoring.java.model.Customer;

public class RentalInfo {

  public String createStatement(Customer customer) {
    StatementCalculator statementCalculator = new StatementCalculator(new MovieRepository(), new PriceCalculator(), new LoyaltyPointsCalculator());
    StatementReportGenerator statementReportGenerator = new StatementReportGenerator();

    StatementData statementData = statementCalculator.computeStatement(customer);

    return statementReportGenerator.generateStatementReport(statementData);
  }
}
