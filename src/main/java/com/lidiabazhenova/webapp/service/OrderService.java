package com.lidiabazhenova.webapp.service;

import com.lidiabazhenova.webapp.dao.OrderDao;
import com.lidiabazhenova.webapp.dao.ProductDao;
import com.lidiabazhenova.webapp.exception.DataSourceException;
import com.lidiabazhenova.webapp.model.Order;

import java.util.Collections;
import java.util.List;

public class OrderService {
    public static final OrderService HOLDER_INSTANCE = new OrderService();

    private OrderService() {
    }

    public static OrderService getInstance() {
        return HOLDER_INSTANCE;
    }

    public List<Order> getOrders() throws DataSourceException {
        return Collections.unmodifiableList(OrderDao.getInstance().getOrders());
    }

    public Order getOrder(final long id) throws DataSourceException {
        return OrderDao.getInstance().getOrder(id);
    }

    public void addOrder(final Order Order) throws DataSourceException {
        OrderDao.getInstance().insertOrder(Order);
    }

    public void deleteOrder(final Long id) throws DataSourceException {
        ProductDao.getInstance().deleteProductsByOrderId(id);
        OrderDao.getInstance().deleteOrder(id);
    }

    public void updateOrder(final Order order) throws DataSourceException {
        OrderDao.getInstance().updateOrder(order);
    }
}
