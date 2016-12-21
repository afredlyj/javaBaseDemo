package afred.javademo.spring.bean;

import org.junit.Test;

/**
 * Created by afred on 16/11/22.
 */
public class AppTest {


    @Test
    public void str() {
        String hello = "h";
        String world = "h";

        System.out.println(hello == world);

        String str = new StringBuilder("h").toString();
        System.out.println(str == hello);
    }

}
