package org.jbpm.bpmn2.objects;

import java.io.Serializable;

public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private boolean valid;
    private double discount;

    /**
     * Constructor
     */
    public Order() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String anId) {
        this.id = anId;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public double getDiscount() {
        return this.discount;
    }

    public void setDiscount(double d) {
        this.discount = d;
    }

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append(this.id);
        buf.append(",");
        buf.append("valid: ");
        buf.append(this.valid);
        buf.append(",");
        buf.append("discount: ");
        buf.append(this.discount);
        return buf.toString();
    }

}
