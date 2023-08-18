package org.kie.kogito.serialization.process.impl;

import java.util.ArrayList;
import java.util.List;

import org.jbpm.process.core.context.variable.Variable;
import org.jbpm.process.core.datatype.DataTypeResolver;
import org.kie.kogito.serialization.process.MarshallerReaderContext;
import org.kie.kogito.serialization.process.ObjectMarshallerStrategy;
import org.kie.kogito.serialization.process.protobuf.KogitoTypesProtobuf;

import com.google.protobuf.Any;

public class ProtobufVariableReader {

    private MarshallerReaderContext context;

    public ProtobufVariableReader(MarshallerReaderContext context) {
        this.context = context;
    }

    public List<Variable> buildVariables(List<KogitoTypesProtobuf.Variable> variablesProtobuf) {
        List<Variable> variables = new ArrayList<>();
        for (KogitoTypesProtobuf.Variable var : variablesProtobuf) {
            Variable storedVar = new Variable();
            storedVar.setName(var.getName());
            Any value = var.getValue();
            ObjectMarshallerStrategy strategy = context.findObjectUnmarshallerStrategyFor(value);
            Object varValue = strategy.unmarshall(value);
            storedVar.setType(DataTypeResolver.fromObject(varValue));
            if (varValue != null) {
                storedVar.setValue(varValue);
            }
            variables.add(storedVar);
        }
        return variables;
    }

}
