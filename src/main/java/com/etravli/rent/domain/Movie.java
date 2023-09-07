package com.etravli.rent.domain;

import com.etravli.rent.enums.CodeEnum;

/**
 * @author ayman
 * Movie obect class
 */
public record Movie(String id, String title, CodeEnum code) {

}
