package com.lidiabazhenova.webapp.dao;

import com.lidiabazhenova.webapp.dao.connection.ConnectionUtil;
import com.lidiabazhenova.webapp.exception.DataSourceException;
import com.lidiabazhenova.webapp.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.List;

public class ProductDao {
    public static final ProductDao HOLDER_INSTANCE = new ProductDao();

    private ProductDao() {
    }

    public static ProductDao getInstance() {
        return HOLDER_INSTANCE;
    }

    private static final String ALL_PRODUCTS_QUERY = "SELECT * FROM products";
    private static final String DELETE_PRODUCT_QUERY = "DELETE FROM products WHERE id = ?";
    private static final String INSERT_PRODUCT_QUERY = "INSERT INTO products(url, name) VALUES(?, ?)";
    //private static final String UPDATE_PRODUCT_QUERY = "UPDATE product SET url = ?, name = ? WHERE id = ?";

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

    public void insertProduct(final Product product) throws DataSourceException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_PRODUCT_QUERY);

            preparedStatement.setString(1, product.getProductUrl());
            preparedStatement.setString(2, product.getProductName());

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

    private Product populateProductsFromResultSet(final ResultSet resultSet) throws SQLException {
        final Product product = new Product.ProductBuilder().setProductId(resultSet.getLong("id")).setProductUrl(resultSet.getString("url"))
                .setProductName(resultSet.getString("name")).build();
        return product;
    }

}
