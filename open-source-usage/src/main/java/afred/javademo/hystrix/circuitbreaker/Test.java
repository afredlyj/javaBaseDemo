package afred.javademo.hystrix.circuitbreaker;

import afred.javademo.hystrix.circuitbreaker.impl.UserInfoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by afred on 16/5/28.
 */
public class Test {

    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    @org.junit.Test
    public void circuit() throws InterruptedException {

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
            if (count > 5) {
                command = new UserInfoCommand(count, true);

                try {
                    String result = command.execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
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

        int count = 100;
        for (int i = 0; i < count; i++) {
            UserInfoCommand command = new UserInfoCommand(i, true);

            try {
                String result = command.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        logger.debug("run method : {}", UserInfoCommand.runTimes());

        logger.debug("fallback method : {}", UserInfoCommand.fallbackTimes());

    }

    @org.junit.Test
    public void userInfoService() {
        UserInfoImpl useInfoService = new UserInfoImpl();


//        for (int i = 0; i < 20; i++) {
//            UserInfoData infoData = useInfoService.queryUserInfo(i);
//            logger.debug("result : {}", infoData);
//        }

        useInfoService.setRpcfail(true);
        for (int i = 0; i < 30; i++) {

            UserInfoData infoData = useInfoService.queryUserInfo(i);

            logger.debug("result : {}", infoData);
        }

    }

}
