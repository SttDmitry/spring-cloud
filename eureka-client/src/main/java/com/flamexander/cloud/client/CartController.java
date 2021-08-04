package com.flamexander.cloud.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public interface CartController {
    @RequestMapping("/save/")
    String save(ShoppingCart cart);
}
