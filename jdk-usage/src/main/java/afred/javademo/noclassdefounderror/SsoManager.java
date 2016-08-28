package afred.javademo.noclassdefounderror;

/**
 * Created by Afred on 15/4/25.
 */
public class SsoManager {

    static {

//        try {
            int f = 1 % 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    public void test() {
        System.out.printf("hello world");
    }

}
