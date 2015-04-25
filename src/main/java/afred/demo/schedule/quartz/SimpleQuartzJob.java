package afred.demo.schedule.quartz;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Afred on 15/3/12.
 */
public class SimpleQuartzJob implements Job {

    private final static Logger logger = LoggerFactory.getLogger(SimpleQuartzJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        Trigger trigger = jobExecutionContext.getTrigger();
        logger.info("trigger name : {}", trigger.getName());

    }
}
