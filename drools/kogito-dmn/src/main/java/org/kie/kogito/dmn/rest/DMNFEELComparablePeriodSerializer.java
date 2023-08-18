package org.kie.kogito.dmn.rest;

import java.io.IOException;

import org.kie.dmn.feel.lang.types.impl.ComparablePeriod;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class DMNFEELComparablePeriodSerializer extends StdSerializer<ComparablePeriod> {

    public DMNFEELComparablePeriodSerializer() {
        this(null);
    }

    public DMNFEELComparablePeriodSerializer(Class<ComparablePeriod> t) {
        super(t);
    }

    @Override
    public void serialize(ComparablePeriod v, JsonGenerator g, SerializerProvider sp) throws IOException {
        sp.defaultSerializeValue(v.asPeriod(), g);
    }
}
