package org.kie.kogito.it;

import org.kie.kogito.testcontainers.springboot.MongoDBSpringBootTestResource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = KogitoSpringbootApplication.class)
@ContextConfiguration(initializers = MongoDBSpringBootTestResource.class)
public class MongoDBPersistenceIT extends PersistenceTest {

}