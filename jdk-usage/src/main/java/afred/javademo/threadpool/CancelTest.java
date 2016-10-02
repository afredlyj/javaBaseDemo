package afred.javademo.threadpool;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by afred on 16/9/17.
 */
public class CancelTest {


    @Test
    public void cancelTask() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Future<String> submit = executorService.submit(new Callable<String>() {
            @Override
            public String call() {
                System.out.println("start run task ... ");
                try {
                    TimeUnit.SECONDS.sleep(5);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("finish task ... ");

                return "hello";
            }
        });

        try {
            submit.get(1, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            System.out.println("task time out");
        } catch (InterruptedException e) {
            System.out.println("task interrupted");
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            // 如果任务已经完成,cancel不会带来任何影响
            // 如果任务异常退出,不再需要结果,则可以取消任务,较少资源消耗
            submit.cancel(true);
            System.out.println("task cancel");
        }

    }

}
