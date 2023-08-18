package org.kie.kogito.codegen.data;

import java.io.Serializable;
import java.math.BigDecimal;

public class Money implements Serializable {

    private BigDecimal amount;

    private Money(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public static Money of(BigDecimal amount) {
        return new Money(amount);
    }
}
