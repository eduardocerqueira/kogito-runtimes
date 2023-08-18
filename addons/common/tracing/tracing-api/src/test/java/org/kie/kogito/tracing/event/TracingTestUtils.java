package org.kie.kogito.tracing.event;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TracingTestUtils {

    public static final ObjectMapper MAPPER = new ObjectMapper();

    public static <T> T readResource(String name, Class<T> clazz) {
        try {
            return MAPPER.readValue(TracingTestUtils.class.getResource(name), clazz);
        } catch (IOException e) {
            throw new RuntimeException("Can't read test resource " + name, e);
        }
    }

    public static String readResourceAsString(String name) {
        try {
            return readFromInputStream(TracingTestUtils.class.getResourceAsStream(name));
        } catch (Exception e) {
            throw new RuntimeException("Can't read test resource " + name, e);
        }
    }

    public static String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    private TracingTestUtils() {
    }
}
