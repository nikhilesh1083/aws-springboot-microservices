package com.usecase.checkout.controller;

import com.usecase.checkout.entity.DeliveryDetails;
import com.usecase.checkout.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/checkout")
public class CheckoutController {
    @Autowired
    BasketService service;

    @PostMapping(value = "/createCheckout")
    @ResponseStatus(HttpStatus.CREATED)
    public String createCheckout(@RequestBody DeliveryDetails details,
                                 @RequestParam("basketId") String basketId) {
        return service.processCheckOut(basketId, details);
    }
}
