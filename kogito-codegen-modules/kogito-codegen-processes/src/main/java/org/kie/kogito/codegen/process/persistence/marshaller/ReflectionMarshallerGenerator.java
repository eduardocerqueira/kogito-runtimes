package org.kie.kogito.codegen.process.persistence.marshaller;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Collection;
import java.util.Optional;

import org.infinispan.protostream.descriptors.FieldDescriptor;
import org.kie.kogito.codegen.api.context.KogitoBuildContext;

public class ReflectionMarshallerGenerator extends AbstractMarshallerGenerator<Class<?>> {

    public ReflectionMarshallerGenerator(KogitoBuildContext context, Collection<Class<?>> rawDataClasses) {
        super(context, rawDataClasses);
    }

    public ReflectionMarshallerGenerator(KogitoBuildContext context) {
        this(context, null);
    }

    @Override
    protected boolean isArray(String javaType, FieldDescriptor field) {
        Optional<Class<?>> clazz = modelClasses.stream().filter(cls -> cls.getName().equals(javaType)).findFirst();
        if (clazz.isPresent()) {
            try {
                PropertyDescriptor[] pds = Introspector.getBeanInfo(clazz.get()).getPropertyDescriptors();
                for (PropertyDescriptor pd : pds) {
                    if (pd.getName().equals(field.getName())) {
                        return pd.getPropertyType().isArray();
                    }
                }
                return false;
            } catch (IntrospectionException e) {
                return false;
            }
        } else {
            return false;
        }
    }
}
