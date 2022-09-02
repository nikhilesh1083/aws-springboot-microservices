package com.usecase.basket.entity;

public class ProductVO {
    private String productId;
    private String productType;
    private String productName;
    private String price;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
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
        return "ProductVO{" +
                "productId='" + productId + '\'' +
                ", productType='" + productType + '\'' +
                ", productName='" + productName + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
