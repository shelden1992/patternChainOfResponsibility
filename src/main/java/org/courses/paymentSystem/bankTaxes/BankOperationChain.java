package org.courses.paymentSystem.bankTaxes;

import org.courses.typePayment.Payment;

public interface BankOperationChain {
    void setNextChain(BankOperationChain bankOperationChain);

    void dispense(Payment payment);
}
