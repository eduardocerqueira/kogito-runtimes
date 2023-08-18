package org.kie.kogito.integrationtests.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"org.acme.travels.**", "org.kie.dmn.kogito.**", "org.kie.kogito.**", "com" +
		".example.**", "org.acme.examples.**", "http*.**"})
public class KogitoSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(KogitoSpringbootApplication.class, args);
	}
}
