package org.kie.kogito.integrationtests;

import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class HelloService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloService.class);

    public String hello(String name) {
        logMethodCall("hello", name);
        return "Hello " + name + "!";
    }

    public JsonNode jsonHello(JsonNode person) {
        logMethodCall("jsonHello", person);

        String retJsonStr = "{\"result\":\"Hello " + person.get("name").textValue() + "\"}";
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readTree(retJsonStr);
        } catch (Exception e) {
            return null;
        }
    }

    public String goodbye(String name) {
        logMethodCall("goodbye", name);
        return "Goodbye " + name + "!";
    }

    public String helloMulti(String name, String lastName) {
        logMethodCall("helloMulti", name, lastName);
        return "Hello (first and lastname) " + name.concat(" ").concat(lastName).concat("!");
    }

    public void helloNoOutput(String name, Integer age) {
        logMethodCall("helloNoOutput", name, age);
    }

    public String helloOutput(String name, Integer age) {
        logMethodCall("helloOutput", name, age);
        return "Hello " + name.concat(" ").concat(String.valueOf(age)).concat("!");
    }

    private static void logMethodCall(String method, Object... arguments) {
        LOGGER.info("HelloService.{} invoked with params: {}", method, arguments);
    }

}
