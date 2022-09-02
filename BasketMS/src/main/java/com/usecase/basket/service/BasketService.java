package com.usecase.basket.service;

import com.usecase.basket.config.ProductClientConfig;
import com.usecase.basket.entity.Basket;
import com.usecase.basket.entity.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class BasketService {
    @Autowired
    DynamoService service;

    @Autowired
    ProductClientConfig productClientConfig;

    public String addProducts(String productId) {
        ProductVO productVO = getProductDetails(productId);
        if (productVO == null)
            return "fallback";
        Basket basket = setBasketDetails(productVO);
        service.injectDynamoItem(basket);
        return basket.getBasketId();
    }

    private Basket setBasketDetails(ProductVO productVO) {
        Basket basket = new Basket();
        basket.setBasketId(String.valueOf(new Random().nextInt(1000)));
        basket.setProductId(productVO.getProductId());
        basket.setProductName(productVO.getProductName());
        basket.setPrice(productVO.getPrice());
        return basket;
    }

    private ProductVO getProductDetails(String productId) {
        System.out.println(" Making a request to product at :" + LocalDateTime.now());
        return productClientConfig.getProduct(productId);
    }

    public Basket getBasketDetails(String basketId) {
        return service.getBasketDetails(basketId);
    }

}
