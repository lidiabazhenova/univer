package com.lidiabazhenova.webapp.model;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class Product {

    private long productId;
    private String productUrl;
    private String productName;
    private double productPrice;
    private double productQuantity;


    public Product(ProductBuilder productBuilder) {
        this.productId = productBuilder.productId;
        this.productUrl = productBuilder.productUrl;
        this.productName = productBuilder.productName;
        this.productPrice = productBuilder.productPrice;
        this.productQuantity = productBuilder.productQuantity;
    }

    public long getProductId() {
        return productId;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public String getProductName() {
        return productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public double getProductQuantity() {
        return productQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productId, product.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Product{");
        sb.append("productId=").append(productId);
        sb.append(", productUrl='").append(productUrl).append('\'');
        sb.append(", productName='").append(productName).append('\'');
        sb.append(", productPrice=").append(productPrice);
        sb.append(", productQuantity =").append(productQuantity);
        sb.append('}');
        return sb.toString();
    }

    public static class ProductBuilder {
        private long productId;
        private String productUrl;
        private String productName;
        private double productPrice;
        private double productQuantity;

        public ProductBuilder setProductId(long id) {
            this.productId = id;
            return this;
        }

        public ProductBuilder setProductUrl(String productUrl) {
            this.productUrl = productUrl;
            return this;
        }

        public ProductBuilder setProductName(String productName) {
            this.productName = productName;
            return this;
        }

        public ProductBuilder setProductPrice(double productPrice) {
            this.productPrice = productPrice;
            return this;
        }

        public ProductBuilder setProductQuantity(double productQuantity) {
            this.productQuantity = productQuantity;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }

    //TODO validateFormatFields;

    public boolean validateNotBlank() {
        if ((StringUtils.isNotBlank(productUrl))&&(StringUtils.isNotBlank(String.valueOf(productPrice)))&&(StringUtils.isNotBlank(String.valueOf(productQuantity)))) {
            return true;
        } else return false;
    }

}
