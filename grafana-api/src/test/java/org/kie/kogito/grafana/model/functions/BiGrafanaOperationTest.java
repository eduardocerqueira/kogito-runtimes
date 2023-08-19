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
package org.kie.kogito.grafana.model.functions;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BiGrafanaOperationTest {

    @Test
    public void testBiGrafanaOperation() {
        // Arrange
        GrafanaFunction firstBaseExpression = new BaseExpression("prefix1", "suffix1");
        GrafanaFunction secondBaseExpression = new BaseExpression("prefix2", "suffix2");
        GrafanaFunction biGrafanaOperation = new BiGrafanaOperation(GrafanaOperation.DIVISION, firstBaseExpression, secondBaseExpression);

        List<Label> labels = Collections.singletonList(new Label("key", "value"));

        // Act
        String result = biGrafanaOperation.render("body", labels);

        // Assert
        assertThat(result).isEqualTo("(prefix1_body_suffix1{key=value})/(prefix2_body_suffix2{key=value})");
    }
}
