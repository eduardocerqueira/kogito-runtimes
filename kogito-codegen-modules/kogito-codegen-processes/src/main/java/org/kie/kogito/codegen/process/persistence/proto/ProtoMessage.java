package org.kie.kogito.codegen.process.persistence.proto;

import java.util.ArrayList;
import java.util.List;

public class ProtoMessage extends ProtoComponent {

    private List<ProtoField> fields = new ArrayList<>();

    public ProtoMessage(String name, String javaPackageOption) {
        super(name, javaPackageOption);
    }

    public List<ProtoField> getFields() {
        return fields;
    }

    public ProtoField addField(String applicability, String type, String name) {
        int index = fields.size() + 1;
        ProtoField field = new ProtoField(applicability, type, name, index);
        if (!fields.contains(field)) {
            fields.add(field);
        }

        return field;
    }

    public String serialize() {
        StringBuilder tostring = new StringBuilder();
        if (comment != null) {
            tostring.append("/* ").append(comment).append(" */ \n");
        }
        tostring.append("message ").append(name).append(" { \n");
        if (javaPackageOption != null) {
            tostring.append("\toption java_package = \"").append(javaPackageOption).append("\";\n");
        }
        fields.forEach(f -> tostring.append(f.toString()));
        tostring.append("}\n");

        return tostring.toString();

    }

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
        ProtoMessage other = (ProtoMessage) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
}
