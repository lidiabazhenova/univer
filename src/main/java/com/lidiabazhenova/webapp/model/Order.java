package com.lidiabazhenova.webapp.model;

import java.util.Objects;

public class Order {

    private long orderId;
    private String orderTitle;

    public Order(OrderBuilder orderBuilder) {
        this.orderId = orderBuilder.orderId;
        this.orderTitle = orderBuilder.orderTitle;
    }

    public long getOrderId() {
        return orderId;
    }

    public String getOrderTitle() {
        return orderTitle;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Order{");
        sb.append("orderId=").append(orderId);
        sb.append(", orderTitle='").append(orderTitle).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }

    public static class OrderBuilder {
        private long orderId;
        private String orderTitle;

        public OrderBuilder setOrderId(long orderId) {
            this.orderId = orderId;
            return this;
        }

        public OrderBuilder setOrderTitle(String orderTitle) {
            this.orderTitle = orderTitle;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
