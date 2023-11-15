package exception;

public enum ExceptionCode {
    ERR01("ERR01", ":The configuration file is not found! File name is "),
    ERR02("ERR02", ":Error loading configuration from the file name "),
    ERR03("ERR03", ":The configuration property is not setup correctly. Please run the program again!"),
    ERR04("ERR04", ":The property is not found! Property is ");

    private final String key;
    private final String value;

    ExceptionCode(String key, String value) {
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

