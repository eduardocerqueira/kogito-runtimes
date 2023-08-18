package org.jbpm.process.core.transformation;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonResolverTest {

    public static class Parent {
        private String id;
        private String age;
        private Child child;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public Child getChild() {
            return child;
        }

        public void setChild(Child child) {
            this.child = child;
        }
    }

    public static class Child {
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class ParentJson {
        @JsonProperty("User Identifier")
        private String id;
        @JsonIgnore
        private String age;
        private Child child;

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public Child getChild() {
            return child;
        }

        public void setChild(Child child) {
            this.child = child;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ChildJson {
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class ParentChildJson {
        private String id;
        private String age;
        private ChildJson child;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public ChildJson getChild() {
            return child;
        }

        public void setChild(ChildJson child) {
            this.child = child;
        }
    }

    private JsonResolver resolver = new JsonResolver();

    @Test
    void testResolveItemsWithoutJsonAnnotation() {
        Map<String, Object> items = new HashMap<>();
        items.put("parent", new Parent());
        items.put("parent2", new Parent());
        items.put("child", new Child());
        items.put("string", "value");
        Map<String, Object> output = resolver.resolveOnlyAnnotatedItems(items);
        assertThat(output.get("parent")).isExactlyInstanceOf(Parent.class);
        assertThat(output.get("parent2")).isExactlyInstanceOf(Parent.class);
        assertThat(output.get("child")).isExactlyInstanceOf(Child.class);
        assertThat(output.get("string")).isEqualTo("value");
    }

    @Test
    void testResolveItemsWithJsonAnnotation() {
        Map<String, Object> items = new HashMap<>();
        items.put("parent", new Parent());
        items.put("parent2", new ParentJson());
        items.put("string", "value");
        Map<String, Object> output = resolver.resolveOnlyAnnotatedItems(items);
        assertThat(output.get("parent")).isExactlyInstanceOf(Parent.class);
        assertThat(output.get("parent2")).isInstanceOf(Map.class);
        assertThat(output.get("string")).isEqualTo("value");
    }

    @Test
    void testResolveItemsWithNestedJsonAnnotation() {
        Map<String, Object> items = new HashMap<>();
        items.put("parent", new Parent());
        items.put("parent2", new ParentChildJson());
        items.put("string", "value");
        Map<String, Object> output = resolver.resolveOnlyAnnotatedItems(items);
        assertThat(output.get("parent")).isExactlyInstanceOf(Parent.class);
        assertThat(output.get("parent2")).isInstanceOf(Map.class);
        assertThat(output.get("string")).isEqualTo("value");
    }

    @Test
    void testResolveAll() {
        Map<String, Object> items = new HashMap<>();
        items.put("parent", new Parent());
        items.put("parent2", new ParentChildJson());
        items.put("child", new Child());
        items.put("child2", new ChildJson());
        items.put("string", "value");
        Map<String, Object> output = resolver.resolveAll(items);
        assertThat(output.get("parent")).isInstanceOf(Map.class);
        assertThat(output.get("parent2")).isInstanceOf(Map.class);
        assertThat(output.get("child")).isInstanceOf(Map.class);
        assertThat(output.get("child2")).isInstanceOf(Map.class);
        assertThat(output.get("string")).isEqualTo("value");
    }

    @Test
    void testDate() {
        LocalDate localDate = LocalDate.of(2021, 12, 21);
        Map<String, Object> r = resolver.resolveAll(Map.of("date", localDate));
        assertThat(r.get("date")).isEqualTo(List.of(2021, 12, 21));
    }
}
