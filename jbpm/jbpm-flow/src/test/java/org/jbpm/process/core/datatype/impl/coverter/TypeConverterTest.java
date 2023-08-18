package org.jbpm.process.core.datatype.impl.coverter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.jbpm.process.core.datatype.impl.type.ObjectDataType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TypeConverterTest {

    @Test
    public void testStringObjectDataType() {

        ObjectDataType data = new ObjectDataType("java.lang.String");
        // no converted is used
        String readValue = (String) data.readValue("hello");
        assertThat(readValue).isEqualTo("hello");
    }

    @Test
    public void testDateObjectDataType() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        Date now = new Date();

        ObjectDataType data = new ObjectDataType("java.util.Date");
        // date converted is used
        Date readValue = (Date) data.readValue(sdf.format(now));
        assertThat(readValue).hasToString(now.toString());
    }
}
