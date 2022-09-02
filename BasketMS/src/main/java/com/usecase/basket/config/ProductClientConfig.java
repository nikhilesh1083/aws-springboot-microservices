package com.usecase.basket.config;

import com.usecase.basket.entity.ProductVO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product", configuration = FeignClientConfiguration.class)
public interface ProductClientConfig {
    String SERVICE_NAME = "myCircuitBreaker";
    String RETRY_SERVICE = "myRetry";

    @CircuitBreaker(name = SERVICE_NAME)
    @Retry(name = RETRY_SERVICE)
    @GetMapping("/api/v1/product/getProduct/{productId}")
    ProductVO getProduct(@PathVariable String productId);

    @CircuitBreaker(name = SERVICE_NAME)
    @Retry(name = RETRY_SERVICE)
    @GetMapping("/api/v1/product/test")
    ProductVO get();
}
