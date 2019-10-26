package org.courses.typePayment;


import org.courses.paymentSystem.additionalTaxesAndOperation.TaxesForLargeAmountMore100_000;
import org.courses.paymentSystem.bankTaxes.HalfPercentForPreferential;

public class PreferentialPayment extends Payment {


    public PreferentialPayment(double bankAccount) {
        super(bankAccount);
    }

    @Override
    public void takePercentForPayment() {
        HalfPercentForPreferential firstChain = new HalfPercentForPreferential();
        TaxesForLargeAmountMore100_000 thirdChain = new TaxesForLargeAmountMore100_000();
        firstChain.setNextChain(thirdChain);
        firstChain.dispense(this);
    }


}
