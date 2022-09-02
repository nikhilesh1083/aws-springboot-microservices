package com.usecase.basket.config;

import com.usecase.basket.entity.ProductVO;
import org.springframework.stereotype.Component;

@Component
public class MyFallback implements ProductClientConfig {

    @Override
    public ProductVO getProduct(String productId) {
        return null;
    }

    @Override
    public ProductVO get() {
        return null;
    }
}
