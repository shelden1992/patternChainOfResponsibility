package org.courses.typePayment;

public abstract class Payment {
    private double bankAccount;

    public Payment(double bankAccount) {
        this.bankAccount = bankAccount;
    }

    public double getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(double bankAccount) {
        this.bankAccount = bankAccount;
    }

    public abstract void takePercentForPayment();
}
