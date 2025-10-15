// CreditCardPayment.java
package com.example.payments;

import com.example.payments.PaymentMethod;

public class CreditCardPayment implements PaymentMethod {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing Credit Card payment of $" + amount);
    }
}
