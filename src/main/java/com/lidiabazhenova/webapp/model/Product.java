package com.lidiabazhenova.webapp.model;

import java.util.Objects;

public class Product {

    private String productUrl;
    private String productName;

    public Product(String productUrl, String productName) {
        this.productUrl = productUrl;
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productUrl, product.productUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productUrl);
    }

    @Override
    public String  toString() {
        final StringBuffer sb = new StringBuffer("Product{");
        sb.append("productUrl='").append(productUrl).append('\'');
        sb.append(", productName='").append(productName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
