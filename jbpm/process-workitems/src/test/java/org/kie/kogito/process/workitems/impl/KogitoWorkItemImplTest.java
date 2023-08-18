package org.kie.kogito.process.workitems.impl;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.kie.kogito.internal.process.runtime.KogitoWorkItem;

import static org.assertj.core.api.Assertions.assertThat;

public class KogitoWorkItemImplTest {

    private static class MyWorkItemHandlerParamResolver implements WorkItemParamResolver<Object> {
        @Override
        public Object apply(KogitoWorkItem t) {
            return t.getParameter("name").toString().concat(" is the best");
        }

    }

    @Test
    public void testPutParameters() {
        KogitoWorkItem workItem = new KogitoWorkItemImpl();
        workItem.getParameters().put("name", "javierito");
        workItem.getParameters().put("resolver", new MyWorkItemHandlerParamResolver());
        assertWI(workItem);
    }

    @Test
    public void testSetParameters() {
        KogitoWorkItemImpl workItem = new KogitoWorkItemImpl();
        Map<String, Object> map = new HashMap<>();
        map.put("name", "javierito");
        map.put("resolver", new MyWorkItemHandlerParamResolver());
        workItem.setParameters(map);
        assertWI(workItem);
    }

    @Test
    public void testSetParameter() {
        KogitoWorkItemImpl workItem = new KogitoWorkItemImpl();
        workItem.setParameter("name", "javierito");
        workItem.setParameter("resolver", new MyWorkItemHandlerParamResolver());
        assertWI(workItem);
    }

    private void assertWI(KogitoWorkItem workItem) {
        assertEqualsWI(workItem, "javierito", "name");
        assertEqualsWI(workItem, "javierito is the best", "resolver");

    }

    private void assertEqualsWI(KogitoWorkItem workItem, Object expected, String parameter) {
        assertThat(workItem.getParameter(parameter)).isEqualTo(expected);
        Map<String, Object> map = new HashMap<>(workItem.getParameters());

        assertThat(map).containsEntry(parameter, expected);
        assertThat(map.entrySet().stream().filter(p -> p.getKey().equals(parameter))
                .findFirst().orElseThrow(IllegalStateException::new).getValue()).isEqualTo(expected);
    }

}
