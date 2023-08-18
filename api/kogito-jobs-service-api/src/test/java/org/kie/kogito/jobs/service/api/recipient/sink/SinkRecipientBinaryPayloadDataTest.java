package org.kie.kogito.jobs.service.api.recipient.sink;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SinkRecipientBinaryPayloadDataTest {

    private static final byte[] TEST_DATA = "some data to test".getBytes();

    @Test
    void getData() {
        SinkRecipientBinaryPayloadData payloadData = SinkRecipientBinaryPayloadData.from(TEST_DATA);
        assertThat(payloadData.getData()).isEqualTo(TEST_DATA);
    }

    @Test
    void equalsMethod() {
        SinkRecipientBinaryPayloadData payloadData1 = SinkRecipientBinaryPayloadData.from(TEST_DATA);
        SinkRecipientBinaryPayloadData payloadData2 = SinkRecipientBinaryPayloadData.from(TEST_DATA);
        assertThat(payloadData1.getData()).isEqualTo(payloadData2.getData());
    }

    @Test
    void hashCodeMethod() {
        SinkRecipientBinaryPayloadData payloadData = SinkRecipientBinaryPayloadData.from(TEST_DATA);
        assertThat(payloadData.hashCode()).isEqualTo(Arrays.hashCode(TEST_DATA));
    }
}
