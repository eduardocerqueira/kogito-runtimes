package org.jbpm.bpmn2.feel;

import java.io.IOException;

import org.kie.dmn.feel.lang.types.impl.ComparablePeriod;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class ComparablePeriodSerializer extends StdSerializer<ComparablePeriod> {

    private static final long serialVersionUID = -3734521147902638828L;

    public ComparablePeriodSerializer() {
        this(null);
    }

    public ComparablePeriodSerializer(Class<ComparablePeriod> t) {
        super(t);
    }

    @Override
    public void serialize(ComparablePeriod v, JsonGenerator g, SerializerProvider sp) throws IOException {
        sp.defaultSerializeValue(v.asPeriod(), g);
    }

}