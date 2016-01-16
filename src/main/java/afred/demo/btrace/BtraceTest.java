package afred.demo.btrace;

import org.apache.commons.lang.math.RandomUtils;

/**
 * Created by winnie on 16/1/16.
 */
public class BtraceTest {

    public static void main(String[] args) throws Exception {


        System.out.println("start ... ");
        Counter counter = new Counter();
        while (true) {
            int random = RandomUtils.nextInt(100);
            counter.add(random);
        }
    }
}
