package afred.javademo.hystrix.circuitbreaker;

import com.netflix.hystrix.strategy.HystrixPlugins;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import afred.javademo.hystrix.circuitbreaker.impl.UserInfoImpl;
import afred.javademo.hystrix.plugins.MyEventNotifier;

/**
 * Created by afred on 16/5/28.
 */
public class TestHystrix {

    private static final Logger logger = LoggerFactory.getLogger(TestHystrix.class);

    @org.junit.Test
    public void circuit() throws InterruptedException {

        HystrixPlugins.getInstance().registerEventNotifier(new MyEventNotifier());


        UserInfoCommand command = null;
//        try {
//            String result = command.execute();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        command = new UserInfoCommand(1, true);
//
//        try {
//            String result = command.execute();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        command = new UserInfoCommand(1, true);
//
//        try {
//            String result = command.execute();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//

        int count = 0;

        while (count < 100) {

            count++;
            if (count > 40) {
                command = new UserInfoCommand(count, false);

                try {
                    String result = command.execute();
                    System.out.println("result : " + result);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

//            else if (count > 5) {
//                command = new UserInfoCommand(count, true);
//
//                try {
//                    String result = command.execute();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }

            else {
                TimeUnit.SECONDS.sleep(1);
                command = new UserInfoCommand(count, true);

                try {
                    String result = command.execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @org.junit.Test
    public void errorThresholdPercentage() {

        int count = 200;
        for (int i = 0; i < count; i++) {
            UserInfoCommand command = new UserInfoCommand(i, true);

            try {
                String result = command.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }

            logger.debug("command : {}, {}", i, command.getMetrics().getHealthCounts());

//            if (i <= )
        }

        logger.debug("run method : {}", UserInfoCommand.runTimes());

        logger.debug("fallback method : {}", UserInfoCommand.fallbackTimes());

    }

    @org.junit.Test
    public void userInfoService() throws InterruptedException {
        UserInfoImpl useInfoService = new UserInfoImpl();


//        for (int i = 0; i < 20; i++) {
//            UserInfoData infoData = useInfoService.queryUserInfo(i);
//            logger.debug("result : {}", infoData);
//        }

        useInfoService.setRpcfail(true);
        for (int i = 0; i < 2000; i++) {

            UserInfoData infoData = useInfoService.queryUserInfo(i);

            logger.debug("result : {}", infoData);

//            TimeUnit.SECONDS.sleep(1);
        }

    }

}
