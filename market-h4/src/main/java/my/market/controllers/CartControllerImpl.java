package my.market.controllers;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import my.market.services.ShoppingCartService;
import my.market.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Controller
@RequestMapping("/cart")
public class CartControllerImpl {
    private final static String QUEUE_NAME = "hello";
    private ShoppingCartService shoppingCartService;
    private CloudCartController cloudCartController;

    @Autowired
    public void setCartController(CloudCartController cartController) {
        this.cloudCartController = cartController;
    }

    @Autowired
    public void setShoppingCartService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public String cartPage(Model model, HttpSession httpSession) {
        ShoppingCart cart = shoppingCartService.getCurrentCart(httpSession);
        model.addAttribute("cart", cart);
        return "cart-page";
    }

    @GetMapping("/remove/{id}")
    public String removeProductFromCart(Model model, @PathVariable("id") Long id, HttpServletRequest httpServletRequest) {
        shoppingCartService.removeFromCart(httpServletRequest.getSession(), id);
        String referrer = httpServletRequest.getHeader("referer");
        return "redirect:" + referrer;
    }

    @RequestMapping("/order/fill")
    public String orderFill(Model model, HttpServletRequest httpServletRequest) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String msg = "Save the cart!";
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
            System.out.println("sent");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        String referrer = httpServletRequest.getHeader("referer");
        return "redirect:/cart/order/save";
    }

    @RequestMapping("/order/save")
    public String orderSave(Model model, HttpSession httpSession) {
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
//        try (Connection connection = factory.newConnection();
//             Channel channel = connection.createChannel()) {
//            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//            System.out.println("wait message");
//            final DeliverCallback deliverCallback = (consumerTag, delivery) -> {
//                String msg = new String(delivery.getBody(), "UTF-8");
//                System.out.println("Received "+msg);
//                shoppingCartService.saveCart(shoppingCartService.getCurrentCart(httpServletRequest.getSession()), user);
//            };
//            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
//            });
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (TimeoutException e) {
//            e.printStackTrace();
//        }
//        String referrer = httpServletRequest.getHeader("referer");
        ShoppingCart cart = shoppingCartService.getCurrentCart(httpSession);
        cloudCartController.save(cart);
//        shoppingCartService.saveCart(cart);
        return "redirect:/cart";
    }
}
