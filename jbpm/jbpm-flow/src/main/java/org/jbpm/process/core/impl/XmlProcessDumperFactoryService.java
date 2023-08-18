package org.jbpm.process.core.impl;

import org.kie.api.internal.utils.KieService;

public interface XmlProcessDumperFactoryService extends KieService {

    XmlProcessDumper newXmlProcessDumper();

}
