package org.kie.kogito.svg.service;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kie.kogito.svg.AbstractProcessSvgService;
import org.kie.kogito.svg.ProcessSvgServiceTest;
import org.kie.kogito.svg.dataindex.DataIndexClient;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

@ExtendWith(MockitoExtension.class)
public class QuarkusProcessSvgServiceTest extends ProcessSvgServiceTest {

    private QuarkusProcessSvgService tested;
    private DataIndexClient dataIndexClient;

    @BeforeEach
    public void setup() {
        dataIndexClient = mock(DataIndexClient.class);

        tested = spy(new QuarkusProcessSvgService(dataIndexClient,
                Optional.empty(),
                AbstractProcessSvgService.DEFAULT_COMPLETED_COLOR,
                AbstractProcessSvgService.DEFAULT_COMPLETED_BORDER_COLOR,
                AbstractProcessSvgService.DEFAULT_ACTIVE_BORDER_COLOR));
    }

    @Override
    protected AbstractProcessSvgService getTestedProcessSvgService() {
        return tested;
    }
}