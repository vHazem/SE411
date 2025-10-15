// PushNotificationChannel.java
package com.example.notifications;

public class PushNotificationChannel extends NotificationChannel {
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending Push notification: " + message);
    }
}
