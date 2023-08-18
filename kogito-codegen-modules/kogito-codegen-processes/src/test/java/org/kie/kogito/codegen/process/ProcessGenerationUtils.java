package org.kie.kogito.codegen.process;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.drools.io.FileSystemResource;
import org.jbpm.compiler.canonical.ProcessToExecModelGenerator;
import org.kie.api.definition.process.Process;
import org.kie.kogito.codegen.api.context.impl.JavaKogitoBuildContext;
import org.kie.kogito.internal.SupportedExtensions;
import org.kie.kogito.internal.process.runtime.KogitoWorkflowProcess;
import org.kie.kogito.serverless.workflow.utils.WorkflowFormat;

/**
 * Utilities for unit Process generation tests
 */
public class ProcessGenerationUtils {

    /**
     * Creates a list of {@link ProcessExecutableModelGenerator} for process generators
     *
     * @param processFilePath from the test/resources classpath folder
     * @return a list of {@link ProcessExecutableModelGenerator} from the given file
     */
    public static List<ProcessExecutableModelGenerator> execModelFromProcessFile(final String processFilePath) {
        final File processFile = new File(ProcessGenerationUtils.class.getResource(processFilePath).getFile());
        final List<Process> processes = parseProcesses(Collections.singleton(processFile));
        Assertions.assertThat(processes).isNotEmpty();

        final ProcessToExecModelGenerator execModelGenerator = new ProcessToExecModelGenerator(ProcessGenerationUtils.class.getClassLoader());
        final List<ProcessExecutableModelGenerator> processExecutableModelGenerators = new ArrayList<>();
        processes.forEach(p -> {
            processExecutableModelGenerators.add(new ProcessExecutableModelGenerator((KogitoWorkflowProcess) p, execModelGenerator));
        });
        return processExecutableModelGenerators;
    }

    private static List<Process> parseProcesses(Collection<File> processFiles) {
        List<Process> processes = new ArrayList<>();
        for (File processSourceFile : processFiles) {
            try {
                FileSystemResource r = new FileSystemResource(processSourceFile);
                if (SupportedExtensions.getBPMNExtensions().stream().anyMatch(processSourceFile.getPath()::endsWith)) {
                    processes.addAll(ProcessCodegen.parseProcessFile(r));
                } else if (SupportedExtensions.getSWFExtensions().stream().anyMatch(processSourceFile.getPath()::endsWith)) {
                    processes.add(ProcessCodegen.parseWorkflowFile(r, WorkflowFormat.fromFileName(processSourceFile.getPath()), JavaKogitoBuildContext.builder().build()).info());
                }
                if (processes.isEmpty()) {
                    throw new IllegalArgumentException("Unable to process file with unsupported extension: " + processSourceFile);
                }
            } catch (RuntimeException e) {
                throw new ProcessCodegenException(processSourceFile.getAbsolutePath(), e);
            }
        }
        return processes;
    }

}
