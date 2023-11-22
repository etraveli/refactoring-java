package entity;

public class TitleAmount {
    private final String title;
    private final double amount;

    public TitleAmount(String title, double amount) {
        this.title = title;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "\t" + title + "\t" + amount + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TitleAmount tp = (TitleAmount) o;
        return title.equals(tp.title) && amount == tp.amount;
    }
}
