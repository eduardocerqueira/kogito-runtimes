package org.kie.kogito.decision;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.kie.dmn.api.core.DMNContext;
import org.kie.dmn.api.core.DMNMetadata;
import org.kie.kogito.ExecutionIdSupplier;

import static org.assertj.core.api.Assertions.assertThat;

public class DecisionExecutionIdUtilsTest {

    @Test
    public void test() {
        final String dummyId = "DUMMY_ID";
        final ExecutionIdSupplier supplier = () -> dummyId;
        final DMNContext ctx = new TestDMNContext();

        assertThat(DecisionExecutionIdUtils.get(DecisionExecutionIdUtils.inject(ctx, supplier))).isEqualTo(dummyId);
    }

    private static class TestDMNContext implements DMNContext {

        private final Map<String, Object> map = new HashMap<>();
        private final DMNMetadata metadata = new TestDMNMetadata();

        @Override
        public Object set(String s, Object o) {
            return map.put(s, o);
        }

        @Override
        public Object get(String s) {
            return map.get(s);
        }

        @Override
        public Map<String, Object> getAll() {
            return map;
        }

        @Override
        public boolean isDefined(String s) {
            return map.containsKey(s);
        }

        @Override
        public DMNMetadata getMetadata() {
            return metadata;
        }

        @Override
        public DMNContext clone() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void pushScope(String s, String s1) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void popScope() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Optional<String> scopeNamespace() {
            throw new UnsupportedOperationException();
        }

    }

    private static class TestDMNMetadata implements DMNMetadata {

        private final Map<String, Object> map = new HashMap<>();

        @Override
        public Object set(String s, Object o) {
            return map.put(s, o);
        }

        @Override
        public Object get(String s) {
            return map.get(s);
        }

        @Override
        public Map<String, Object> asMap() {
            return map;
        }

    }

}
