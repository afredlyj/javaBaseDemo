package afred.javademo.guava;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.HashSet;

/**
 * Created by afred on 16/5/26.
 */
public class CollectionsTest {
    @Test
    public void immutable() {


        HashSet<Object> set = Sets.newHashSet();
        set.add("hello");
        set.add("world");
        ImmutableSet<Object> immutableSet = ImmutableSet.copyOf(set);

        set.add("afred");

        System.out.println(set);
        System.out.println(immutableSet);


        immutableSet.add("hello");
    }
}
