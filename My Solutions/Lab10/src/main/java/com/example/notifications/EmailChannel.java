// EmailChannel.java
package com.example.notifications;

public class EmailChannel extends NotificationChannel {
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending Email notification: " + message);
    }
}
