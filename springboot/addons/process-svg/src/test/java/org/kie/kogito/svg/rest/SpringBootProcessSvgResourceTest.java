package org.kie.kogito.svg.rest;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kie.kogito.svg.service.SpringBootProcessSvgService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class SpringBootProcessSvgResourceTest {

    private final static String PROCESS_INSTANCE_ID = "piId";
    private final static String PROCESS_ID = "travels";
    private final static String AUTH_HEADER = "Bearer: token";

    private SpringBootProcessSvgResource processSvgResourceTest;
    private SpringBootProcessSvgService processSvgServiceMock;

    @BeforeEach
    public void setup() {
        processSvgResourceTest = new SpringBootProcessSvgResource();
        processSvgServiceMock = mock(SpringBootProcessSvgService.class);
        processSvgResourceTest.setProcessSvgService(processSvgServiceMock);
    }

    @Test
    void getProcessSvgTest() {
        processSvgResourceTest.getProcessSvg(PROCESS_ID);
        verify(processSvgServiceMock).getProcessSvg(PROCESS_ID);
    }

    @Test
    void getExecutionPathByProcessInstanceIdTest() throws IOException {
        processSvgResourceTest.getExecutionPathByProcessInstanceId(PROCESS_ID, PROCESS_INSTANCE_ID, AUTH_HEADER);
        verify(processSvgServiceMock).getProcessInstanceSvg(PROCESS_ID, PROCESS_INSTANCE_ID, AUTH_HEADER);
    }
}