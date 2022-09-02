package com.usecase.basket.config;

import feign.Feign;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.feign.FeignDecorators;
import io.github.resilience4j.feign.Resilience4jFeign;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

public class FeignClientConfiguration {
    private final CircuitBreakerRegistry registry;
    private final MyFallback fallbackProducerClient;
    private final RetryRegistry retryRegistry;

    public FeignClientConfiguration(CircuitBreakerRegistry registry, MyFallback fallbackProducerClient, RetryRegistry retryRegistry) {
        this.registry = registry;
        this.fallbackProducerClient = fallbackProducerClient;
        this.retryRegistry = retryRegistry;
    }

    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder() {
        CircuitBreaker circuitBreaker = registry.circuitBreaker(ProductClientConfig.SERVICE_NAME);
        Retry retry = retryRegistry.retry(ProductClientConfig.RETRY_SERVICE);
        FeignDecorators decorators = FeignDecorators.builder()
                .withCircuitBreaker(circuitBreaker)
                .withRetry(retry)
                .withFallback(fallbackProducerClient)
                .build();
        return Resilience4jFeign.builder(decorators);
    }

}
