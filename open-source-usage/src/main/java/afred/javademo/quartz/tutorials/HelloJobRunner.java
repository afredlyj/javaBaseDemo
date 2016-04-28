package afred.javademo.quartz.tutorials;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

/**
 * Created by winnie on 2016-04-27 .
 */
public class HelloJobRunner {

    public static void main(String[] args) throws SchedulerException {

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        JobDetail jobDetail = newJob(HelloJob.class).withIdentity("myJob", "group").build();

        Trigger trigger = newTrigger().withIdentity("myTrigger", "group").startNow().withSchedule(simpleSchedule().withIntervalInSeconds(10).repeatForever()).build();

        scheduler.scheduleJob(jobDetail, trigger);

        scheduler.start();
    }

}
