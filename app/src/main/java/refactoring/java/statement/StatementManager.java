package refactoring.java.statement;

import refactoring.java.config.ApplicationConfiguration;
import refactoring.java.model.Customer;

public class StatementManager {
  private ApplicationConfiguration applicationConfiguration;

  public StatementManager(ApplicationConfiguration applicationConfiguration) {
    this.applicationConfiguration = applicationConfiguration;
  }

  public String createStatement(Customer customer) {
    StatementCalculator statementCalculator = new StatementCalculator(applicationConfiguration);
    StatementReportGenerator statementReportGenerator = new StatementReportGenerator();

    StatementData statementData = statementCalculator.computeStatement(customer);

    return statementReportGenerator.generateStatementReport(statementData);
  }
}
