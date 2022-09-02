/*
package com.usecase.checkout.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.CreateTopicRequest;
import software.amazon.awssdk.services.sns.model.ListTopicsRequest;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sns.model.SnsException;
import software.amazon.awssdk.services.sns.model.SubscribeRequest;
import software.amazon.awssdk.services.sns.model.Topic;

import java.util.Objects;

@Service
public class MessageService {

    @Autowired
    private SnsClient snsClient;

    public String sendMessage(String message, String mail) {
        ListTopicsRequest listTopicsRequest = ListTopicsRequest.builder()
                .build();
        String topicArn = null;
        Topic sendNotification = snsClient.listTopics(listTopicsRequest).topics().stream().
                filter(s -> s.topicArn().contains("SendNotification")).findAny().orElse(null);
        if (Objects.isNull(sendNotification)) {
            topicArn = createTopic();
            subMailSNS(topicArn, mail);
        } else
            topicArn = sendNotification.topicArn();
        System.out.println("topicArn : " + topicArn);
        try {
            PublishRequest request = PublishRequest.builder().message(message).topicArn(topicArn).build();
            PublishResponse result = snsClient.publish(request);
            System.out.println(result.messageId() + " Message sent. Status was " + result.sdkHttpResponse().statusCode());
        } catch (SnsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
        }
        return "Order placed Successfully";
    }

    private String createTopic() {
        CreateTopicRequest request = CreateTopicRequest.builder()
                .name("SendNotification")
                .build();
        return snsClient.createTopic(request).topicArn();
    }

    public void subMailSNS(String topicArn, String email) {
        SubscribeRequest request = SubscribeRequest.builder()
                .protocol("email")
                .endpoint(email)
                .returnSubscriptionArn(true)
                .topicArn(topicArn)
                .build();
        snsClient.subscribe(request);
    }

}
*/
