package org.jbpm.process.instance.impl.humantask;

import java.util.Objects;
import java.util.Set;

public class Reassignment {

    private Set<String> potentialUsers;
    private Set<String> potentialGroups;

    public Reassignment(Set<String> potentialUsers, Set<String> potentialGroups) {
        this.potentialUsers = potentialUsers;
        this.potentialGroups = potentialGroups;
    }

    public Set<String> getPotentialUsers() {
        return potentialUsers;
    }

    public Set<String> getPotentialGroups() {
        return potentialGroups;
    }

    @Override
    public String toString() {
        return "Reassigment [potentialUsers=" + potentialUsers + ", potentialGroups=" + potentialGroups + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(potentialGroups, potentialUsers);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Reassignment))
            return false;
        Reassignment other = (Reassignment) obj;
        return Objects.equals(potentialGroups, other.potentialGroups) && Objects.equals(potentialUsers,
                other.potentialUsers);
    }
}
