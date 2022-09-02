package com.usecase.checkout.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.usecase.checkout.config.BasketClientConfig;
import com.usecase.checkout.entity.BasketVO;
import com.usecase.checkout.entity.DeliveryDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BasketService {
    @Autowired
    BasketClientConfig clientConfig;

    @Autowired
    ConsumerService consumerService;

    public String processCheckOut(String basketId, DeliveryDetails details) {
        BasketVO basketVO = getBasketDetails(basketId);
        if (basketVO == null)
            return "fallback";
        else {
            consumerService.addMessageTOQueue(convertToString(basketVO));
            consumerService.addMessageTOQueue(convertToString(details));
            return "Order Placed Successfully";
        }
    }

    private String convertToString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private BasketVO getBasketDetails(String basketId) {
        return clientConfig.getBasketById(basketId);
    }
}
