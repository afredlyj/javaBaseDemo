package afred.javademo.concurrent.lock;

import com.google.common.collect.Lists;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by afred on 17/2/19.
 */
public class FairAndUnFairTest {

    private static final Logger logger = LoggerFactory.getLogger(FairAndUnFairTest.class);

    private static class Job extends Thread {
        private ReentrantLock2 lock;

        public Job(ReentrantLock2 lock) {
            this.lock = lock;
        }

        public void run() {
            logger.info("current thread : {}, threads : {}", Thread.currentThread(), lock.getQueuedThreads());
        }

    }

    private static class ReentrantLock2 extends ReentrantLock {
        public ReentrantLock2(boolean fair) {
            super(fair);
        }

        @Override
        protected Collection<Thread> getQueuedThreads() {
            ArrayList<Thread> threads = Lists.newArrayList(super.getQueuedThreads());
            Collections.reverse(threads);
            return threads;
        }

    }

    private static ReentrantLock2 fairLock = new ReentrantLock2(true);

    private static ReentrantLock2 unfairLock = new ReentrantLock2(false);

    @Test
    public void fair() {
        testLock(fairLock);
    }

    @Test
    public void unfair() {
        testLock(unfairLock);
    }

    private void testLock(ReentrantLock2 lock2) {

        new Job(lock2).start();
        new Job(lock2).start();
        new Job(lock2).start();
        new Job(lock2).start();
        new Job(lock2).start();

    }

}
