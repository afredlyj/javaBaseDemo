package afred.javademo.guava;

import com.google.common.base.Throwables;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import org.junit.Test;

/**
 * Created by afred on 16/7/20.
 */
public class OrderingTest {

    @Test
    public void selfDefine() {

        Ordering<String> byLengthOrdering = new Ordering<String>() {
            public int compare(String left, String right) {
                return Ints.compare(left.length(), right.length());
            }
        };

        String min = byLengthOrdering.min("1000", "222");
        System.out.println(min);


    }

}
