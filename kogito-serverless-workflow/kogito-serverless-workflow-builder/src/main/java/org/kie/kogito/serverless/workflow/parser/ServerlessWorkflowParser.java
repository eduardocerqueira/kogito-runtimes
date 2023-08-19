/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.kie.kogito.serverless.workflow.parser;

import java.io.IOException;
import java.io.Reader;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jbpm.process.core.datatype.impl.type.ObjectDataType;
import org.jbpm.ruleflow.core.Metadata;
import org.jbpm.ruleflow.core.RuleFlowProcessFactory;
import org.jbpm.workflow.core.WorkflowModelValidator;
import org.kie.kogito.codegen.api.GeneratedInfo;
import org.kie.kogito.codegen.api.context.KogitoBuildContext;
import org.kie.kogito.internal.process.runtime.KogitoWorkflowProcess;
import org.kie.kogito.internal.utils.ConversionUtils;
import org.kie.kogito.jackson.utils.ObjectMapperFactory;
import org.kie.kogito.serverless.workflow.SWFConstants;
import org.kie.kogito.serverless.workflow.extensions.OutputSchema;
import org.kie.kogito.serverless.workflow.operationid.WorkflowOperationIdFactoryProvider;
import org.kie.kogito.serverless.workflow.parser.handlers.StateHandler;
import org.kie.kogito.serverless.workflow.parser.handlers.StateHandlerFactory;
import org.kie.kogito.serverless.workflow.parser.handlers.validation.WorkflowValidator;
import org.kie.kogito.serverless.workflow.suppliers.JsonSchemaValidatorSupplier;
import org.kie.kogito.serverless.workflow.utils.ServerlessWorkflowUtils;
import org.kie.kogito.serverless.workflow.utils.WorkflowFormat;

import com.fasterxml.jackson.databind.JsonNode;

import io.serverlessworkflow.api.Workflow;
import io.serverlessworkflow.api.datainputschema.DataInputSchema;
import io.serverlessworkflow.api.timeouts.TimeoutsDefinition;
import io.serverlessworkflow.api.timeouts.WorkflowExecTimeout;
import io.serverlessworkflow.api.workflow.Constants;

import static org.kie.kogito.serverless.workflow.utils.ServerlessWorkflowUtils.processResourceFile;

public class ServerlessWorkflowParser {

    public static final String NODE_START_NAME = "Start";
    public static final String NODE_END_NAME = "End";
    public static final String DEFAULT_NAME = "workflow";
    public static final String DEFAULT_PACKAGE = "org.kie.kogito.serverless";
    public static final String DEFAULT_VERSION = "1.0";

    public static final String JSON_NODE = "com.fasterxml.jackson.databind.JsonNode";
    public static final String DEFAULT_WORKFLOW_VAR = SWFConstants.DEFAULT_WORKFLOW_VAR;

    private NodeIdGenerator idGenerator = DefaultNodeIdGenerator.get();
    private Workflow workflow;
    private GeneratedInfo<KogitoWorkflowProcess> processInfo;
    private KogitoBuildContext context;

    public static ServerlessWorkflowParser of(Reader workflowFile, WorkflowFormat workflowFormat, KogitoBuildContext context) throws IOException {
        return of(ServerlessWorkflowUtils.getWorkflow(workflowFile, workflowFormat), context);
    }

    /**
     * @deprecated use method that accepts WorkflowFormat enumeration
     */
    @Deprecated
    public static ServerlessWorkflowParser of(Reader workflowFile, String workflowFormat, KogitoBuildContext context) throws IOException {
        return of(ServerlessWorkflowUtils.getWorkflow(workflowFile, workflowFormat), context);
    }

    public static ServerlessWorkflowParser of(Workflow workflow, KogitoBuildContext context) {
        return new ServerlessWorkflowParser(workflow, context);
    }

    public ServerlessWorkflowParser withIdGenerator(NodeIdGenerator idGenerator) {
        this.idGenerator = idGenerator;
        return this;
    }

    private ServerlessWorkflowParser(Workflow workflow, KogitoBuildContext context) {
        this.workflow = workflow;
        this.context = context;
    }

