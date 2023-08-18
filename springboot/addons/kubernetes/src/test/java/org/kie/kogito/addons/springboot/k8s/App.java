package org.kie.kogito.addons.springboot.k8s;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.kie.kogito.addons.springboot.k8s.**")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
