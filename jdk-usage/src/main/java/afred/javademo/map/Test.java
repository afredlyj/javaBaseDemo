package afred.javademo.map;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by winnie on 2016-04-24 .
 */
public class Test {

    @org.junit.Test
    public void linkedHashMap() {
        LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
        hashMap.put("1", "1");
        hashMap.put("2", "2");
        hashMap.put("3", "3");

        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            System.out.println(entry.getKey());
        }
    }

}
