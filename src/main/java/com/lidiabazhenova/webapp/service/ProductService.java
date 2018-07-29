package com.lidiabazhenova.webapp.service;

import com.lidiabazhenova.webapp.dao.ProductDao;
import com.lidiabazhenova.webapp.exception.DataSourceException;
import com.lidiabazhenova.webapp.model.Product;

import java.util.Collections;
import java.util.List;

public class ProductService {
    public static final ProductService HOLDER_INSTANCE = new ProductService();

    private ProductService() {
    }

    public static ProductService getInstance() {
        return HOLDER_INSTANCE;
    }

    public List<Product> getProducts() throws DataSourceException {
        return Collections.unmodifiableList(ProductDao.getInstance().getProducts());
    }

    public List<Product> getProductsByOrderId(final long id) throws DataSourceException {
        return Collections.unmodifiableList(ProductDao.getInstance().getProductsByOrderId(id));
    }

    public Product getProduct(final long id) throws DataSourceException {
        return ProductDao.getInstance().getProduct(id);
    }

    public void addProduct(final Product product) throws DataSourceException {
        ProductDao.getInstance().insertProduct(product);
    }

    public void deleteProduct(final Long id) throws DataSourceException {
        ProductDao.getInstance().deleteProduct(id);
    }

    public void updateProduct(final Product product) throws DataSourceException {
        ProductDao.getInstance().updateProduct(product);
    }
}
