package com.renu.assignment2;

public class ProductItem {
    private String productItemName;
    private double productPrice;
    private int productQnt;

    public String getProductItemName() {
        return productItemName;
    }

    public void setProductItemName(String productItemName) {
        this.productItemName = productItemName;
    }

    public double getProductQnt() {
        return productQnt;
    }

    public void setProductQnt(Integer productQnt) {
        this.productQnt = productQnt;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public ProductItem(String productItemName, double productPrice, int productQnt) {
        this.productItemName = productItemName;
        this.productPrice = productPrice;
        this.productQnt = productQnt;


    }
}
