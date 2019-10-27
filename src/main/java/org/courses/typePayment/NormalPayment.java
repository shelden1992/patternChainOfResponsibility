package org.courses.typePayment;

import org.courses.paymentSystem.additionalTaxesAndOperation.TaxesForLargeAmountMore100_000;
import org.courses.paymentSystem.bankTaxes.OnePercentBasisOperation;

public class NormalPayment extends Payment {

    public NormalPayment(double bankAccount) {
        super(bankAccount);
    }

    @Override
    public void takePercentForPayment() {
        OnePercentBasisOperation basisChain = new OnePercentBasisOperation();
        TaxesForLargeAmountMore100_000 thirdChain = new TaxesForLargeAmountMore100_000();
        basisChain.setNextChain(thirdChain);
        basisChain.dispense(this);
    }
}
