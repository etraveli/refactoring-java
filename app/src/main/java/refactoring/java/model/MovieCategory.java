package refactoring.java.model;

public enum MovieCategory {
    REGULAR("regular"),
    CHILDRENS("childrens"),
    NEW("new");

    private String name;

    MovieCategory(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
