package com.lidiabazhenova.webapp.service;

import com.lidiabazhenova.webapp.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private static List<Product> products = new ArrayList();

    static {
        products.add(new Product("https://oz.by/stationery/more10571950.html?sbtoken=88b118047983de3f728d85deb9507f4b", "канцтовары"));
        products.add(new Product("https://oz.by/stationery/more10254731.html?sbtoken=d4a99efcd221d6c11288ceaf233be8d5", "канцтовары"));
        products.add(new Product("https://oz.by/pens/more10354337.html?sbtoken=41b6d2ce226183997c21ae656e785234", "канцтовары"));
    }

    public List<Product> retrieveProducts() {
        return products;
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public void deleteProduct(Product product){
        products.remove(product);
    }
}
