package com.minh.shoppingcart.service;

import com.minh.shoppingcart.model.Cart;
import com.minh.shoppingcart.model.OrderLine;
import com.minh.shoppingcart.model.Product;
import com.minh.shoppingcart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private ProductRepository productRepository;

    public void addToCart(Long id, HttpSession session) {
        HashMap<Long, OrderLine> cart;
        var rawCart = session.getAttribute("CART");

        if (rawCart instanceof HashMap) {
            cart = (HashMap<Long, OrderLine>) rawCart;
        } else {
            cart = new HashMap<>();
        }

        Optional<Product> product= productRepository.findById(id);
        if(product.isPresent()){
            OrderLine orderLine = cart.get(id);
            if(orderLine == null) {
                cart.put(id, new OrderLine(product.get(), 1));

            } else {
                orderLine.increaseByOne();
                cart.put(id, orderLine);
            }
        }

        session.setAttribute("CART", cart);
    }

    public void reduceToCart(Long id, HttpSession session) {
        HashMap<Long, OrderLine> cart;
        var rawCart = session.getAttribute("CART");
        cart = (HashMap<Long, OrderLine>) rawCart;

        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            OrderLine orderLine = cart.get(id);
            orderLine.decreaseByOne();

            if (orderLine.getCount() == 0) {
                cart.remove(id);
            } else {
                cart.put(id, orderLine);
            }
        }

        session.setAttribute("CART", cart);
    }

    public int countItemInCart(HttpSession session) {
        HashMap<Long, OrderLine> cart;
        var rawCart = session.getAttribute("CART");
        if(rawCart instanceof HashMap) {
            cart = (HashMap<Long, OrderLine>) rawCart;
            return cart.values().stream().mapToInt(OrderLine::getCount).sum();

        } else {
            return 0;
        }
    }

    public Cart getCart(HttpSession session) {
        HashMap<Long, OrderLine> cart;
        var rawCart = session.getAttribute("CART");
        if(rawCart instanceof HashMap) {
            cart = (HashMap<Long, OrderLine>) rawCart;

            return new Cart(
                    cart.values().stream().toList(),
                    0.01f,
                    true
            );
        } else {
            return new Cart();
        }
    }
}
