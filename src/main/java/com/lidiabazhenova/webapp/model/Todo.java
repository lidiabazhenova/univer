package com.lidiabazhenova.webapp.model;

import java.util.Objects;

public class Todo {

    private String url;
    private String product;

    public Todo(String url, String product) {
        this.url = url;
        this.product = product;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return Objects.equals(url, todo.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }

    @Override
    public String  toString() {
        final StringBuffer sb = new StringBuffer("Todo{");
        sb.append("url='").append(url).append('\'');
        sb.append(", product='").append(product).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
