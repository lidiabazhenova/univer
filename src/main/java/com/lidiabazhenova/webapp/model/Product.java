package com.lidiabazhenova.webapp.model;

import java.util.Objects;

public class Product {

    private String productUrl;
    private String productName;

    public Product(ProductBuilder productBuilder) {
        this.productUrl = productBuilder.productUrl;
        this.productName = productBuilder.productName;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public String getProductName() {
        return productName;
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
    public String toString() {
        final StringBuffer sb = new StringBuffer("Product{");
        sb.append("productUrl='").append(productUrl).append('\'');
        sb.append(", productName='").append(productName).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static class ProductBuilder {
        private String productUrl;
        private String productName;

        public ProductBuilder setProductUrl(String productUrl) {
            this.productUrl = productUrl;
            return this;
        }

        public ProductBuilder setProductName(String productName) {
            this.productName = productName;
            return this;
        }

        public Product build() {
            validateRequiredFields();

            return new Product(this);
        }

        protected void validateRequiredFields() {
//            if (productUrl == null) {
//                throw new NullPointerException("Please, enter url");
//            }
//
//            if (productName == null) {
//                throw new NullPointerException("Please, enter name");
//            }
        }
    }

}
