package org.courses.paymentSystem.currency;

import org.apache.log4j.Logger;
import org.courses.paymentSystem.bankTaxes.BankOperationChain;
import org.courses.typePayment.Payment;

import static java.util.Objects.isNull;

public class UAH200Dispenser implements BankOperationChain {
    public static final Logger LOG = Logger.getLogger(UAH200Dispenser.class);
    private static final int UAH_200_DISPENSE = 200;
    private BankOperationChain nextChainOperation;

    public void setNextChain(BankOperationChain dispenseChain) {
        this.nextChainOperation = dispenseChain;

    }

    public void dispense(Payment payment) {
        double amount = payment.getBankAccount();

        if (amount >= 200) {
            int numberDenomination = (int) (amount / UAH_200_DISPENSE);
            LOG.info("Dispense " + numberDenomination + " of 200 UAH");
            int result = (int) (amount % UAH_200_DISPENSE);
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
