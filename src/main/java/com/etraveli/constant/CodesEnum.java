package com.etraveli.constant;

import lombok.Getter;

@Getter
public enum CodesEnum {
    REGULAR("REGULAR"),
    CHILDRENS("CHILDRENS"),
    NEW("NEW");

    private final String value;

    CodesEnum(String value) {
        this.value = value;
    }
}
