package com.usecase.checkout.config;

import com.usecase.checkout.entity.BasketVO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "basket", configuration = FeignClientConfiguration.class)
public interface BasketClientConfig {
    String SERVICE_NAME = "myCircuitBreaker";
    String RETRY_SERVICE = "myRetry";

    @CircuitBreaker(name = SERVICE_NAME)
    @Retry(name = RETRY_SERVICE)
    @GetMapping("/api/v1/basket/getBasketById/{basketId}")
    BasketVO getBasketById(@PathVariable String basketId);

    @CircuitBreaker(name = SERVICE_NAME)
    @Retry(name = RETRY_SERVICE)
    @GetMapping("/api/v1/basket/testVO")
    BasketVO getBasketVO();
}
