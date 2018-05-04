package com.lidiabazhenova.webapp.model;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class ListOfProducts {

    private String productUrl;
    private String productName;

    public ListOfProducts(ProductBuilder productBuilder) {
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
        ListOfProducts product = (ListOfProducts) o;
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

        public ListOfProducts build() {
            //validateRequiredFields();

            return new ListOfProducts(this);
        }
    }

    public boolean validateRequiredFields() {
        if ((StringUtils.isNotBlank(productUrl)) && (StringUtils.isNotBlank(productName))) {
            return true;
        } else return false;
    }

}
