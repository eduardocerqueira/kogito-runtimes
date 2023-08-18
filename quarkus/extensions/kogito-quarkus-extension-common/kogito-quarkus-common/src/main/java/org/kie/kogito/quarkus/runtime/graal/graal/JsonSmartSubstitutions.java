package org.kie.kogito.quarkus.runtime.graal.graal;

import java.io.IOException;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.spi.mapper.JsonSmartMappingProvider;
import com.oracle.svm.core.annotate.Substitute;
import com.oracle.svm.core.annotate.TargetClass;

import net.minidev.json.JSONStyle;
import net.minidev.json.reader.BeansWriterASM;

@TargetClass(JsonSmartMappingProvider.class)
final class JsonSmartMappingProviderTarget {

    @Substitute
    public <T> T map(Object source, Class<T> targetType, Configuration configuration) {
        throw new UnsupportedOperationException("this path is never taken");
    }
}

@TargetClass(BeansWriterASM.class)
final class BeansWriterASMTarget {
    @Substitute
    public <E> void writeJSONString(E value, Appendable out, JSONStyle compression) throws IOException {
        out.append(value.toString());
    }
}
