package org.kie.kogito.internal.utils;

import java.util.Arrays;
import java.util.Objects;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.kie.kogito.internal.utils.ConversionUtils.concatPaths;
import static org.kie.kogito.internal.utils.ConversionUtils.convert;
import static org.kie.kogito.internal.utils.ConversionUtils.convertToCollection;
import static org.kie.kogito.internal.utils.ConversionUtils.toCamelCase;

class ConversionUtilsTest {

    static class Person {
        private final String name;
        private final int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return name + ";" + age;
        }

        @Override
        public int hashCode() {
            return Objects.hash(age, name);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            Person other = (Person) obj;
            return age == other.age && Objects.equals(name, other.name);
        }

        public static Person convert(String str) {
            String[] strs = str.split(";");
            return new Person(strs[0], Integer.parseInt(strs[1]));
        }

    }

    static class PersonConstructor {

        public PersonConstructor(String str) {

        }
    }

    @Test
    void testConvertBoolean() {
        assertThat(convert("true", Boolean.class)).isTrue();
        assertThat(convert("5", Boolean.class)).isFalse();
    }

    @Test
    void testConvertInteger() {
        assertThat(convert("5", Integer.class)).isEqualTo(5);
    }

    @Test
    void testConvertDouble() {
        assertThat(convert("10.54d", Double.class)).isEqualTo(10.54d);
    }

    @Test
    void testConvertFloat() {
        assertThat(convert("10.54f", Float.class)).isEqualTo(10.54f);
    }

    @Test
    void testConvertLong() {
        assertThat(convert("1000000000", Long.class)).isEqualTo(1000000000L);
    }

    @Test
    void testConvertShort() {
        assertThat(convert("5", Short.class)).isEqualTo((short) 5);
    }

    @Test
    void testConvertByte() {
        assertThat(convert("112", Byte.class)).isEqualTo((byte) 112);
    }

    @Test
    void testConvertPerson() {
        Person person = new Person("Javi", 23);
        String personAsString = person.toString();
        assertThat(convert(personAsString, Person.class)).isEqualTo(person);
        assertThat(convert(personAsString, PersonConstructor.class)).isInstanceOf(PersonConstructor.class);
    }

    @Test
    void testCamelCase() {
        assertThat(toCamelCase("myapp.create")).isEqualTo("myappCreate");
        assertThat(toCamelCase("getByName_1")).isEqualTo("getByName1");
        assertThat(toCamelCase("myapp.create")).isNotEqualTo("myappcreate");
    }

    @Test
    public void testConcatPaths() {
        final String expected = "http:localhost:8080/pepe/pepa/pepi";
        assertThat(concatPaths("http:localhost:8080/pepe/", "/pepa/pepi")).isEqualTo(expected);
        assertThat(concatPaths("http:localhost:8080/pepe", "pepa/pepi")).isEqualTo(expected);
        assertThat(concatPaths("http:localhost:8080/pepe/", "pepa/pepi")).isEqualTo(expected);
        assertThat(concatPaths("http:localhost:8080/pepe", "/pepa/pepi")).isEqualTo(expected);
    }

    @Test
    public void testConvertToCollection() {
        assertThat(convertToCollection("1,2,3", Integer.class)).isEqualTo(Arrays.asList(1, 2, 3));
    }
}
