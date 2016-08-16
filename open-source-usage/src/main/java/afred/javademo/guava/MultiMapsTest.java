package afred.javademo.guava;

import com.google.common.base.Joiner;
import com.google.common.collect.*;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by afred on 16/8/16.
 */
public class MultiMapsTest {

    class Pairs {
        private String key;

        private String values;

        public Pairs(String key, String values) {

            this.key = key;
            this.values = values;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValues() {
            return values;
        }

        public void setValues(String values) {
            this.values = values;
        }

        @Override
        public String toString() {
            return "Pairs{" +
                    "key='" + key + '\'' +
                    ", values='" + values + '\'' +
                    '}';
        }
    }

    @Test
    public void test() {

        HashMultimap<String, String> multimap = HashMultimap.create();


        multimap.put("1", "11");
        multimap.put("1", "111");
        multimap.put("1", "111");
        multimap.put("2", "22");
        multimap.put("2", "222");

        for (Map.Entry<String, String> each : multimap.entries()) {
            System.out.println(each);
        }

        Joiner joiner = Joiner.on(",");

        List<Pairs> list = Lists.newArrayList();

        for (String key : multimap.keySet()) {
            Set<String> values = multimap.get(key);

            String join = joiner.join(values);

            System.out.println("key : " + key + ", join : " + join);

            Pairs pairs = new Pairs(key, join);

            list.add(pairs);
        }

        for (Pairs each : list) {

            System.out.println("result : " + each );
        }
    }

}
