package org.courses.typePayment;

public class InterBankPayment extends Payment {
    public InterBankPayment(double bankAccount) {
        super(bankAccount);
    }

    @Override
    public void takePercentForPayment() {
    }
}
