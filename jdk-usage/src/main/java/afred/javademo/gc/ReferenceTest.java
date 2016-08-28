package afred.javademo.gc;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by winnie on 15/12/2.
 */
public class ReferenceTest {


    public static void main(String[] args) {
        ReferenceTask task = new ReferenceTask();

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);


        scheduledExecutorService.scheduleWithFixedDelay(task, 0, 5, TimeUnit.SECONDS);


    }

}
