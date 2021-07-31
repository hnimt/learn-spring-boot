package com.minh.shoppingcart.controller;

import com.minh.shoppingcart.model.Cart;
import com.minh.shoppingcart.model.Customer;
import com.minh.shoppingcart.service.CartService;
import com.minh.shoppingcart.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class MailController {
    @Autowired
    MailService mailService;
    @Autowired
    CartService cartService;

    @PostMapping("/sendmail")
    public String sendMail(HttpSession session, Model model,
                           @ModelAttribute("customer") Customer customer,
                           BindingResult result) {
        if(!result.hasErrors()) {
            Cart cart = cartService.getCart(session);
            mailService.sendMail(customer, cart);
        }
        return "payment";

    }
}
