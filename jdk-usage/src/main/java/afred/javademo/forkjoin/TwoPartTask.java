package afred.javademo.forkjoin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * Created by afred on 16/6/6.
 */
public class TwoPartTask extends RecursiveTask<TwoPartTask.UserInfo> {

    private static final Logger logger = LoggerFactory.getLogger(TwoPartTask.class);

    private final String userName;


    public TwoPartTask(String userName) {
        this.userName = userName;
    }

    @Override
    protected UserInfo compute() {

        logger.debug("start to query user info by user name : {}", userName);


        RecursiveTask<Integer> amountTask = new RecursiveTask<Integer>() {
            @Override
            protected Integer compute() {

                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    logger.error("interrupted exception : {}", userName);
                }

                return 100;
            }
        };

        logger.debug("start amount task");

        amountTask.fork();

        logger.debug("after invoke fork of amount task");

        RecursiveTask<Integer> creditsTask = new RecursiveTask<Integer>() {
            @Override
            protected Integer compute() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    logger.error("interrupted exception : {}", userName);
                }

                return 2;

            }
        };

        creditsTask.fork();

        logger.debug("after invoke fork of credits task");


        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);

        logger.debug("before join of amount task");

        userInfo.setAmount(amountTask.join());

        logger.debug("after join of amount task, and before call credits's join");


        userInfo.setCredits(creditsTask.join());
        logger.debug("after join of credits task");

        return userInfo;
    }

    class UserInfo {
        private int credits;

        private int amount;

        private String userName;

        public int getCredits() {
            return credits;
        }

        public void setCredits(int credits) {
            this.credits = credits;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        @Override
        public String toString() {
            return "UserInfo{" +
                    "credits=" + credits +
                    ", amount=" + amount +
                    ", userName='" + userName + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TwoPartTask task = new TwoPartTask("afred");

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        ForkJoinTask<UserInfo> submit = forkJoinPool.submit(task);

        logger.debug("query user info response : {}", submit.get());
    }

}
