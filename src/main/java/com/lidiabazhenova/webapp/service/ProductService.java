package com.lidiabazhenova.webapp.service;

import com.lidiabazhenova.webapp.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductService {
    private static List<Product> products;

    public ProductService() {
        products = new ArrayList();
        products.add(new Product.ProductBuilder()
                .setProductUrl("https://oz.by/stationery/more10254731.html?sbtoken=d4a99efcd221d6c11288ceaf233be8d5")
                .setProductName("канцтовары").build());
        products.add(new Product.ProductBuilder()
                .setProductUrl("https://oz.by/pens/more10354337.html?sbtoken=41b6d2ce226183997c21ae656e785234")
                .setProductName("канцтовары").build());
    }

    public List<Product> retrieveProducts() {
        return Collections.unmodifiableList(products);
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void deleteProduct(Product product) {
        products.remove(product);
    }
}
