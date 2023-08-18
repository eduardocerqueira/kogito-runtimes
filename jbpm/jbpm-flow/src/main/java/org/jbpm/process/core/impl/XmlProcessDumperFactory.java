package org.jbpm.process.core.impl;

import org.kie.api.internal.utils.KieService;

public class XmlProcessDumperFactory {

    public static XmlProcessDumper newXmlProcessDumperFactory() {
        return getXmlProcessDumperFactoryService().newXmlProcessDumper();
    }

    public static XmlProcessDumperFactoryService getXmlProcessDumperFactoryService() {
        return LazyHolder.service;
    }

    private static class LazyHolder {
        private static final XmlProcessDumperFactoryService service = KieService.load(XmlProcessDumperFactoryService.class);
    }

    private XmlProcessDumperFactory() {
        // It is not allowed to create instances of util classes.
    }
}
