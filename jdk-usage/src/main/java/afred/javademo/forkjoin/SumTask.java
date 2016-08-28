package afred.javademo.forkjoin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created by afred on 16/6/5.
 */
public class SumTask extends RecursiveTask<Long> {

    private static final int THRESHOLD = 10;

    private long start;

    private long end;


    private static final Logger logger = LoggerFactory.getLogger(SumTask.class);

    public SumTask(long end) {
        this(1, end);

    }

    public SumTask(long start, long end) {

        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {

        logger.debug("start : {}, end : {}", start, end);

        long sum = 0;
        if (end - start < THRESHOLD) {

            for (long i = start; i <=end; i++) {
                sum += i;
            }

        } else {
            long mid = (start + end) >>> 1;
            SumTask left = new SumTask(start, mid);

            SumTask right = new SumTask(mid + 1, end);
            left.fork();
            right.fork();

            sum = left.join() + right.join();
        }


        return sum;
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        SumTask sumTask = new SumTask(100);

        SumTask sumTask1 = new SumTask(200);

        ForkJoinTask<Long> task = forkJoinPool.submit(sumTask);

        ForkJoinTask<Long> task1 = forkJoinPool.submit(sumTask1);

        Long result = task.get();

        System.out.println("result : " + result);

        System.out.println("result1 : " + task1.get());
    }
}
