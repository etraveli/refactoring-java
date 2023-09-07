package com.etravli.rent.enums;

/**
 * Movie Code type
 */
public enum CodeEnum {
    REGULAR("regular"),
    CHILDREN("children"),
    NEW("new");

    private final String code;

    CodeEnum(String value) {
        this.code = value;
    }


    public String getCode() {
        return code;
    }
}
