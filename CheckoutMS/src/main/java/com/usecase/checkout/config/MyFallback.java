package com.usecase.checkout.config;

import com.usecase.checkout.entity.BasketVO;
import org.springframework.stereotype.Component;

@Component
public class MyFallback implements BasketClientConfig {

    @Override
    public BasketVO getBasketById(String basketId) {
        return null;
    }

    @Override
    public BasketVO getBasketVO() {
        return null;
    }
}
