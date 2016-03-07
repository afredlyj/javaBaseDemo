package afred.javademo.concurrent.thread;

import org.apache.commons.lang.RandomStringUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by winnie on 2016-03-01 .
 */
public class Producer extends Thread {

    private final  Queue<String> queue;

    private final  int maxQueueSize;

    public Producer(Queue<String> queue, int maxQueueSize) {
        this.queue = queue;
        this.maxQueueSize = maxQueueSize;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                while (maxQueueSize == queue.size()) {
                    try {
                        System.out.println("队列满了，等待消费者");

                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // 队列空闲，可以添加新数据
                String element = RandomStringUtils.random(2);
                System.out.println("入队列 : " + element);
                queue.add(element);

                queue.notifyAll();

            }
        }
    }

    public static void main(String[] args) {

        LinkedList<String> list = new LinkedList<String>();
        Producer producer = new Producer(list, 5);

        Consumer consumer = new Consumer(list);

        producer.start();

        consumer.start();

    }
}
