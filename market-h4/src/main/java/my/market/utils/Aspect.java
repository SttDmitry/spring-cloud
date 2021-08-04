package my.market.utils;

import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@org.aspectj.lang.annotation.Aspect
@Component
public class Aspect {
    @Before("execution(public * my.market.controllers.CloudCartController.*(..))")
    public void allMethodsCallsLogging() {
        System.out.println("В классе CloudCartController вызывают метод");
    }
}
