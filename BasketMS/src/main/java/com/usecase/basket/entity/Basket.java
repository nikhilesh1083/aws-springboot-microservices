package com.usecase.basket.entity;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class Basket {
    private String basketId;
    private String productId;
    private String productName;
    private String price;

    public String getBasketId() {
        return basketId;
    }

    @DynamoDbPartitionKey
    public void setBasketId(String basketId) {
        this.basketId = basketId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Basket{" +
                "basketId='" + basketId + '\'' +
                ", productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
