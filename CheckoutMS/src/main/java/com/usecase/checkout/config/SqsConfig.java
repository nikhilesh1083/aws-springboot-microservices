package com.usecase.checkout.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

@Configuration
public class SqsConfig {

    @Bean
    public SqsClient sqsClient() {
        Region region = Region.US_EAST_1;
        return SqsClient.builder()
                .region(region).credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                .build();
    }
}
