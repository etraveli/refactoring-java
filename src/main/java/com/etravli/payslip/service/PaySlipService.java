package com.etravli.payslip.service;

import com.etravli.payslip.common.PaySlipConstants;
import com.etravli.payslip.model.PaySlip;
import com.etravli.rent.domain.Customer;
import com.etravli.rent.domain.MovieRental;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ayman
 * Service class to create paySlip
 * and payslip prinnt for the customer
 * Based on his rent
 */
public class PaySlipService {

    /**
     * Create and fill customer statement that will be used
     * for filling the printed statement
     *
     * @param customerRent
     * @return
     */
    public PaySlip buildStatement(Customer customerRent) {
        PaySlip customerStatement = new PaySlip(customerRent.getName());

        for (MovieRental rent : customerRent.getRentalList()) {
            updateCustomerStatement(rent, customerStatement);
        }
        return customerStatement;

    }

    /**
     * Update statement per each line
     * Update totals
     *
     * @param rent
     * @param customerStatement
     */
    private void updateCustomerStatement(MovieRental rent, PaySlip customerStatement) {
        double lineAmount = 0D;
        customerStatement.setTotalEarnedPoints(customerStatement.getTotalEarnedPoints() + 1);

        switch (rent.movie().code()) {
            case REGULAR:
                lineAmount = 2D;
                if (rent.days() > 2) {
                    lineAmount = ((rent.days() - 2) * 1.5) + lineAmount;
                }
                break;
            case NEW:
                lineAmount = rent.days() * 3;
                if (rent.days() > 2) {
                    customerStatement.setTotalEarnedPoints(customerStatement.getTotalEarnedPoints() + 1);
                }
                break;
            case CHILDREN:
                lineAmount = 1.5;
                if (rent.days() > 3) {
                    lineAmount = ((rent.days() - 3) * 1.5) + lineAmount;
                }
            default:

        }

        // Add detail line
        Map<String, Object> lineDetail= new HashMap<>();
        lineDetail.put(PaySlipConstants.DETAIL_HEADER_NAME_TITLE,rent.movie().title());
        lineDetail.put(PaySlipConstants.DETAIL_HEADER_NAME_AMOUNT, lineAmount);
        customerStatement.addDetail( lineDetail);
        // update total amount
        customerStatement.setTotalAmount(customerStatement.getTotalAmount() + lineAmount);
    }

    /**
     * Generate  paySlip  for customer
     *
     * @param paySlip
     * @return
     */
    public String printStatement(PaySlip paySlip) {
        StringBuilder statement = new StringBuilder();
        statement.append(String.format(PaySlipConstants.HEADER_RENTAL_RECORD, paySlip.getCustomerName()));
        paySlip.getDetails().forEach(line -> statement.append(String.format(PaySlipConstants.DETAIL_LINE_TAB, line.get(PaySlipConstants.DETAIL_HEADER_NAME_TITLE),(Double) line.get(PaySlipConstants.DETAIL_HEADER_NAME_AMOUNT))));
        statement.append(String.format(PaySlipConstants.FOOTER_AMOUNT_OWED, paySlip.getTotalAmount()));
        statement.append(String.format(PaySlipConstants.FOOTER_FREQUENT_ENTER_POINTS, paySlip.getTotalEarnedPoints()));
        return statement.toString();

    }
}
