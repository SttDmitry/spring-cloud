package com.flamexander.cloud.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartControllerImpl implements CartController{

    public CartControllerImpl(){
    }

    @Value("${spring.application.name}")
    private String appName;

    @Autowired
    ShoppingCartService shoppingCartService;

    @Override
    public String save(ShoppingCart cart) {
        shoppingCartService.saveCart(cart);
        return null;
    }
}
