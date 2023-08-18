package org.kie.kogito.it;

import org.kie.kogito.testcontainers.springboot.MongoDBSpringBootTestResource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = KogitoSpringbootApplication.class, properties = { "kogito.persistence.optimistic.lock=true" })
@ContextConfiguration(initializers = MongoDBSpringBootTestResource.class)
public class MongoDBOptimisticLockingIT extends OptimisticLockingTest {

}