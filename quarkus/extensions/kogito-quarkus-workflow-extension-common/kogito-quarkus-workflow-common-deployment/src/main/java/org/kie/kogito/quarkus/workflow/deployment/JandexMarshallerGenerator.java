package org.kie.kogito.quarkus.workflow.deployment;

import java.lang.reflect.Field;
import java.util.Collection;

import org.infinispan.protostream.descriptors.FieldDescriptor;
import org.jboss.jandex.ClassInfo;
import org.kie.kogito.codegen.api.context.KogitoBuildContext;
import org.kie.kogito.codegen.process.persistence.marshaller.AbstractMarshallerGenerator;

public class JandexMarshallerGenerator extends AbstractMarshallerGenerator<ClassInfo> {

    public JandexMarshallerGenerator(KogitoBuildContext context, Collection<ClassInfo> rawDataClasses) {
        super(context, rawDataClasses);
    }

    @Override
    protected boolean isArray(String javaType, FieldDescriptor field) {
        try {
            Field declaredField = Class.forName(javaType).getDeclaredField(field.getName());
            return declaredField.getType().isArray() && !declaredField.getType().isPrimitive();
        } catch (ClassNotFoundException | NoSuchFieldException e) {
            return false;
        }
    }
}
