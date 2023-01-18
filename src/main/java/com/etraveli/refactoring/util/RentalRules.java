package com.etraveli.refactoring.util;

import com.etraveli.refactoring.model.*;

public class RentalRules {
    
    public static double cost(MovieCode code, int days){
        double thisAmount = 0;
        switch (code) {
        case REGULAR:
            thisAmount = 2;
            if (days > 2) {
            thisAmount = ((days - 2) * 1.5) + thisAmount;
            }
            break;
        case CHILDRENS:
            thisAmount = 1.5;
            if (days > 3) {
            thisAmount = ((days - 3) * 1.5) + thisAmount;
            }
            break;
        case NEW:
            thisAmount = days * 3;
            break; 
        }
        return thisAmount;
    }
    
    public static int points(MovieCode code, int days){
        int points = 1;
        // add bonus for a two day new release rental
        if (code == MovieCode.NEW && days > 2) points++;
        return points;
    }
}
