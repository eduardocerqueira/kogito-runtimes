package org.kie.kogito.codegen.data;

import org.infinispan.protostream.annotations.ProtoEnumValue;

public enum AnswerWithAnnotations {
    @ProtoEnumValue(number = 1)
    YES,
    @ProtoEnumValue(number = 2)
    MAYBE,
    @ProtoEnumValue(number = 3)
    NO
}
