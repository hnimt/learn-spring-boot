package com.minh.shoppingcart.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class OrderLine implements Serializable {
    private static final long serialVersionUID = 8053726206598492720L;

    private Product product;
    private int count;
    public void increaseByOne() {
        count += 1;
    }
    public void decreaseByOne() {
        count -= 1;
    }
}
