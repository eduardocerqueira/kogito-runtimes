package org.kie.kogito.test.utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.kie.kogito.test.utils.SocketUtils.PORT_RANGE_MAX;
import static org.kie.kogito.test.utils.SocketUtils.PORT_RANGE_MIN;

public class SocketUtilsTest {

    @Test
    public void findAvailablePortTest() {
        int availablePort = SocketUtils.findAvailablePort();
        assertThat(availablePort).isLessThanOrEqualTo(PORT_RANGE_MAX)
                .isGreaterThanOrEqualTo(PORT_RANGE_MIN);
    }

}
