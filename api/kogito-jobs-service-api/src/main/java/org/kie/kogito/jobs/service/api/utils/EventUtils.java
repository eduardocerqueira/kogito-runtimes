package org.kie.kogito.jobs.service.api.utils;

public class EventUtils {

    private EventUtils() {
    }

    public static boolean isValidExtensionName(String name) {
        if (name == null || name.length() == 0) {
            return false;
        }
        for (int i = 0; i < name.length(); i++) {
            if (!isValidExtensionChar(name.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static void validateExtensionName(String name) {
        if (!isValidExtensionName(name)) {
            throw new IllegalArgumentException("Invalid attribute or extension name: '" + name
                    + "'. CloudEvents extension and attribute names MUST consist of lower-case " +
                    " letters ('a' to 'z') or digits ('0' to '9') from the ASCII character set.");
        }
    }

    private static boolean isValidExtensionChar(char c) {
        return (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9');
    }
}
