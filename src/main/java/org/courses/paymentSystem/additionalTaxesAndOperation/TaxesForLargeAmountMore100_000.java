package org.courses.paymentSystem.additionalTaxesAndOperation;

import org.apache.log4j.Logger;
import org.courses.paymentSystem.bankTaxes.BankOperationChain;
import org.courses.typePayment.Payment;

import static java.util.Objects.isNull;


public class TaxesForLargeAmountMore100_000 implements BankOperationChain {
    private static final Logger LOG = Logger.getLogger(TaxesForLargeAmountMore100_000.class);
    private static final double TAXES_FOR_LARGE_AMOUNT = 0.05;
    private static final int LARGE_AMOUNT = 100_000;
    private double amount;
    private BankOperationChain nextChainOperation;

    @Override
    public void setNextChain(BankOperationChain bankOperationChain) {
        this.nextChainOperation = bankOperationChain;
    }

    @Override
    public void dispense(Payment payment) {
        double amount = payment.getBankAccount();
        if (amount > LARGE_AMOUNT) {
            LOG.info("Bank account in payment = " + payment.getBankAccount());
            double result = payment.getBankAccount() * TAXES_FOR_LARGE_AMOUNT;
            LOG.info("Percent is = " + result);
            double resultWithMinusPercent = amount - result;
            if (resultWithMinusPercent > 0) {
                payment.setBankAccount(resultWithMinusPercent);
            }

        }
        if (isNull(nextChainOperation)) {
            LOG.info("It's last operation, take you cash = " + payment.getBankAccount());
        } else nextChainOperation.dispense(payment);


    }
}
