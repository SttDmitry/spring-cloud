package my.market.controllers;

import my.market.entities.Greeting;
import my.market.entities.Message;
import my.market.entities.Product;
import my.market.services.ProductService;
import my.market.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class ShopControllerWs {

    private ShoppingCartService shoppingCartService;

    @Autowired
    public void setShoppingCartService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

//    @MessageMapping("/hello")
//    @SendTo("/topic/greetings")
//    public Greeting greeting(Message message, HttpServletRequest httpServletRequest) throws Exception {
////        Thread.sleep(1000); // simulated delay
//        return new Greeting( shoppingCartService.getCurrentCart(httpServletRequest.getSession()).getItems().size()+" товар(а/ов)");
//    }
}