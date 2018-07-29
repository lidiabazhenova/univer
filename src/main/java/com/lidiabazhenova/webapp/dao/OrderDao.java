package com.lidiabazhenova.webapp.dao;

import com.lidiabazhenova.webapp.dao.connection.ConnectionUtil;
import com.lidiabazhenova.webapp.exception.DataSourceException;
import com.lidiabazhenova.webapp.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    public static final OrderDao HOLDER_INSTANCE = new OrderDao();

    private static final String ALL_ORDERS_QUERY = "SELECT * FROM orders";
    private static final String GET_ORDER_QUERY = "SELECT * FROM orders WHERE order_id = ?";
    private static final String GET_ORDER_TITLE_QUERY = "SELECT order_title FROM orders WHERE order_id=?";
    private static final String DELETE_ORDER_QUERY = "DELETE FROM orders WHERE order_id  = ?";
    private static final String INSERT_ORDER_QUERY = "INSERT INTO orders(order_id , order_title) VALUES(?, ?)";
    private static final String UPDATE_ORDER_QUERY = "UPDATE orders SET order_title = ? WHERE order_id = ?";

    private OrderDao() {
    }

    public static OrderDao getInstance() {
        return HOLDER_INSTANCE;
    }

    public List<Order> getOrders() throws DataSourceException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(ALL_ORDERS_QUERY);
            resultSet = preparedStatement.executeQuery();

            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                orders.add(populateOrdersFromResultSet(resultSet));
            }

            return orders;

        } catch (final SQLException ex) {
            throw new DataSourceException(ex);
        } finally {
            ConnectionUtil.close(resultSet, preparedStatement, connection);
        }
    }

    public Order getOrder(final long id) throws DataSourceException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_ORDER_QUERY);

            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return populateOrdersFromResultSet(resultSet);
            } else {
                return null;
            }

        } catch (final SQLException ex) {
            throw new DataSourceException(ex);
        } finally {
            ConnectionUtil.close(resultSet, preparedStatement, connection);
        }
    }

    public void insertOrder(final Order order) throws DataSourceException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_ORDER_QUERY);

            preparedStatement.setLong(1, order.getOrderId());
            preparedStatement.setString(2, order.getOrderTitle());

            preparedStatement.executeUpdate();
        } catch (final SQLException ex) {
            throw new DataSourceException(ex);
        } finally {
            ConnectionUtil.close(preparedStatement, connection);
        }
    }

    public void deleteOrder(final long id) throws DataSourceException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_ORDER_QUERY);

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (final SQLException ex) {
            throw new DataSourceException(ex);
        } finally {
            ConnectionUtil.close(preparedStatement, connection);
        }
    }

    public void updateOrder(final Order order) throws DataSourceException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_ORDER_QUERY);

            preparedStatement.setString(1, order.getOrderTitle());
            preparedStatement.setLong(2, order.getOrderId());

            preparedStatement.executeUpdate();
        } catch (final SQLException ex) {
            throw new DataSourceException(ex);
        } finally {
            ConnectionUtil.close(preparedStatement, connection);
        }
    }

    private Order populateOrdersFromResultSet(final ResultSet resultSet) throws SQLException {
        final Order order = new Order.OrderBuilder()
                .setOrderId(resultSet.getLong("order_id"))
                .setOrderTitle(resultSet.getString("order_title"))
                .build();
        return order;
    }
}
