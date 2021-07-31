package com.minh.shoppingcart.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Product implements Serializable {

    private long id;
    private String name;
    private String manufacturer;
    private long price;
    private String photo;

    public Product(String name, String manufacturer, long price, String photo) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.photo = photo;
    }
}
