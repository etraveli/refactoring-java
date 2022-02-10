package refactoring.java.statement;

/**
 * Statement data for an individual line item (e g a rented movie).
 */
public class StatementLineItem {
    String title;
    double amount;
    int loyaltyPoints;

    public StatementLineItem(String title, double amount, int loyaltyPoints) {
        this.title = title;
        this.amount = amount;
        this.loyaltyPoints = loyaltyPoints;
    }

    public String getTitle() {
        return title;
    }

    public double getAmount() {
        return amount;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }
}
