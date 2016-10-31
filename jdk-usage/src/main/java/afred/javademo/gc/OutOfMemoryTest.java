package afred.javademo.gc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created with demo. 
 * User: liyuanjun(80059138) 
 * Date: 2015-07-31 
 * Time: 17:59
 */
public class OutOfMemoryTest {

    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<String>();
        while (true) {
            byte[] bs = new byte[1024];
            list.add(new String(bs));

            TimeUnit.SECONDS.sleep(1);
        }
    }

}
