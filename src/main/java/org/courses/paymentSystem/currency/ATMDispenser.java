package org.courses.paymentSystem.currency;

import org.apache.log4j.Logger;
import org.courses.paymentSystem.bankTaxes.BankOperationChain;
import org.courses.typePayment.Payment;

import java.util.Scanner;

public class ATMDispenser {
    private static final Logger LOG = Logger.getLogger(ATMDispenser.class);
    private static UAH500Dispenser uah500Dispenser;
    private Payment payment;


    public ATMDispenser(Payment payment) {
        this.payment = payment;
        uah500Dispenser = new UAH500Dispenser();
        BankOperationChain uah200Dispenser = new UAH200Dispenser();
        BankOperationChain UAH100Dispenser = new UAH100Dispenser();
        uah500Dispenser.setNextChain(uah200Dispenser);
        uah200Dispenser.setNextChain(UAH100Dispenser);
    }

    public void giveAndCalculate() {
        double bankAccountBeforePercent = payment.getBankAccount();
        payment.takePercentForPayment();


        int amount = 0;
        double bankAccountAfterPercent = payment.getBankAccount();

        LOG.info("Enter amount to dispense");
        Scanner input = new Scanner(System.in);
        amount = input.nextInt();

        if (amount % 100 != 0) {
            LOG.info("Amount should be in multiple of 100");
        } else if (amount > bankAccountAfterPercent) {
            LOG.info("Sorry you can take off only " + bankAccountAfterPercent);

        } else {
            payment.setBankAccount(amount);
            uah500Dispenser.dispense(payment);
            payment.setBankAccount(bankAccountAfterPercent - amount);
            return;

        }
        payment.setBankAccount(bankAccountBeforePercent);


    }


}
