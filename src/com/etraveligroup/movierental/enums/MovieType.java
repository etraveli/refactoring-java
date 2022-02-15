package com.etraveligroup.movierental.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author LMOPURI
 *
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
public enum MovieType {
	REGULAR("regular"), CHILDRENS("childrens"), NEW("new");

	private String name;
}
