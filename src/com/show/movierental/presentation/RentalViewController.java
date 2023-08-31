package com.show.movierental.presentation;

import com.show.movierental.core.Customer;
import com.show.movierental.service.RentalService;

public class RentalViewController {
    private RentalService rentalService;
    private RentalConsoleView rentalConsoleView;

    public RentalViewController(RentalService rentalService, RentalConsoleView rentalConsoleView) {
        this.rentalService = rentalService;
        this.rentalConsoleView = rentalConsoleView;
    }

    public void generateAndDisplayRentalStatement(Customer customer) {
        String statement = rentalService.generateRentalStatement(customer);
        rentalConsoleView.displayRentalStatement(statement);
    }
}