// SMSChannel.java
package com.example.notifications;

public class SMSChannel extends NotificationChannel {
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending SMS notification: " + message);
    }
}
