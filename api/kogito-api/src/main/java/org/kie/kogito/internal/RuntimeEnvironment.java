package org.kie.kogito.internal;

public enum RuntimeEnvironment {

    JDK,
    BUILDING_NATIVE,
    RUNNING_NATIVE;

    public static RuntimeEnvironment get() {
        String graalvmNativeImage = System.getProperty("org.graalvm.nativeimage.imagecode");
        if ("buildtime".equals(graalvmNativeImage)) {
            return BUILDING_NATIVE;
        }
        if ("runtime".equals(graalvmNativeImage)) {
            return RUNNING_NATIVE;
        }
        return JDK;
    }

    public static boolean isNative() {
        return !isJdk();
    }

    public static boolean isJdk() {
        return get() == JDK;
    }
}
