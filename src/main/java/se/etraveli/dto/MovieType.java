package se.etraveli.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MovieType {
    REGULAR(2),
    CHILDRENS(2),
    NEW(1);

    private double price;
}
