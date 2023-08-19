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
package org.jbpm.compiler.xml.processes;

import java.util.HashSet;

import org.jbpm.compiler.xml.Handler;
import org.jbpm.compiler.xml.Parser;
import org.jbpm.compiler.xml.core.BaseAbstractHandler;
import org.jbpm.workflow.core.node.CompositeNode;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class InPortHandler extends BaseAbstractHandler
        implements
        Handler {
    public InPortHandler() {
        if ((this.validParents == null) && (this.validPeers == null)) {
            this.validParents = new HashSet();
            this.validParents.add(CompositeNode.class);

            this.validPeers = new HashSet();
            this.validPeers.add(null);

            this.allowNesting = false;
        }
    }

    public Object start(final String uri,
            final String localName,
            final Attributes attrs,
            final Parser parser) throws SAXException {
        parser.startElementBuilder(localName,
                attrs);
        CompositeNode compositeNode = (CompositeNode) parser.getParent();
        final String type = attrs.getValue("type");
        emptyAttributeCheck(localName, "type", type, parser);
        final String nodeId = attrs.getValue("nodeId");
        emptyAttributeCheck(localName, "nodeId", nodeId, parser);
        final String nodeInType = attrs.getValue("nodeInType");
        emptyAttributeCheck(localName, "nodeInType", nodeInType, parser);
        compositeNode.linkIncomingConnections(type, new Long(nodeId), nodeInType);
        return null;
    }

    public Object end(final String uri,
            final String localName,
            final Parser parser) throws SAXException {
        parser.endElementBuilder();
        return null;
    }

    public Class generateNodeFor() {
        return null;
    }

}
