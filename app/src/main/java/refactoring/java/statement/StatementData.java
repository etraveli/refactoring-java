package refactoring.java.statement;

import java.util.ArrayList;
import java.util.List;

public class StatementData {
    private String name;
    private List<StatementLineItem> lineItems;

    public StatementData(String name) {
        this.name = name;
        this.lineItems = new ArrayList<>();
    }

    public double getTotalAmount() {
        return lineItems.stream()
                .filter(li -> li != null)
                .mapToDouble(StatementLineItem::getAmount)
                .sum();
    }

    public int getTotalLoyaltyPoints() {
        return lineItems.stream()
                .filter(li -> li != null)
                .mapToInt(StatementLineItem::getLoyaltyPoints)
                .sum();
    }

    public String getName() {
        return name;
    }

    public List<StatementLineItem> getLineItems() {
        return lineItems;
    }

    public void addLineItem(StatementLineItem statementLineItem) {
        lineItems.add(statementLineItem);
    }
}
