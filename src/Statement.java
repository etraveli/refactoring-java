
import java.util.Map;

/**
 * Dedicated class for statement. In future if we need to change the structure
 * of the statement, we will have one place to change in. Also, now the code of
 * formatting the statement is separated from the code of calculating values
 * 
 */
public class Statement {

    private double totalAmount;
    private int frequentEnterPoints;
    private String customerName;
    private Map<String, Double> moviesAmounts;

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setFrequentEnterPoints(int frequentEnterPoints) {
        this.frequentEnterPoints = frequentEnterPoints;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setMoviesAmounts(Map<String, Double> moviesAmounts) {
        this.moviesAmounts = moviesAmounts;
    }

    @Override
    public String toString() {
        String result;

        if (this.totalAmount > 0) {
            result = "Rental Record for " + this.customerName + "\n";

            for (Map.Entry<String, Double> entry : this.moviesAmounts.entrySet()) {
                result += "\t" + entry.getKey() + "\t" + entry.getValue() + "\n";
            }

            result += "Amount owed is " + this.totalAmount + "\n";
            result += "You earned " + this.frequentEnterPoints + " frequent points\n";

        } else {
            result = Const.Msg.NO_RESULT;
        }
        return result;
    }

}
