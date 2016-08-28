package afred.javademo.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Afred on 15/7/29.
 */
public class SyncTest {

    /**
     * 题目：有三个线程分别打印A、B、C,请用多线程编程实现，在屏幕上循环打印10次ABCABC…
     * @param args
     */
    public static void main(String[] args) {

        PrintControl control = new PrintControl();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new PrintTask(control, 'A'));
        service.execute(new PrintTask(control, 'B'));
        service.execute(new PrintTask(control, 'C'));

        service.shutdown();
    }


}

class PrintControl {

    private char currentPrintChar = 'A';

    public boolean shouldPrint(PrintTask task) {
        return task.getValue() == currentPrintChar;
    }

    public void print(PrintTask task) {
        System.out.println(task.getValue());

        generateNextPrintChar(task);
    }

    private void generateNextPrintChar(PrintTask task) {

        switch (task.getValue()) {
            case 'A':
                currentPrintChar = 'B';
                break;
            case 'B':
                currentPrintChar = 'C';
                break;
            case 'C':
                currentPrintChar = 'A';
                System.out.println();
        }

    }

}

class PrintTask implements Runnable {

    private char value;

    private PrintControl control;


    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public PrintControl getControl() {
        return control;
    }

    public void setControl(PrintControl control) {
        this.control = control;
    }

    public PrintTask(PrintControl control, char value) {
        this.value = value;
        this.control = control;
    }

    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {
            synchronized (control) {

                while (!control.shouldPrint(this)) {
                    try {
                        control.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                control.print(this);
                control.notifyAll();
            }

        }
    }
}
