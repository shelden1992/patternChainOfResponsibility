package org.courses.paymentSystem.currency;

import org.apache.log4j.Logger;
import org.courses.paymentSystem.bankTaxes.BankOperationChain;
import org.courses.typePayment.Payment;

import static java.util.Objects.isNull;

public class UAH100Dispenser implements BankOperationChain {
    public static final Logger LOG = Logger.getLogger(UAH200Dispenser.class);
    private static final int UAH_100_DISPENSE = 100;
    private BankOperationChain nextChainOperation;

    public void setNextChain(BankOperationChain dispenseChain) {
        this.nextChainOperation = dispenseChain;

    }

    public void dispense(Payment payment) {
        double amount = payment.getBankAccount();

        if (amount >= 100) {
            int numberDenomination = (int) (amount / UAH_100_DISPENSE);
            LOG.info("Dispense " + numberDenomination + " of 100 UAH");
            int result = (int) (amount % UAH_100_DISPENSE);
            payment.setBankAccount(result);
            if (result != 0) {
                LOG.info("Such amount is left on the account = " + result);
            }
        }

        if (isNull(nextChainOperation)) {
            LOG.info("You are take all money");
        } else {
            nextChainOperation.dispense(payment);
        }

    }
}
