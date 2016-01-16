package afred.demo.schedule.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by Afred on 15/3/12.
 */
public class SimpleTriggerRunner {

    private static final Logger logger = LoggerFactory.getLogger(SimpleTriggerRunner.class);

    public void task() throws SchedulerException {

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();

        // retrieve a scheduler from factory
        Scheduler scheduler = schedulerFactory.getScheduler();

        //
        long current = System.currentTimeMillis();

        JobDetail detail = new JobDetail("job-detail", "job-detail-group", SimpleQuartzJob.class);

        SimpleTrigger simpleTrigger = new SimpleTrigger("simple-trigger", "simple-trigger-group");
        simpleTrigger.setStartTime(new Date(current));

        simpleTrigger.setRepeatInterval(10000);
        simpleTrigger.setRepeatCount(0);

        simpleTrigger.setEndTime(new Date(current + 60000));

        scheduler.scheduleJob(detail, simpleTrigger);

        scheduler.start();

    }

    public static void main(String[] args) throws SchedulerException {
        new SimpleTriggerRunner().task();

    }

}
