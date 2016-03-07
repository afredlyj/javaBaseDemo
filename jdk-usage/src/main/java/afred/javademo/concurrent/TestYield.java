package afred.demo.concurrent;

/**
 * Created by winnie on 2015-05-11 .
 */
public class TestYield {

    static class LiftOff implements Runnable {

        protected int countDown = 10;

        private static int taskCount = 10;

        private final int id = taskCount++;

        public LiftOff(int countDown) {
            this.countDown = countDown;
        }

        public String status() {
            return ("# " + id + ", " + (countDown > 0 ? countDown : " off!"));
        }

        @Override
        public void run() {
            while (countDown-- > 0) {
                System.out.println("status : " + status());
//                Thread.yield();
            }
        }
    }

    public static void main(String[] args) {
//        Thread t = new Thread(new LiftOff(20));
//        t.start();
//        System.out.println("waiting for lift off!");

//        int count = 0;
//        for (int i = 0; i <= 999999; i++) {
//            String str = String.valueOf(i);
//            if (str.contains("1")) {
//                continue;
//            }
//            count++;
//        }
//        System.out.printf("count : " +  count);

        

    }

}
