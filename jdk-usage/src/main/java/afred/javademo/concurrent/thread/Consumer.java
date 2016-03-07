package afred.javademo.concurrent.thread;

import java.util.Queue;

/**
 * Created by winnie on 2016-03-01 .
 */
public class Consumer extends Thread {

    private final Queue<String> queue;

    public Consumer(Queue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                while (queue.isEmpty()) {
                    try {
                        System.out.println("队列空了，等待生产者");
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                String element = queue.remove();
                System.out.println("出队列 : " + element);
                queue.notifyAll();
            }
        }

    }
}
