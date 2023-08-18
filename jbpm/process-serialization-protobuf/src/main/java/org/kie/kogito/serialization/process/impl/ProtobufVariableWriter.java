package org.kie.kogito.serialization.process.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.kie.kogito.serialization.process.MarshallerWriterContext;
import org.kie.kogito.serialization.process.ObjectMarshallerStrategy;
import org.kie.kogito.serialization.process.protobuf.KogitoTypesProtobuf;

import com.google.protobuf.Any;
import com.google.protobuf.ByteString;
import com.google.protobuf.BytesValue;

public class ProtobufVariableWriter {

    private MarshallerWriterContext context;

    public ProtobufVariableWriter(MarshallerWriterContext context) {
        this.context = context;
    }

    public List<KogitoTypesProtobuf.Variable> buildVariables(List<Map.Entry<String, Object>> variables) {
        Comparator<Map.Entry<String, Object>> comparator = (o1, o2) -> o1.getKey().compareTo(o2.getKey());
        Collections.sort(variables, comparator);

        List<KogitoTypesProtobuf.Variable> variablesProtobuf = new ArrayList<>();
        for (Map.Entry<String, Object> entry : variables) {
            KogitoTypesProtobuf.Variable.Builder variableBuilder = KogitoTypesProtobuf.Variable.newBuilder();
            variableBuilder.setName(entry.getKey());
            if (entry.getValue() != null) {
                Object value = entry.getValue();
                ObjectMarshallerStrategy strategy = context.findObjectMarshallerStrategyFor(value);
                variableBuilder.setDataType(entry.getValue().getClass().getName()).setValue(strategy.marshall(value));
            } else {
                variableBuilder.setValue(Any.pack(BytesValue.of(ByteString.EMPTY)));
            }
            variablesProtobuf.add(variableBuilder.build());
        }
        return variablesProtobuf;
    }

}
