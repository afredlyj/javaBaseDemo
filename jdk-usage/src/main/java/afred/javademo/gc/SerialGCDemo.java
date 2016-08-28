package afred.javademo.gc;

/**
 * Created by winnie on 15/10/15.
 */
public class SerialGCDemo {

    /**
     * -Xms20M -Xmx20M -Xmn10M -XX:+UseSerialGC -XX:+PrintGCDetails
     *
     * @param args
     * @throws InterruptedException
     */

    public static void main(String[] args) throws InterruptedException {

        Thread.sleep(20000);

        onetimeYGC();

//        pretenureSize();
//
//        fullGC();

//        clearReference();
    }




    /**
     * -Xms20M -Xmx20M -Xmn10M -XX:+UseSerialGC -XX:+PrintGCDetails
        DefNew : 单线程Serial年轻代GC
     * @throws InterruptedException
     */
    private static void onetimeYGC() throws InterruptedException {
        byte[] b1 = new byte[2 * 1024 * 1024];
        byte[] b2 = new byte[2 * 1024 * 1024];
        byte[] b3 = new byte[2 * 1024 * 1024];

        Thread.sleep(3000);
        System.out.println("after sleep 3 sec");
        /**
         *  触发一次young gc
         *  eden区8M，当前已经6M，所以b4申请内存时触发gc
         */
        byte[] b4 = new byte[1 * 1024 * 1024];
        System.out.println("after b4");


        Thread.sleep(3000);
        System.out.println("end");

    }


    /**
     * -Xms20M -Xmx20M -Xmn10M -XX:PretenureSizeThreshold=3145728 -XX:+UseSerialGC -XX:+PrintGCDetails
     * @throws InterruptedException
     */
    private static void pretenureSize() throws InterruptedException {
        byte[] b1 = new byte[2 * 1024 * 1024];
        byte[] b2 = new byte[2 * 1024 * 1024];
        byte[] b3 = new byte[2 * 1024 * 1024];

        Thread.sleep(3000);
        System.out.println("after sleep 3 sec");
        /**
         *  没有触发young gc
         *  eden区8M，当前已经6M，所以b4申请内存时，由于超过PretenureSizeThreshold，直接在old区分配
         */
        byte[] b4 = new byte[4 * 1024 * 1024];

        Thread.sleep(9000);
        System.out.println("end");

    }

    /**
     * -Xms20M -Xmx20M -Xmn10M -XX:+UseSerialGC -XX:+PrintGCDetails

     * @throws InterruptedException
     */
    private static void fullGC() throws InterruptedException {
        byte[] b1 = new byte[2 * 1024 * 1024];
        byte[] b2 = new byte[2 * 1024 * 1024];
        byte[] b3 = new byte[2 * 1024 * 1024];

        System.out.println("step 1");
        byte[] b4 = new byte[2 * 1024 * 1024];
        Thread.sleep(3000);
        System.out.println("step 2");


        byte[] b5 = new byte[2 * 1024 * 1024];
        byte[] b6 = new byte[2 * 1024 * 1024];

        System.out.println("step 3");
        byte[] b7 = new byte[2 * 1024 * 1024];
        Thread.sleep(3000);
        System.out.println("end");

    }

    private static void clearReference() throws InterruptedException {
        byte[] b1 = new byte[2 * 1024 * 1024];
        byte[] b2 = new byte[2 * 1024 * 1024];
        byte[] b3 = new byte[2 * 1024 * 1024];

        System.out.println("step 1");
        b1 = null;
        byte[] b4 = new byte[2 * 1024 * 1024];
        Thread.sleep(3000);
        System.out.println("step 2");


        byte[] b5 = new byte[2 * 1024 * 1024];
        byte[] b6 = new byte[2 * 1024 * 1024];
        b4 = null;
        b5 = null;
        b6 = null;

        System.out.println("step 3");
        byte[] b7 = new byte[2 * 1024 * 1024];
        Thread.sleep(30000);
        System.out.println("end");

    }
}
