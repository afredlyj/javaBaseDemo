package afred.demo.btrace;

import java.util.concurrent.TimeUnit;

/**
 * Created by winnie on 16/1/16.
 */
public class Counter {

    private static int count = 0;

    public int add(int num) throws Exception {

        count += num;

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
}
