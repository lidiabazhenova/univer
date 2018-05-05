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

    public List<Product> retrieveProducts() throws DataSourceException {
        return Collections.unmodifiableList(ProductDao.getInstance().getProducts());
    }

    public void addProduct(final Product product) throws DataSourceException {
        ProductDao.getInstance().insertProduct(product);
    }

    public void deleteProduct(final Long id) throws DataSourceException {
        ProductDao.getInstance().deleteProduct(id);
    }
}
