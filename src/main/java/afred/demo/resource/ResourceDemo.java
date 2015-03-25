package afred.demo.resource;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by winnie on 2015-03-25 .
 */
public class ResourceDemo {

    public static void main(String[] args) {

        /**
         * "/"-separated names; no leading "/" (all names are absolute)
         */
        InputStream inputStream = ResourceDemo.class.getClassLoader().getResourceAsStream("afred/demo/resource/demo1.txt");

        URL url = ResourceDemo.class.getClassLoader().getResource("afred/demo/resource/demo1.txt");
        System.out.println("url : " + url);
        System.out.println("inputstrem : " + inputStream);

        /**
         * "/"-separated names; leading "/" indicates absolute names; all other names are relative to the class's package
         */
        inputStream = ResourceDemo.class.getResourceAsStream("demo1.txt");

        url = ResourceDemo.class.getResource("demo1.txt");
        System.out.println("url : " + url);

        System.out.println("inputstrem : " + inputStream);

    }

}
