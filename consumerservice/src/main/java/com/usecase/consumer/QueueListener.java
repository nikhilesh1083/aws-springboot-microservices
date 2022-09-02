package com.usecase.consumer;

import io.awspring.cloud.messaging.core.NotificationMessagingTemplate;
import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class QueueListener {
    @Value("${topicName}")
    String topicName;

    private final NotificationMessagingTemplate notificationMessagingTemplate;

    public QueueListener(NotificationMessagingTemplate notificationMessagingTemplate) {
        this.notificationMessagingTemplate = notificationMessagingTemplate;
    }

    @SqsListener(value = "${custom.sqs-queue-name}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    private void receiveMessage(String message) {
        System.out.println("Queue Message: " + message);
        if (message.contains("basketId"))
            this.notificationMessagingTemplate.convertAndSend(topicName, message);
    }
}
