package org.courses.paymentSystem.currency;

import org.apache.log4j.Logger;
import org.courses.paymentSystem.bankTaxes.BankOperationChain;
import org.courses.typePayment.Payment;

import static java.util.Objects.isNull;

public class UAH500Dispenser implements BankOperationChain {
    public static final Logger LOG = Logger.getLogger(UAH500Dispenser.class);
    private static final int UAH_500_DISPENSE = 500;
    private BankOperationChain nextChainOperation;

    public void setNextChain(BankOperationChain dispenseChain) {
        this.nextChainOperation = dispenseChain;

    }

    public void dispense(Payment payment) {
        double amount = payment.getBankAccount();

        if (amount >= 500) {
            int numberDenomination = (int) (amount / UAH_500_DISPENSE);
            LOG.info("Dispense " + numberDenomination + " of 500 UAH");
            int result = (int) (amount % UAH_500_DISPENSE);
            payment.setBankAccount(result);
            if (result != 0) {
                nextChainOperation.dispense(payment);
            }
        }
        if (isNull(nextChainOperation)) {
            LOG.info("Left in your account cash = " + payment.getBankAccount());
        } else {
            nextChainOperation.dispense(payment);
        }

    }
}
