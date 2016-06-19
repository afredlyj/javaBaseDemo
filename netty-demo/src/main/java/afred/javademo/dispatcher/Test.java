package afred.javademo.dispatcher;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by afred on 16/6/2.
 */
public class Test {

    @org.junit.Test
    public void test() {
        Map<String, String> map = Maps.newHashMap();

        System.out.println(map.getClass().getTypeParameters());
    }

}
