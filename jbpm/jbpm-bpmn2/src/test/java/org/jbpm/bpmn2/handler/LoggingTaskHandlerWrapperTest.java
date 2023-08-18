package org.jbpm.bpmn2.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.bpmn2.JbpmBpmn2TestCase;
import org.jbpm.bpmn2.handler.LoggingTaskHandlerDecorator.InputParameter;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LoggingTaskHandlerWrapperTest extends JbpmBpmn2TestCase {

    @Test
    public void testLimitExceptionInfoList() throws Exception {
        kruntime = createKogitoProcessRuntime("BPMN2-ExceptionThrowingServiceProcess.bpmn2");

        LoggingTaskHandlerDecorator loggingTaskHandlerWrapper = new LoggingTaskHandlerDecorator(ServiceTaskHandler.class, 2);
        loggingTaskHandlerWrapper.setPrintStackTrace(false);
        kruntime.getKogitoWorkItemManager().registerWorkItemHandler("Service Task", loggingTaskHandlerWrapper);

        Map<String, Object> params = new HashMap<>();
        params.put("serviceInputItem", "exception message");
        kruntime.startProcess("ServiceProcess", params);
        kruntime.startProcess("ServiceProcess", params);
        kruntime.startProcess("ServiceProcess", params);

        int size = loggingTaskHandlerWrapper.getWorkItemExceptionInfoList().size();
        assertThat(size).as("WorkItemExceptionInfoList is too large: " + size).isEqualTo(2);
    }

    @Test
    public void testFormatLoggingError() throws Exception {
        kruntime = createKogitoProcessRuntime("BPMN2-ExceptionThrowingServiceProcess.bpmn2");

        LoggingTaskHandlerDecorator loggingTaskHandlerWrapper = new LoggingTaskHandlerDecorator(ServiceTaskHandler.class, 2);
        loggingTaskHandlerWrapper.setLoggedMessageFormat("{0} - {1} - {2} - {3}");
        List<InputParameter> inputParameters = new ArrayList<LoggingTaskHandlerDecorator.InputParameter>();
        inputParameters.add(InputParameter.EXCEPTION_CLASS);
        inputParameters.add(InputParameter.WORK_ITEM_ID);
        inputParameters.add(InputParameter.WORK_ITEM_NAME);
        inputParameters.add(InputParameter.PROCESS_INSTANCE_ID);

        loggingTaskHandlerWrapper.setLoggedMessageInput(inputParameters);

        loggingTaskHandlerWrapper.setPrintStackTrace(false);
        kruntime.getKogitoWorkItemManager().registerWorkItemHandler("Service Task", loggingTaskHandlerWrapper);

        Map<String, Object> params = new HashMap<>();
        params.put("serviceInputItem", "exception message");
        kruntime.startProcess("ServiceProcess", params);
        kruntime.startProcess("ServiceProcess", params);
        kruntime.startProcess("ServiceProcess", params);
    }

}
