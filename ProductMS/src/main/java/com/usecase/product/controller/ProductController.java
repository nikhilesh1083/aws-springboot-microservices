package com.usecase.product.controller;

import com.usecase.product.entity.Product;
import com.usecase.product.service.DynamoDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    DynamoDBService dynamoDBService;

    @Value("#{${rating.map}}")
    Map<String, String> ratingMap;

    @GetMapping(value = "/getAll")
    public List<Product> getAllProducts() {
        return dynamoDBService.getAllProductsList();
    }

    @GetMapping(value = "/test")
    public Product get() throws InterruptedException {
        Product product = new Product();
        product.setProductId("12367555");
        product.setProductName("hello_test");
        return product;
    }

    @GetMapping(value = "/getProduct/{productId}")
    public Product getProduct(@PathVariable(value = "productId") String productId) {
        return dynamoDBService.getProductDetails(productId);
    }

}
