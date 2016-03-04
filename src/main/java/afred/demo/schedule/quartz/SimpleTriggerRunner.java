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

        simpleTrigger.setRepeatInterval(1000);
        simpleTrigger.setRepeatCount(10);

        simpleTrigger.setEndTime(new Date(current + 60000));

        scheduler.scheduleJob(detail, simpleTrigger);

        scheduler.start();

    }

    public static void main(String[] args) {
        String str =
                "\"{\\\"createBy\\\":\\\"lyj\\\",\\\"createTime\\\":1449137523000,\\\"resultCode\\\":0,\\\"templateCode\\\":\\\"updatepassword_sms\\\",\\\"templateDesc\\\":\\\"\\xe5\\xaf\\x86\\xe7\\xa0\\x81\\xe5\\x8f\\x98\\xe6\\x9b\\xb4\\xe9\\x80\\x9a\\xe7\\x9f\\xa5\\\",\n"
                        + "\\\"templateId\\\":2371,\n"
                        + "\\\"templateMsg\\\":\\\"\\xe6\\x82\\xa8\\xe7\\x9a\\x84\\xe8\\xb4\\xa6\\xe5\\x8f\\xb7\\xef\\xbc\\x88XXX\\xef\\xbc\\x89\\xe7\\x99\\xbb\\xe5\\xbd\\x95\\xe5\\xaf\\x86\\xe7\\xa0\\x81\\xe5\\x8f\\x91\\xe7\\x94\\x9f\\xe5\\x8f\\x98\\xe5\\x8a\\xa8\\xef\\xbc\\x8c\\xe6\\x84\\x9f\\xe8\\xb0\\xa2\\xe6\\x82\\xa8\\xe4\\xbd\\xbf\\xe7\\x94\\xa8OPPO\\xe5\\x9c\\xa8\\xe7\\xba\\xbf\\xe6\\x9c\\x8d\\xe5\\x8a\\xa1\\xe3\\x80\\x82\\\",\\\"templateType\\\":\\\"SMS\\\",\\\"updateBy\\\":\\\"lyj\\\",\\\"updateTime\\\":1450080119000}\"";

        System.out.println(str);
    }

//    public static void main(String[] args) throws SchedulerException {
//        new SimpleTriggerRunner().task();
//
//    }

}
