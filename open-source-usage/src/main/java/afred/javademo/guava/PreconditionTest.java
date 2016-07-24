package afred.javademo.guava;

import com.google.common.base.Preconditions;
import org.junit.Test;

/**
 * Created by afred on 16/7/20.
 */
public class PreconditionTest {

    @Test
    public void test() {

        Preconditions.checkArgument(false, "hello");

        Preconditions.checkElementIndex(10, 10);
    }

}
