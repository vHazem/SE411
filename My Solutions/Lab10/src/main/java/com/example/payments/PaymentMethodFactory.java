// PaymentMethodFactory.java
package com.example.payments;

public class PaymentMethodFactory {
    public static PaymentMethod createCreditCardPayment() {
        return new CreditCardPayment();
    }

    public static PaymentMethod createPayPalPayment() {
        return new PayPalPayment();
    }

    public static PaymentMethod createApplePayPayment() {
        return new ApplePayPayment();
    }
}
