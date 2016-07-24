package afred.javademo.guava;

import com.google.common.base.MoreObjects;
import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;

/**
 * Created by afred on 16/7/20.
 */
public class ObjectsTest {

    @Test
    public void equalTest() {
        Assert.assertTrue(!Objects.equals(null, 1));
    }

    class Student {
        private int age;

        private String name;

    }

    @Test
    public void toStringTest() {
        System.out.println(Objects.toString("hello"));

        System.out.println(Objects.toString(null, "hello"));

        System.out.println(MoreObjects.toStringHelper(Student.class).add("hello", "world"));
    }

}
