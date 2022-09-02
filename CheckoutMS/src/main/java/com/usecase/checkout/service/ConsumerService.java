package com.usecase.checkout.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlResponse;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Service
public class ConsumerService {

    @Autowired
    SqsClient sqsClient;

    private String getUrl() {
        GetQueueUrlResponse getQueueUrlResponse = sqsClient
                .getQueueUrl(GetQueueUrlRequest.builder()
                        .queueName("checkout-queue")
                        .build());
        return getQueueUrlResponse.queueUrl();
    }


    public String addMessageTOQueue(String message) {
        return sqsClient.sendMessage(SendMessageRequest.builder()
                .queueUrl(getUrl())
                .messageBody(message)
                .build()).messageId();
    }
}