    private GeneratedInfo<KogitoWorkflowProcess> parseProcess() {
        WorkflowValidator.validateStart(workflow);
        RuleFlowProcessFactory factory = RuleFlowProcessFactory.createProcess(workflow.getId(), !workflow.isKeepActive())
                .name(workflow.getName() == null ? DEFAULT_NAME : workflow.getName())
                .version(workflow.getVersion() == null ? DEFAULT_VERSION : workflow.getVersion())
                .packageName(workflow.getMetadata() != null ? workflow.getMetadata().getOrDefault("package",
                        DEFAULT_PACKAGE) : DEFAULT_PACKAGE)
                .visibility("Public")
                .variable(DEFAULT_WORKFLOW_VAR, new ObjectDataType(JsonNode.class), ObjectMapperFactory.listenerAware().createObjectNode())
                .type(KogitoWorkflowProcess.SW_TYPE);
        ParserContext parserContext =
                new ParserContext(idGenerator, factory, context, WorkflowOperationIdFactoryProvider.getFactory(context.getApplicationProperty(WorkflowOperationIdFactoryProvider.PROPERTY_NAME)));

        modelValidator(parserContext, Optional.ofNullable(workflow.getDataInputSchema())).ifPresent(factory::inputValidator);
        modelValidator(parserContext, ServerlessWorkflowUtils.getExtension(workflow, OutputSchema.class).map(OutputSchema::getOutputSchema)).ifPresent(factory::outputValidator);
        loadConstants(factory, parserContext);
        Collection<StateHandler<?>> handlers =
                workflow.getStates().stream().map(state -> StateHandlerFactory.getStateHandler(state, workflow, parserContext))
                        .filter(Optional::isPresent).map(Optional::get).filter(state -> !state.usedForCompensation()).collect(Collectors.toList());
        handlers.forEach(StateHandler::handleStart);
        handlers.forEach(StateHandler::handleEnd);
        handlers.forEach(StateHandler::handleState);
        handlers.forEach(StateHandler::handleTransitions);
        handlers.forEach(StateHandler::handleConnections);
        if (parserContext.isCompensation()) {
            factory.metaData(Metadata.COMPENSATION, true);
            factory.addCompensationContext(workflow.getId());
        }

        TimeoutsDefinition timeouts = workflow.getTimeouts();
        if (timeouts != null) {
            WorkflowExecTimeout workflowTimeout = timeouts.getWorkflowExecTimeout();
            if (workflowTimeout != null) {
                factory.metaData(Metadata.PROCESS_DURATION, workflowTimeout.getDuration());
            }
        }

        Collection<String> tags = workflow.getAnnotations();
        if (tags != null && !tags.isEmpty()) {
            factory.metaData(Metadata.TAGS, tags);
        }
        String description = workflow.getDescription();
        if (!ConversionUtils.isEmpty(description)) {
            factory.metaData(Metadata.DESCRIPTION, description);
        }
        return new GeneratedInfo<>(factory.validate().getProcess(), parserContext.generatedFiles());
    }

    private Optional<WorkflowModelValidator> modelValidator(ParserContext parserContext, Optional<DataInputSchema> schema) {
        return schema.map(s -> {
            // TODO when all uris included auth ref, include authref
            processResourceFile(workflow, parserContext, s.getSchema());
            return new JsonSchemaValidatorSupplier(s.getSchema(), s.isFailOnValidationErrors());
        });
    }

    public GeneratedInfo<KogitoWorkflowProcess> getProcessInfo() {
        if (processInfo == null) {
            processInfo = parseProcess();
        }
        return processInfo;
    }

    private void loadConstants(RuleFlowProcessFactory factory, ParserContext parserContext) {
        Constants constants = workflow.getConstants();
        if (constants != null) {
            if (constants.getRefValue() != null) {
                processResourceFile(workflow, parserContext, constants.getRefValue()).ifPresent(bytes -> {
                    try {
                        constants.setConstantsDef(ObjectMapperFactory.get().readValue(bytes, JsonNode.class));
                    } catch (IOException e) {
                        throw new IllegalArgumentException("Invalid file " + constants.getRefValue(), e);
                    }
                });
            }
            factory.metaData(Metadata.CONSTANTS, constants.getConstantsDef());
        }
    }
}
