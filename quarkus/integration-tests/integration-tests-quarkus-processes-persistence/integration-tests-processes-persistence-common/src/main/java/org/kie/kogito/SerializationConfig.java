package org.kie.kogito;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.ArrayList;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection(targets = { Person.class, Person[].class, Address.class, Instant.class, ArrayList.class, String.class, BigDecimal.class, Number.class, BigInteger.class, ZonedDateTime.class,
        byte[].class, int[].class }, classNames = { "java.time.Ser" }, serialization = true)
public class SerializationConfig {
}