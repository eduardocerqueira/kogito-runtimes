package org.jbpm.process.core.datatype;

import java.util.HashSet;
import java.util.Set;

public class DataTypeUtils {
    private static final Set<String> langClasses = new HashSet<>();

    static {
        langClasses.add("Integer");
        langClasses.add("Boolean");
        langClasses.add("String");
        langClasses.add("Float");
        langClasses.add("Object");
    }

    public static String ensureLangPrefix(String type) {
        return langClasses.contains(type) ? "java.lang." + type : type;
    }

    public static boolean isAssignableFrom(DataType target, DataType src) {
        if (target == src || target.equals(src)) {
            return true;
        }
        try {
            return target.getObjectClass().isAssignableFrom(src.getObjectClass());
        } catch (Exception e) {
            // compare string types
        }
        return target.getStringType().equals(src.getStringType());
    }

    private DataTypeUtils() {

    }
}
