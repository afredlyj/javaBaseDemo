package afred.javademo.concurrent.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by winnie on 2016-02-29 .
 */
public class JoinTest {

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new Task("t1", null));
        Thread t2 = new Thread(new Task("t2",t1));
        Thread t3 = new Thread(new Task("t3", t2));

        t3.start();
        t2.start();
        t1.start();
        t3.join();
    }

    static class Task implements Runnable {

        private String name;

        private Thread dependencyThread;

        public Task(String name, Thread dependencyThread) {
            this.name = name;
            this.dependencyThread = dependencyThread;
        }

        public void run() {
            System.out.println("当前线程[" + Thread.currentThread().getName() + "]执行任务" + name);

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            if (dependencyThread != null) {
                try {
                    dependencyThread.join();
                    System.out.println("任务" + name + "执行完成");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
