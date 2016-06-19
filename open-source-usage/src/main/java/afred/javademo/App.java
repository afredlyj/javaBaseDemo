package afred.javademo;

import org.springframework.core.io.FileSystemResource;

import java.io.IOException;
import java.io.InputStream;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main(String[] args) throws IOException {

        FileSystemResource fileSystemResource = new FileSystemResource("bean/LogUtils.java");

        System.out.println(fileSystemResource.exists());

        fileSystemResource = new FileSystemResource(".idea");

        System.out.println(fileSystemResource.exists());

        System.out.println(fileSystemResource.getPath());

        System.out.println(fileSystemResource.getURL().getPath());


        fileSystemResource = new FileSystemResource("classpath:logback.xml");

        System.out.println(fileSystemResource.exists());

        System.out.println(fileSystemResource.getPath());

        System.out.println(fileSystemResource.getURL().getPath());

    }
}
