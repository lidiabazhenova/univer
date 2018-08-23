package com.lidiabazhenova.webapp.model;

import java.util.Objects;

public class Product {

    private long orderId;
    private long productId;
    private String productUrl;
    private String productName;
    private double productPrice;
    private int productQuantity;

    public Product(ProductBuilder productBuilder) {
        this.orderId = productBuilder.orderId;
        this.productId = productBuilder.productId;
        this.productUrl = productBuilder.productUrl;
        this.productName = productBuilder.productName;
        this.productPrice = productBuilder.productPrice;
        this.productQuantity = productBuilder.productQuantity;
    }

    public long getOrderId() {
        return orderId;
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

    public int getProductQuantity() {
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
        sb.append("orderId=").append(orderId);
        sb.append("productId=").append(productId);
        sb.append(", productUrl='").append(productUrl).append('\'');
        sb.append(", productName='").append(productName).append('\'');
        sb.append(", productPrice=").append(productPrice);
        sb.append(", productQuantity =").append(productQuantity);
        sb.append('}');
        return sb.toString();
    }

    public static class ProductBuilder {
        private long orderId;
        private long productId;
        private String productUrl;
        private String productName;
        private double productPrice;
        private int productQuantity;

        public ProductBuilder setOrderId(long orderId) {
            this.orderId = orderId;
            return this;
        }

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

        public ProductBuilder setProductQuantity(int productQuantity) {
            this.productQuantity = productQuantity;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
