package com.lidiabazhenova.webapp.model;

import java.util.List;

public class ShoppingList {

    private long listId;
    private String listTitle;
    private List<Product> products;


    public ShoppingList(long id, String title, List<Product> products) {
        this.listId = id;
        this.listTitle = title;
        this.products = products;
    }


    public long getListId() {
        return listId;
    }

    public String getListTitle() {
        return listTitle;
    }

    public List<Product> getProducts() {
        return products;
    }
}
