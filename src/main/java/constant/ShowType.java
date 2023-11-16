package constant;

public enum ShowType {

    REGULAR("REGULAR", "Regular"),
    NEW_RELEASE("NEW_RELEASE", "New release"),
    CHILDREN("CHILDREN", "Children");

    private final String key;
    private final String value;

    ShowType(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
