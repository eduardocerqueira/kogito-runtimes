package org.kie.kogito.incubation.common;

import java.util.Objects;

class Address {
    String street;

    @Override
    public boolean equals(Object o) {
        return (o instanceof Address)
                && Objects.equals(((Address) o).street, street);
    }

}

class User implements DataContext, DefaultCastable {
    String firstName;
    String lastName;
    Address addr;

    @Override
    public boolean equals(Object o) {
        if (o instanceof User) {
            User user = (User) o;
            return Objects.equals(firstName, user.firstName)
                    && Objects.equals(lastName, user.lastName)
                    && Objects.equals(addr, user.addr);
        } else
            return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, addr);
    }
}

class CustomMeta implements MetaDataContext, DefaultCastable {
    String value;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CustomMeta that = (CustomMeta) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}