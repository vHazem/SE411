// MainApp.java
package com.example;

import com.example.notifications.*;
import com.example.payments.*;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.*;

public class App {

    private static final Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {

        setupLogger();

        logger.info("System start");

        try {
            // Part 1: Notification System
            NotificationChannel[] channels = new NotificationChannel[]{
                    NotificationFactory.createEmailChannel(),
                    NotificationFactory.createSMSChannel(),
                    NotificationFactory.createPushNotificationChannel()
            };

            String sampleMessage = "Welcome to our system!";

            System.out.println("Sending notifications:");
            for (NotificationChannel channel : channels) {
                channel.sendNotification(sampleMessage);
            }

            // Part 2: Payment Gateway
            List<PaymentMethod> paymentMethods = new ArrayList<>();
            paymentMethods.add(PaymentMethodFactory.createCreditCardPayment());
            paymentMethods.add(PaymentMethodFactory.createPayPalPayment());
            paymentMethods.add(PaymentMethodFactory.createApplePayPayment());

            double amount = 99.99;

            System.out.println("\nProcessing payments:");
            for (PaymentMethod method : paymentMethods) {
                method.processPayment(amount);
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "An exception occurred: ", e);
        } finally {
            logger.info("System stop");
        }
    }

    private static void setupLogger() {
        try {
            LogManager.getLogManager().reset();

            // File handler for logging
            FileHandler fh = new FileHandler("system.log", true);
            fh.setLevel(Level.ALL);
            fh.setFormatter(new SimpleFormatter());

            logger.addHandler(fh);
            logger.setLevel(Level.ALL);

        } catch (IOException e) {
            System.err.println("Failed to setup logger handler.");
            e.printStackTrace();
        }
    }
}
