package com.flamexander.cloud.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class ShoppingCartService {
    private OrderItemRepository orderItemRepository;

    @Autowired
    public void setOrderItemRepository(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public ShoppingCart getCurrentCart(HttpSession session) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("cart", cart);
        }
        return cart;
    }


    public void saveCart(ShoppingCart cart) {
        User user = new User();
        user.setId(1L);
        user.setUserName("Alex");
        user.setPassword("123");
        user.setFirstName("Alex");
        user.setLastName("Alex");
        user.setEmail("111@gmail.com");
        user.setPhone("888");
        for (OrderItem oi: cart.getItems()) {
            oi.setUser(user);
            orderItemRepository.save(oi);
        }
    }
}
