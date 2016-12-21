package afred.javademo.classloader;

import org.junit.Test;

import java.sql.Driver;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Created by afred on 16/12/4.
 */
public class TestThreadContextClassLoader {


    @Test
    public void testContextClassLoader() {
        ServiceLoader<Driver> serviceLoader = ServiceLoader.load(Driver.class);
        Iterator<Driver> iterator = serviceLoader.iterator();

        while (iterator.hasNext()) {
            Driver driver = iterator.next();
            System.out.println("driver : " + driver + ", loader : " + driver.getClass().getClassLoader());
        }
    }
}
