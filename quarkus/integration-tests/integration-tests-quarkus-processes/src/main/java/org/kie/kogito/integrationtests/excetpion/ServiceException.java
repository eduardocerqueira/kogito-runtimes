package org.kie.kogito.integrationtests.excetpion;

public class ServiceException extends RuntimeException {

    public ServiceException(String message) {
        super(message);
    }
}
