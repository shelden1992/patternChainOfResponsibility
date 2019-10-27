package org.courses.paymentSystem.bankTaxes;

import org.apache.log4j.Logger;
import org.courses.typePayment.Payment;

import static java.util.Objects.isNull;

public class HalfPercentForPreferential implements BankOperationChain {
    private static final Logger LOG = Logger.getLogger(HalfPercentForPreferential.class);
    private static final double PERCENT = 0.005;
    private BankOperationChain nextChainOperation;

    public void setNextChain(BankOperationChain bankOperationChain) {
        this.nextChainOperation = bankOperationChain;
    }

    public void dispense(Payment payment) {
        double amount = payment.getBankAccount();
        if (amount > 0) {
            LOG.info("Bank account in payment = " + payment.getBankAccount());
            double result = payment.getBankAccount() * PERCENT;
            LOG.info("Percent is = " + result);
            double resultWithMinusPercent = amount - result;
            if (resultWithMinusPercent > 0) {
                payment.setBankAccount(resultWithMinusPercent);
                if (isNull(nextChainOperation)) {
                    LOG.info("It's last operation, take you cash = " + resultWithMinusPercent);
                } else {
                    nextChainOperation.dispense(payment);
                }
            }

        }
    }


}
