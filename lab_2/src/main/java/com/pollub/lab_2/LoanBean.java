package com.pollub.lab_2;

import java.io.Serializable;

public class LoanBean implements Serializable {
    private double amount;
    private double interestRate;
    private int periodInMonths;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getPeriodInMonths() {
        return periodInMonths;
    }

    public void setPeriodInMonths(int periodInMonths) {
        this.periodInMonths = periodInMonths;
    }

    public double getMonthlyPayment() {
        double monthlyInterestRate = interestRate / 100 / 12;
        var result = (amount * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -periodInMonths));
        if (Double.isNaN(result))
            throw new IllegalArgumentException("Invalid input");
        return result;
    }
}
