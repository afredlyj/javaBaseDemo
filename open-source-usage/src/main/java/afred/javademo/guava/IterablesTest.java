package afred.javademo.guava;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by afred on 16/7/22.
 */
public class IterablesTest {

    @Test
    public void concatTest() {
        List<Integer> list1 = Ints.asList(1, 2, 3);
        List<Integer> list2 = Ints.asList(3, 4, 5);

        Iterable<Integer> concat = Iterables.concat(list1, list2);

        System.out.println("concat result : " + concat);
    }

    @Test
    public void frequency() {
        ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3, 3, 4, 1, 2);

        int frequency = Iterables.frequency(integers, 1);
        Assert.assertTrue(frequency == 2);
        Assert.assertTrue(Iterables.frequency(integers, 4) == 1);
    }

    @Test
    public void partition() {
        ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3, 3, 4, 1, 2);

        Iterable<List<Integer>> partition = Iterables.partition(integers, 2);
        System.out.println(partition);
    }

    @Test
    public void limit() {
        ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3, 3, 4, 1, 2);

        Iterable<Integer> limit = Iterables.limit(integers, 2);
        System.out.println(limit);


    }


}
