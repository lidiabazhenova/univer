package com.lidiabazhenova.webapp.dao;

import com.lidiabazhenova.webapp.dao.connection.ConnectionUtil;
import com.lidiabazhenova.webapp.exception.DataSourceException;
import com.lidiabazhenova.webapp.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    public static final ProductDao HOLDER_INSTANCE = new ProductDao();

    private static final String ALL_PRODUCTS_QUERY = "SELECT * FROM products";
    private static final String GET_PRODUCTS_BY_ORDER_ID_QUERY = "SELECT * FROM products WHERE order_id = ?";
    private static final String GET_PRODUCT_QUERY = "SELECT * FROM products WHERE product_id = ?";
    private static final String DELETE_PRODUCT_QUERY = "DELETE FROM products WHERE product_id = ?";
    private static final String INSERT_PRODUCT_QUERY = "INSERT INTO products(order_id, url, name, price, quantity) VALUES(?, ?, ?, ?, ?)";
    private static final String UPDATE_PRODUCT_QUERY = "UPDATE products SET url = ?, name = ?, price = ?, quantity = ? WHERE product_id = ?";
    private static final String DELETE_PRODUCTS_BY_ORDER_ID_QUERY = "DELETE FROM products WHERE order_id = ?";

    private ProductDao() {
    }

    public static ProductDao getInstance() {
        return HOLDER_INSTANCE;
    }

    public List<Product> getProducts() throws DataSourceException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(ALL_PRODUCTS_QUERY);
            resultSet = preparedStatement.executeQuery();

            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                products.add(populateProductsFromResultSet(resultSet));
            }

            return products;

        } catch (final SQLException ex) {
            throw new DataSourceException(ex);
        } finally {
            ConnectionUtil.close(resultSet, preparedStatement, connection);
        }
    }

    public List<Product> getProductsByOrderId(final long id) throws DataSourceException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_PRODUCTS_BY_ORDER_ID_QUERY );
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                products.add(populateProductsFromResultSet(resultSet));
            }

            return products;

        } catch (final SQLException ex) {
            throw new DataSourceException(ex);
        } finally {
            ConnectionUtil.close(resultSet, preparedStatement, connection);
        }
    }

    public Product getProduct(final long id) throws DataSourceException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_PRODUCT_QUERY);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return populateProductsFromResultSet(resultSet);
            } else {
                return null;
            }

        } catch (final SQLException ex) {
            throw new DataSourceException(ex);
        } finally {
            ConnectionUtil.close(resultSet, preparedStatement, connection);
        }
    }

    public void insertProduct(final Product product) throws DataSourceException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_PRODUCT_QUERY);
            //TODO max length url
            preparedStatement.setLong(1, product.getOrderId());
            preparedStatement.setString(2, product.getProductUrl());
            preparedStatement.setString(3, product.getProductName());
            preparedStatement.setDouble(4, product.getProductPrice());
            preparedStatement.setDouble(5, product.getProductQuantity());

            preparedStatement.executeUpdate();
        } catch (final SQLException ex) {
            throw new DataSourceException(ex);
        } finally {
            ConnectionUtil.close(preparedStatement, connection);
        }
    }

    public void deleteProduct(final long id) throws DataSourceException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_PRODUCT_QUERY);

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (final SQLException ex) {
            throw new DataSourceException(ex);
        } finally {
            ConnectionUtil.close(preparedStatement, connection);
        }
    }

    public void updateProduct(final Product product) throws DataSourceException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_QUERY);

            preparedStatement.setString(1, product.getProductUrl());
            preparedStatement.setString(2, product.getProductName());
            preparedStatement.setDouble(3, product.getProductPrice());
            preparedStatement.setDouble(4, product.getProductQuantity());
            preparedStatement.setLong(5, product.getProductId());

            preparedStatement.executeUpdate();
        } catch (final SQLException ex) {
            throw new DataSourceException(ex);
        } finally {
            ConnectionUtil.close(preparedStatement, connection);
        }
    }

    public void deleteProductsByOrderId(final long id) throws DataSourceException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_PRODUCTS_BY_ORDER_ID_QUERY);

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (final SQLException ex) {
            throw new DataSourceException(ex);
        } finally {
            ConnectionUtil.close(preparedStatement, connection);
        }
    }

    private Product populateProductsFromResultSet(final ResultSet resultSet) throws SQLException {
        final Product product = new Product.ProductBuilder()
                .setOrderId(resultSet.getLong("order_id"))
                .setProductId(resultSet.getLong("product_id"))
                .setProductUrl(resultSet.getString("url"))
                .setProductName(resultSet.getString("name"))
                .setProductPrice(resultSet.getDouble("price"))
                .setProductQuantity(resultSet.getInt("quantity"))
                .build();
        return product;
    }
}
