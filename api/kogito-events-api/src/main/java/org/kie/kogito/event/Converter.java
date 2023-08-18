package org.kie.kogito.event;

import java.io.IOException;

/**
 * Checked version of function interface that transforms one
 * object into another. If conversion is not possible, then
 * an IOException is thrown
 * 
 * @param <T> input object
 * @param <S> output object
 */
@FunctionalInterface
public interface Converter<T, S> {
    /**
     * Converts input object into output object
     * 
     * @param value input object
     * @return output object
     * @throws IOException if there is a legit problem with the conversion.
     *         For example, a wrong format of the input. This method should not throw, willingly, any runtime exception to allow
     *         proper handling of the IOexception by the caller
     */
    S convert(T value) throws IOException;
}
