package afred.demo.gc;

/**
 * Created by winnie on 15/10/16.
 */
public class SerialGCThreshold {

    public static void main(String[] args) throws InterruptedException {

        Thread.sleep(20000);


        System.out.println("start ... ");

        SerialGCMemoryObject o1 = new SerialGCMemoryObject(1);
        SerialGCMemoryObject o2 = new SerialGCMemoryObject(8);
        SerialGCMemoryObject o3 = new SerialGCMemoryObject(8);
        SerialGCMemoryObject o4 = new SerialGCMemoryObject(8);
        // 1024 * 256 * 25 = 6M++
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

        o2 = null;
        o3 = null;
        o5 = null;

        System.out.println("before o6");
        SerialGCMemoryObject o6 = new SerialGCMemoryObject(8);
//
        Thread.sleep(5000);


    }

    private static void defaultMaxTenuringThreshold() {

    }


}


class SerialGCMemoryObject {
    private byte[] bytes = null;

    public SerialGCMemoryObject(int multi) {
        bytes = new byte[1024 * 256 * multi];
    }
}