package com.minh.shoppingcart.service;

import com.minh.shoppingcart.model.Cart;
import com.minh.shoppingcart.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

@Service
public class PaymentService {
    @Autowired private CartService cartService;
    @Autowired private JavaMailSender emailSender;

    public void sendEmail (String to, HttpSession session, Customer customer) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        boolean multipart = true;
        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");

        Cart cart = cartService.getCart(session);
        long total = cart.getTotal();

        String htmlMsg = "<h1>Dear "+ customer.getName() +"</h1>\n" +
                "  <h2>Your total bill: "+ total +"$</h2>";
        message.setContent(htmlMsg, "text/html");
        helper.setTo(to);
        helper.setSubject("Test send HTML email");

        emailSender.send(message);
    }

    public void testGit() {

    }
}
