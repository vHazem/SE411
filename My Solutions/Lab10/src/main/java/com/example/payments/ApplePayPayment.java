// ApplePayPayment.java
package com.example.payments;

public class ApplePayPayment implements PaymentMethod {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing Apple Pay payment of $" + amount);
    }
}
