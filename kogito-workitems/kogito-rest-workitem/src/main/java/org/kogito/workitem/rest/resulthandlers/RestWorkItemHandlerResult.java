package org.kogito.workitem.rest.resulthandlers;

import java.util.function.BiFunction;

import io.vertx.mutiny.core.buffer.Buffer;
import io.vertx.mutiny.ext.web.client.HttpResponse;

/* Added to make it easier to search for ResultHandler bifunction implementations, 
 * see https://github.com/kiegroup/kogito-runtimes/pull/778#pullrequestreview-493382982 */
public interface RestWorkItemHandlerResult extends BiFunction<HttpResponse<Buffer>, Class<?>, Object> {
}
