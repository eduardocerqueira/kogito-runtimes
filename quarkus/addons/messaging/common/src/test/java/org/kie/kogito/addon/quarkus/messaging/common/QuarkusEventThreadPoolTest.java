package org.kie.kogito.addon.quarkus.messaging.common;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.jcip.annotations.NotThreadSafe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@NotThreadSafe
public class QuarkusEventThreadPoolTest {

    private static final String CHANNEL_NAME = "nevermind";

    private class CounterQuarkusEmitterController extends BackpressureKogitoEmitter {
        private AtomicInteger stopCounter = new AtomicInteger(0);
        private AtomicInteger resumeCounter = new AtomicInteger(0);

        @Override
        public boolean resume(String channelName) {
            boolean result = super.resume(channelName);
            if (result) {
                resumeCounter.incrementAndGet();
            }
            return result;
        }

        @Override
        public boolean stop(String channelName) {
            boolean result = super.stop(channelName);
            if (result) {
                stopCounter.incrementAndGet();
            }
            return result;
        }
    }

    private CounterQuarkusEmitterController controller;

    @BeforeEach
    void setup() {
        controller = new CounterQuarkusEmitterController();
    }

    @Test
    void testQuarkusEventThreadPoolSingleThreadTest() throws InterruptedException, ExecutionException {
        testIt(1, 1, 10);
        assertStop();
    }

    @Test
    void testQuarkusEventThreadPoolMultiThreadTest() throws InterruptedException, ExecutionException {
        testIt(10, 1, 100);
        assertStop();
    }

    @Test
    void testQuarkusEventThreadPoolLongQueueTest() throws InterruptedException, ExecutionException {
        testIt(1, 10, 20);
        assertStop();
    }

    @Test
    void testQuarkusEventThreadPoolMultiThreadNotStopTest() throws InterruptedException, ExecutionException {
        testIt(10, 1, 10);
        assertNotStop();
    }

    @Test
    void testQuarkusEventThreadPoolLongQueueNotStopTest() throws InterruptedException, ExecutionException {
        testIt(1, 10, 10);
        assertNotStop();
    }

    @Test
    void testQuarkusEventThreadPoolMultiThreadLongQueueNotStopTest() throws InterruptedException, ExecutionException {
        testIt(10, 100, 100);
        assertNotStop();
    }

    @Test
    void testQuarkusEventThreadPoolMultiThreadLongQueueTest() throws InterruptedException, ExecutionException {
        testIt(10, 10, 10000);
        assertStop();
    }

    private void testIt(int numThreads, int queueSize, int count) throws InterruptedException, ExecutionException {
        ExecutorService executor = new QuarkusEventThreadPool(numThreads, queueSize, controller, CHANNEL_NAME);
        final AtomicInteger counter = new AtomicInteger(0);
        List<Callable<Integer>> runnables = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            final int temp = i;
            runnables.add(() -> {
                counter.incrementAndGet();
                return temp + 1;
            });
        }
        List<Future<Integer>> answers = executor.invokeAll(runnables);
        assertEquals(count, counter.get());
        for (int i = 0; i < answers.size(); i++) {
            assertEquals(i + 1, answers.get(i).get());
        }
    }

    private void assertNotStop() {
        assertEquals(0, controller.stopCounter.get());
        assertEquals(0, controller.resumeCounter.get());
    }

    private void assertStop() {
        assertNotEquals(0, controller.stopCounter.get());
        assertNotEquals(0, controller.resumeCounter.get());
        assertEquals(controller.stopCounter.get(), controller.resumeCounter.get());
    }
}
