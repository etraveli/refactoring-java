package refactoring.java.statement;

/**
 * Generates the statement report.
 */
public class StatementReportGenerator {

    String generateStatementReport(StatementData statementData) {
        StringBuilder statementReport = new StringBuilder();

        createHeader(statementReport, statementData.getName());
        for (StatementLineItem statementLineItem : statementData.getLineItems()) {
            createLineItem(statementReport, statementLineItem.getTitle(), statementLineItem.getAmount());
        }
        createFooter(statementReport, statementData.getTotalAmount(), statementData.getTotalLoyaltyPoints());

        return statementReport.toString();
    }

    private void createHeader(StringBuilder statementReport, String name) {
        statementReport.append("Rental Record for ");
        statementReport.append(name);
        statementReport.append("\n");
    }

    private void createLineItem(StringBuilder statementReport, String title, double rentalAmount) {
        statementReport.append("\t");
        statementReport.append(title);
        statementReport.append("\t");
        statementReport.append(rentalAmount);
        statementReport.append("\n");
    }

    private void createFooter(StringBuilder statementReport, double totalAmount, int loyaltyPoints) {
        statementReport.append("Amount owed is ");
        statementReport.append(totalAmount);
        statementReport.append("\n");

        statementReport.append("You earned ");
        statementReport.append(loyaltyPoints);
        statementReport.append(" frequent points\n");
    }
}
