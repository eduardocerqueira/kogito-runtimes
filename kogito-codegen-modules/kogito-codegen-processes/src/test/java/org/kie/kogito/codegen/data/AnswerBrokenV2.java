package org.kie.kogito.codegen.data;

import org.infinispan.protostream.annotations.ProtoEnumValue;

public enum AnswerBrokenV2 {
    YES,
    MAYBE,
    @ProtoEnumValue(number = 3)
    NO
}
