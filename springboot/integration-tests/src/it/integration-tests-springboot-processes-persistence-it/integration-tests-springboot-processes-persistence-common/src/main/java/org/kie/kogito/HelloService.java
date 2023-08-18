package org.kie.kogito;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloService.class);

    public String hello(String name) throws IOException {
        if ("exception".equals(name)) {
            throw new IOException("what kind of name is that?");
        }
        logMethodCall("hello", name);
        return "Hello " + name + "!";
    }

    private static void logMethodCall(String method, Object... arguments) {
        LOGGER.info("HelloService.{} invoked with params: {}", method, arguments);
    }
}
