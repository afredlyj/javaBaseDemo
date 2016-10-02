package afred.javademo.concurrent.delayqueue;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by afred on 16/9/1.
 */
public class DelayQueueProducer {

    private BlockingQueue<DelayObject> queue;

    private final Random random = new Random();

    public DelayQueueProducer(BlockingQueue queue) {
        this.queue = queue;
    }

    private Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
//                int delay = random.nextInt(10000);
                DelayObject delayObject = new DelayObject(UUID.randomUUID().toString(), 100000 + System.currentTimeMillis());

                System.out.println("delay object : " + delayObject + ", " + System.currentTimeMillis());

                queue.offer(delayObject);
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    });

    public void start(){
        this.thread.start();
    }
}
