package org.kie.kogito.addon.source.files;

import java.util.Optional;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import io.quarkus.test.junit.QuarkusTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@QuarkusTest
class SourceFilesResourceTest {
    private static final String PROCESS_ID = "processId";

    private SourceFilesResource sourceFilesTestResource;

    @Mock
    private SourceFilesProvider mockSourceFileProvider;

    @BeforeEach
    void setup() {
        sourceFilesTestResource = new SourceFilesResource();
        mockSourceFileProvider = mock(SourceFilesProvider.class);
        sourceFilesTestResource.setSourceFilesProvider(mockSourceFileProvider);
    }

    @Test
    void getSourceFilesByProcessIdTest() {
        sourceFilesTestResource.getSourceFilesByProcessId(PROCESS_ID);
        verify(mockSourceFileProvider).getProcessSourceFiles(PROCESS_ID);
    }

    @Test
    void getEmptySourceFileByProcessIdTest() {
        when(mockSourceFileProvider.getProcessSourceFile(PROCESS_ID)).thenReturn(Optional.empty());
        assertThat(sourceFilesTestResource.getSourceFileByProcessId(PROCESS_ID).getStatus()).isEqualTo(Response.Status.NOT_FOUND.getStatusCode());
        verify(mockSourceFileProvider).getProcessSourceFile(PROCESS_ID);
    }

    @Test
    void getValidSourceFileByProcessIdTest() {
        when(mockSourceFileProvider.getProcessSourceFile(PROCESS_ID)).thenReturn(Optional.of(new SourceFile("petstore.sw.json")));
        assertThat(sourceFilesTestResource.getSourceFileByProcessId(PROCESS_ID).getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
        verify(mockSourceFileProvider).getProcessSourceFile(PROCESS_ID);
    }
}
