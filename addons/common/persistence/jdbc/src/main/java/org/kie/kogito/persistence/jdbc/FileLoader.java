package org.kie.kogito.persistence.jdbc;

import java.io.InputStream;
import java.util.List;

public class FileLoader {

    private FileLoader() {

    }

    static List<String> getQueryFromFile(final String dbType, final String scriptName) {
        final String fileName = String.format("sql/%s_%s.sql", scriptName, dbType);
        try (InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName)) {
            if (stream == null) {
                throw new IllegalStateException(String.format("Impossible to find %s", fileName));
            }
            byte[] buffer = stream.readAllBytes();
            String[] statements = new String(buffer).split(";");
            return List.of(statements);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error reading query script file %s", fileName), e);
        }
    }

}
