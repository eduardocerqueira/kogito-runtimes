package org.kie.kogito.process.workitems.impl;

import java.util.function.Function;

import org.kie.kogito.internal.process.runtime.KogitoWorkItem;

/* Added to make it easier to search for ParamResolver function implementations, 
 * see https://github.com/kiegroup/kogito-runtimes/pull/778#pullrequestreview-493382982 */
public interface WorkItemParamResolver<T> extends Function<KogitoWorkItem, T> {

}
