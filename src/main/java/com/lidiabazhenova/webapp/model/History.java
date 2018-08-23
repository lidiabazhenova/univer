package com.lidiabazhenova.webapp.model;

import java.util.Date;
import java.util.Objects;

public class History {

    private String description;
    private long orderId;
    private Date date;

    public History(HistoryBuilder historyBuilder) {
        this.description = historyBuilder.description;
        this.orderId = historyBuilder.orderId;
        this.date = historyBuilder.date;
    }

    public String getDescription() {
        return description;
    }

    public long getOrderId() {
        return orderId;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        History history = (History) o;
        return orderId == history.orderId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("History{");
        sb.append("description='").append(description).append('\'');
        sb.append(", orderId=").append(orderId);
        sb.append(", date=").append(date);
        sb.append('}');
        return sb.toString();
    }

    public static class HistoryBuilder {
        private String description;
        private long orderId;
        private Date date;

        public HistoryBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public HistoryBuilder setOrderId(long orderId) {
            this.orderId = orderId;
            return this;
        }

        public HistoryBuilder setDate(Date date) {
            this.date = date;
            return this;
        }

        public History build() {
            return new History(this);
        }
    }
}
