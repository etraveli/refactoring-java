package com.etravli.payslip.model;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * PaySlip class for the customer
 */
public class PaySlip {
    private final String customerName;
    private Map<String, Double> details;
    private Double totalAmount = 0D;
    private Integer totalEarnedPoints = 0;


    public PaySlip(String customerName) {

        this.customerName = customerName;
    }

    public String getCustomerName() {

        return customerName;
    }

    public Map<String, Double> getDetails() {

        return details;
    }

    public Double getTotalAmount() {

        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getTotalEarnedPoints() {
        return totalEarnedPoints;
    }

    public void setTotalEarnedPoints(Integer totalEarnedPoints) {
        this.totalEarnedPoints = totalEarnedPoints;
    }

    /**
     * Add detail line for the statement
     *
     * @param title
     * @param value
     */
    public void addDetail(String title, Double value) {
        if (details == null) {
            this.details = new LinkedHashMap<>();
        }
        details.put(title, value);

    }
}
