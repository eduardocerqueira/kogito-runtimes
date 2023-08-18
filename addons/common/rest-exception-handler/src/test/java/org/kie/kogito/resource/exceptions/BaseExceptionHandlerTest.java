package org.kie.kogito.resource.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kie.kogito.internal.process.runtime.WorkItemNotFoundException;
import org.kie.kogito.process.NodeInstanceNotFoundException;
import org.kie.kogito.process.ProcessInstanceDuplicatedException;
import org.kie.kogito.process.ProcessInstanceExecutionException;
import org.kie.kogito.process.ProcessInstanceNotFoundException;
import org.kie.kogito.process.VariableViolationException;
import org.kie.kogito.process.workitem.InvalidLifeCyclePhaseException;
import org.kie.kogito.process.workitem.InvalidTransitionException;
import org.kie.kogito.process.workitem.NotAuthorizedException;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;

@ExtendWith(MockitoExtension.class)
class BaseExceptionHandlerTest {

    private BaseExceptionsHandler tested;

    @Mock
    private Object badRequestResponse;

    @Mock
    private Object conflictResponse;

    @Mock
    private Object internalErrorResponse;

    @Mock
    private Object notFoundResponse;

    @Mock
    private Object forbiddenResponse;

    @BeforeEach
    void setUp() {
        tested = spy(new BaseExceptionsHandler() {
            @Override
            protected Object badRequest(Object body) {
                return badRequestResponse;
            }

            @Override
            protected Object conflict(Object body) {
                return conflictResponse;
            }

            @Override
            protected Object internalError(Object body) {
                return internalErrorResponse;
            }

            @Override
            protected Object notFound(Object body) {
                return notFoundResponse;
            }

            @Override
            protected Object forbidden(Object body) {
                return forbiddenResponse;
            }
        });
    }

    @Test
    void testMapInvalidLifeCyclePhaseException() {
        Object response = tested.mapException(new InvalidLifeCyclePhaseException("message"));
        assertThat(response).isEqualTo(badRequestResponse);
    }

    @Test
    void testMapInvalidTransitionException() {
        Object response = tested.mapException(new InvalidTransitionException("message"));
        assertThat(response).isEqualTo(badRequestResponse);
    }

    @Test
    void testMapNodeInstanceNotFoundException() {
        Object response = tested.mapException(new NodeInstanceNotFoundException("processInstanceId", "nodeInstanceId"));
        assertThat(response).isEqualTo(notFoundResponse);
    }

    @Test
    void testMapNotAuthorizedException() {
        Object response = tested.mapException(new NotAuthorizedException("message"));
        assertThat(response).isEqualTo(forbiddenResponse);
    }

    @Test
    void testMapProcessInstanceDuplicatedException() {
        Object response = tested.mapException(new ProcessInstanceDuplicatedException("processInstanceId"));
        assertThat(response).isEqualTo(conflictResponse);
    }

    @Test
    void testMapProcessInstanceExecutionException() {
        Object response = tested.mapException(new ProcessInstanceExecutionException("processInstanceId", "nodeId", "message"));
        assertThat(response).isEqualTo(internalErrorResponse);
    }

    @Test
    void testMapProcessInstanceNotFoundException() {
        Object response = tested.mapException(new ProcessInstanceNotFoundException("processInstanceId"));
        assertThat(response).isEqualTo(notFoundResponse);
    }

    @Test
    void testMapWorkItemNotFoundException() {
        Object response = tested.mapException(new WorkItemNotFoundException("workItemId"));
        assertThat(response).isEqualTo(notFoundResponse);
    }

    @Test
    void testMapVariableViolationException() {
        Object response = tested.mapException(new VariableViolationException("processInstanceId", "variable",
                "message"));
        assertThat(response).isEqualTo(badRequestResponse);
    }
}
