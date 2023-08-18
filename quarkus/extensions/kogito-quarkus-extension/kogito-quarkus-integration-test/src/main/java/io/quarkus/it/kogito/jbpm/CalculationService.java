package io.quarkus.it.kogito.jbpm;

import java.security.SecureRandom;
import java.util.Random;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CalculationService {

    private Random random = new SecureRandom();

    public Order calculateTotal(Order order) {
        order.setTotal(random.nextDouble());

        return order;
    }
}
