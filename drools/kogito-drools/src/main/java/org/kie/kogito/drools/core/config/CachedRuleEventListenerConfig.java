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
package org.kie.kogito.drools.core.config;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.event.rule.AgendaEventListener;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.kie.kogito.rules.RuleEventListenerConfig;

public class CachedRuleEventListenerConfig implements RuleEventListenerConfig {

    private final List<AgendaEventListener> agendaListeners;
    private final List<RuleRuntimeEventListener> ruleRuntimeListeners;

    public CachedRuleEventListenerConfig() {
        agendaListeners = new ArrayList<>();
        ruleRuntimeListeners = new ArrayList<>();
    }

    public CachedRuleEventListenerConfig(List<AgendaEventListener> agendaListeners, List<RuleRuntimeEventListener> ruleRuntimeListeners) {
        this.agendaListeners = agendaListeners;
        this.ruleRuntimeListeners = ruleRuntimeListeners;
    }

    public CachedRuleEventListenerConfig register(AgendaEventListener listener) {
        agendaListeners.add(listener);
        return this;
    }

    public CachedRuleEventListenerConfig register(RuleRuntimeEventListener listener) {
        ruleRuntimeListeners.add(listener);
        return this;
    }

    @Override
    public List<AgendaEventListener> agendaListeners() {
        return agendaListeners;
    }

    @Override
    public List<RuleRuntimeEventListener> ruleRuntimeListeners() {
        return ruleRuntimeListeners;
    }

}
