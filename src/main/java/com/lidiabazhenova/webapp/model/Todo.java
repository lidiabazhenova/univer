package com.lidiabazhenova.webapp.model;

import java.util.Objects;

public class Todo {

    private String name;
    private String product;

    public Todo(String name, String product) {
        this.name = name;
        this.product = product;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return Objects.equals(name, todo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String  toString() {
        final StringBuffer sb = new StringBuffer("Todo{");
        sb.append("name='").append(name).append('\'');
        sb.append(", product='").append(product).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
