package afred.javademo.concurrent;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by winnie on 2016-03-07 .
 */
public class ExchangerDemo {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);

        final Exchanger<String> exchanger = new Exchanger<>();
        service.execute(new Runnable() {
            @Override
            public void run() {

                System.out.println(Thread.currentThread().getName() + " start to work");
                try {
                    exchanger.exchange("hello world");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        service.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String exchange = exchanger.exchange("hi, afred");
                    System.out.printf(Thread.currentThread().getName() + " receive " + exchange);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        service.shutdown();
    }

}
