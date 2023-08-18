package org.kie.kogito.codegen.data;

import java.util.Objects;

public class PersonSubClass extends Person {

    private String subField;

    public String getSubField() {
        return subField;
    }

    public void setSubField(String subField) {
        this.subField = subField;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof PersonSubClass))
            return false;
        if (!super.equals(o))
            return false;
        PersonSubClass that = (PersonSubClass) o;
        return Objects.equals(subField, that.subField);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), subField);
    }
}
