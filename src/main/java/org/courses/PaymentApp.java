package org.courses;

import org.apache.log4j.Logger;
import org.courses.paymentSystem.currency.ATMDispenser;
import org.courses.typePayment.Payment;

public class PaymentApp {
    private static Logger LOG = Logger.getLogger(PaymentApp.class);

    public static void main(String[] args) {
        Payment preferentialClient = new PreferentialClient(1000);
        ATMDispenser atmDispenser = new ATMDispenser(preferentialClient);
        atmDispenser.giveAndCalculate();
        LOG.info(preferentialClient.getBankAccount());
    }


}
