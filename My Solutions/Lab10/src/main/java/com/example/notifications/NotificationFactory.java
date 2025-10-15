// NotificationFactory.java
package com.example.notifications;

public class NotificationFactory {
    public static NotificationChannel createEmailChannel() {
        return new EmailChannel();
    }

    public static NotificationChannel createSMSChannel() {
        return new SMSChannel();
    }

    public static NotificationChannel createPushNotificationChannel() {
        return new PushNotificationChannel();
    }
}
