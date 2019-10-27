package org.courses.typePayment;

import org.courses.paymentSystem.additionalTaxesAndOperation.TaxesForLargeAmountMore100_000;
import org.courses.paymentSystem.bankTaxes.OnePercentBasisOperation;
import org.courses.paymentSystem.bankTaxes.TwoPercentForJuridical;

public class JurdicalPayment extends Payment {


    public JurdicalPayment(double bankAccount) {
        super(bankAccount);
    }

    @Override
    public void takePercentForPayment() {
        OnePercentBasisOperation basisChain = new OnePercentBasisOperation();
        TwoPercentForJuridical secondChain = new TwoPercentForJuridical();
        TaxesForLargeAmountMore100_000 thirdChain = new TaxesForLargeAmountMore100_000();
        basisChain.setNextChain(secondChain);
        secondChain.setNextChain(thirdChain);
        basisChain.dispense(this);
    }
}
