package org.kie.kogito.workflows.services;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SquareService {

    public Collection<Integer> squareAll(Collection<Integer> numbers) {
        return numbers.stream().map(this::squareOne).collect(Collectors.toList());
    }

    public Integer squareOne(Integer x) {
        return x * x;
    }
}
