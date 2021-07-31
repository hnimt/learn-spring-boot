package com.minh.shoppingcart.controller;

import com.minh.shoppingcart.model.Customer;
import com.minh.shoppingcart.repository.ProductRepository;
import com.minh.shoppingcart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class ProductController {
    @Autowired private ProductRepository productRepository;
    @Autowired private CartService cartService;

    @GetMapping("/")
    public String showHomePage(HttpSession session, Model model) {
        model.addAttribute("products", productRepository.getAll());
        model.addAttribute("cartCount", cartService.countItemInCart(session));
        return "index";
    }

    @GetMapping("/buy/{id}")
    public String buy(@PathVariable(name = "id") Long id, HttpSession session, Model model) {
        cartService.addToCart(id, session);
        return "redirect:/";
    }

    @GetMapping("/add/{id}")
    public String add(@PathVariable(name = "id") Long id, HttpSession session, Model model) {
        cartService.addToCart(id, session);
        return "redirect:/checkout";
    }

    @GetMapping("/reduce/{id}")
    public String reduce(@PathVariable(name = "id") Long id, HttpSession session, Model model) {
        cartService.reduceToCart(id, session);
        return "redirect:/checkout";
    }

    @GetMapping("/checkout")
    public String checkout(HttpSession session, Model model) {
        model.addAttribute("cart", cartService.getCart(session));
        model.addAttribute("cartCount", cartService.countItemInCart(session));
        return "checkout";
    }

    @GetMapping("/payment")
    public String payment(HttpSession session, Model model) {
        model.addAttribute("cart", cartService.getCart(session));
        model.addAttribute("cartCount", cartService.countItemInCart(session));
        model.addAttribute("customer", new Customer());
        return "payment";
    }

    @PostMapping("/payment")
    public String paymentPost(HttpSession session, Model model,
                              @ModelAttribute("customer") Customer customer,
                              BindingResult result) {
        if(!result.hasErrors()) {
            
            return "redirect:/payment/success";
        }
        return "payment";
    }

    @GetMapping("/payment/success")
    public String paymentSuccess(Model model) {
        return "success";
    }
}
