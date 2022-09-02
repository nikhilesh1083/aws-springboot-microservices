package com.usecase.basket.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.usecase.basket.entity.Basket;
import com.usecase.basket.entity.ProductVO;
import com.usecase.basket.service.BasketService;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/basket")
public class BasketController {
    @Autowired
    BasketService basketService;

    @PostMapping(value = "/addProducts")
    public String addProducts(@RequestBody String productId) {
        return basketService.addProducts(productId);
    }

    @GetMapping("/getBasketById/{basketId}")
    @ResponseStatus(HttpStatus.OK)
    public Basket getBasketById(@PathVariable String basketId) {
        return basketService.getBasketDetails(basketId);
    }

    @GetMapping(value = "/testVO")
    public Basket getBasketVO() {
        Basket basket = new Basket();
        basket.setBasketId("123");
        basket.setPrice("13222");
        basket.setProductName("Ipone");
        basket.setProductId("1243");
        return basket;
    }
}
