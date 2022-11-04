package models.enums;

public enum MovieCode {
    REGULAR("regular"), NEW("new"), CHILDREN("children");
    private String label;
    MovieCode(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
