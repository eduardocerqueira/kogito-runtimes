package org.kie.kogito.workflows.services;

import java.util.Date;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class BasicDataPerson {

    private String cardId;
    private double discount;
    private int count;
    private Boolean enabled;
    private Date birthDate;

    public BasicDataPerson() {
    }

    public BasicDataPerson(String cardId, double discount, int count, Boolean enabled, Date birthDate) {
        this.cardId = cardId;
        this.discount = discount;
        this.count = count;
        this.enabled = enabled;
        this.birthDate = birthDate;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}