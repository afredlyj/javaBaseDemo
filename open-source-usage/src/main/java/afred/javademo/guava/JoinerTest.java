package afred.javademo.guava;

import com.google.common.base.Joiner;
import org.junit.Test;

/**
 * Created by afred on 16/7/24.
 */
public class JoinerTest {

    @Test
    public void sample() {
        Joiner joiner = Joiner.on(",").skipNulls();

        String join = joiner.join("hello", "world");

        System.out.println(join);
    }

}
