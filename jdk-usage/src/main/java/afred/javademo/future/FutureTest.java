package afred.javademo.future;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * Created by winnie on 2016-03-27 .
 */
public class FutureTest {
    private static final Logger logger = LoggerFactory.getLogger(FutureTest.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);


        Future<String> submit = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(2);

                return "hello";
            }
        });


        logger.debug("future : {}", submit);

        logger.debug("result : {}", submit.get());
    }

}
