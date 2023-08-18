package org.kie.kogito.dmn.util;

import java.lang.reflect.InvocationTargetException;

import org.kie.dmn.api.core.DMNResult;
import org.kie.dmn.api.core.FEELPropertyAccessible;

public class StronglyTypedUtils {

    private StronglyTypedUtils() {
    }

    public static FEELPropertyAccessible convertToOutputSet(FEELPropertyAccessible inputSet,
            Class<? extends FEELPropertyAccessible> outputSetClass) {
        FEELPropertyAccessible outputSet;
        try {
            outputSet = outputSetClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            throw new RuntimeException(e);
        }
        outputSet.fromMap(inputSet.allFEELProperties());
        return outputSet;
    }

    public static FEELPropertyAccessible extractOutputSet(DMNResult result,
            Class<? extends FEELPropertyAccessible> outputSetClass) {
        FEELPropertyAccessible outputSet;
        try {
            outputSet = outputSetClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            throw new RuntimeException(e);
        }
        outputSet.fromMap(result.getContext().getAll());
        return outputSet;
    }
}
