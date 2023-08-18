package org.kie.kogito.workflows.services;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EvenService {

    public void isEven(int number) {
        if (number % 2 != 0) {
            throw new IllegalArgumentException("Odd situation");
        }
    }
}
