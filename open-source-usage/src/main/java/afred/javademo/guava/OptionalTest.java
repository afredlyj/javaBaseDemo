package afred.javademo.guava;

import com.google.common.base.Objects;
import com.google.common.base.Optional;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

/**
 * Created by afred on 16/7/20.
 */
public class OptionalTest {

    @Test
    public void of() {

        Optional<Integer> of = Optional.of(5);
        Assert.assertTrue(of.isPresent());

        try {
            Optional.of(null);
            Assert.fail();
        } catch (NullPointerException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void absent() {
        Optional<Integer> absent = Optional.absent();

        Assert.assertTrue(!absent.isPresent());
    }

    @Test
    public void fromNullable() {

        Optional<Integer> optional = Optional.fromNullable(1);
        Assert.assertTrue(optional.isPresent());

        Assert.assertTrue(optional.get() == 1);

        optional = Optional.fromNullable(null);
        Assert.assertTrue(!optional.isPresent());

        try {
            optional.get();
        } catch (IllegalStateException e) {
            Assert.assertTrue(true);

        }
    }

    @Test
    public void query() {
        Optional<Integer> optional = Optional.fromNullable(1);

        Integer or = optional.or(2);
        Assert.assertTrue(or == 1);

        optional = Optional.fromNullable(null);
        or = optional.or(2);
        Assert.assertTrue(or == 2);


        Assert.assertTrue(optional.orNull() == null);

    }



}
