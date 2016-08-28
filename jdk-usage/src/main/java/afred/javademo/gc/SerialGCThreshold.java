package afred.javademo.gc;

/**
 * Created by winnie on 15/10/16.
 */
public class SerialGCThreshold {

    /**
     * -Xms20M -Xmx20M -Xmn10M -XX:+UseSerialGC -XX:+PrintGCDetails -XX:+PrintTenuringDistribution ＋XX:＋PrintHeapAtGC
     * eden : 8m
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

//        System.out.printf("hello world");

//        defaultMaxTenuringThreshold();

        tenuringTheshold(5);
    }

    private static void tenuringTheshold(int counter) throws InterruptedException {

        for (int i = 0; i < counter; i++) {
            SerialGCMemoryObject o1 = new SerialGCMemoryObject(1);
            SerialGCMemoryObject o2 = new SerialGCMemoryObject(8);
            Thread.sleep(4000);

        }

        Thread.sleep(4000);
    }

    private static void defaultMaxTenuringThreshold() throws InterruptedException {
        Thread.sleep(2000);

        System.out.println("start ... ");

        SerialGCMemoryObject o1 = new SerialGCMemoryObject(1);
        System.out.println("before o2");
        SerialGCMemoryObject o2 = new SerialGCMemoryObject(8);
        System.out.println("before o3");

        //
        SerialGCMemoryObject o3 = new SerialGCMemoryObject(8);
        System.out.println("before o4");

        // 1024 ＊ 256 ＊ 17  = 4M++  ＋  (main 函数启动会占用2M内存？)
        // 创建o4之时，由于eden区内存已经使用6M++，此时没有更多可用空间留给o4
        // 触发YGC
        SerialGCMemoryObject o4 = new SerialGCMemoryObject(8);
        // 1024 * 256 * 25 = 6M++ ＋ (main 函数启动会占用2M内存？)
        o2 = null;
        o3 = null;

        Thread.sleep(4000);

        System.out.println("before o5");
        SerialGCMemoryObject o5 = new SerialGCMemoryObject(8);
        System.out.println("after o5");

        Thread.sleep(4000);

        System.out.println("before o2");
        o2 = new SerialGCMemoryObject(8);

        System.out.println("after o2");
        o3 = new SerialGCMemoryObject(8);
        // eden : 6M++
        // from space :


        o2 = null;
        o3 = null;
        o5 = null;

        System.out.println("before o6");
        SerialGCMemoryObject o6 = new SerialGCMemoryObject(8);
//
        Thread.sleep(5000);

    }


}


class SerialGCMemoryObject {
    private byte[] bytes = null;

    public SerialGCMemoryObject(int multi) {
        bytes = new byte[1024 * 256 * multi];
    }
}