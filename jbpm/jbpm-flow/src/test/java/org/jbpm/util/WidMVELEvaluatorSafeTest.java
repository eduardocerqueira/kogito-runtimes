package org.jbpm.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

import static org.assertj.core.api.Assertions.fail;

@Disabled("This test causes problems to surefire (see same issue with org.drools.core.util.MVELSafeHelperTest) It works when executed by itself.")
public class WidMVELEvaluatorSafeTest extends WidMVELEvaluatorTest {

    private static TestSecurityManager tsm;

    @BeforeEach
    public void before() {
        try {
            String enginePolicy = getResouce("/policy/engine.policy");
            String kiePolicy = getResouce("/policy/kie.policy");
            System.setProperty("java.security.policy",
                    enginePolicy);
            System.setProperty("kie.security.policy",
                    kiePolicy);

            tsm = new TestSecurityManager();
            System.setSecurityManager(tsm);
        } catch (Exception e) {
            fail("unable to initiate security manager : " + e.getMessage());
        }
    }

    @AfterEach
    public void after() {
        System.setSecurityManager(null);
        System.setProperty("java.security.policy",
                "");
        System.setProperty("kie.security.policy",
                "");
    }

    public static class TestSecurityManager extends SecurityManager {

        @Override
        public void checkExit(int status) {
            super.checkExit(status);
            throw new ShouldHavePrevented("The security policy should have prevented the call to System.exit()");
        }
    }

    public static class ShouldHavePrevented extends SecurityException {

        public ShouldHavePrevented(String message) {
            super(message);
        }
    }
}
