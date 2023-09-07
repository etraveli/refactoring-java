package com.etravli.payslip.model;

import com.etravli.payslip.common.PaySlipConstants;

import java.util.*;

/**
 * PaySlip class for the customer
 */
public class PaySlip {
    private final String customerName;
    private List<Map<String,Object>> details;
    private Double totalAmount = 0D;
    private Integer totalEarnedPoints = 0;


    public PaySlip(String customerName) {

        this.customerName = customerName;
    }

    public String getCustomerName() {

        return customerName;
    }

    public List<Map<String,Object>> getDetails() {

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
     * Add detail line for the paySlip
     * @param lineAsMap
     */
    public void addDetail(Map<String,Object> lineAsMap) {
        if (details == null) {
            this.details = new ArrayList<>();
        }
        details.add(lineAsMap);

    }
}
