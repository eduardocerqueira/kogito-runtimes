package org.kie.kogito.codegen.process.persistence.proto;

public abstract class ProtoComponent {

    protected String name;
    protected String javaPackageOption;
    protected String comment;

    public ProtoComponent(String name, String javaPackageOption) {
        this.name = name;
        this.javaPackageOption = javaPackageOption;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJavaPackageOption() {
        return javaPackageOption;
    }

    public void setJavaPackageOption(String javaPackageOption) {
        this.javaPackageOption = javaPackageOption;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public abstract String serialize();

    @Override
    public String toString() {
        return serialize();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProtoComponent other = (ProtoComponent) obj;
        if (name == null) {
            return other.name == null;
        } else {
            return name.equals(other.name);
        }
    }
}
