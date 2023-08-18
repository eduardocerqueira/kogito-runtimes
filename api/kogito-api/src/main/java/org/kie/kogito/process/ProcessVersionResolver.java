package org.kie.kogito.process;

import java.util.function.Function;

/**
 * Resolver interface to look up the Process version
 */
public interface ProcessVersionResolver extends Function<Process, String> {

}
