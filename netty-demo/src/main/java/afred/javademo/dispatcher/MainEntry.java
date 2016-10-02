package afred.javademo.dispatcher;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import afred.javademo.dispatcher.resteasy.SpringContainer;

/**
 * Created by afred on 16/10/2.
 */
public class MainEntry {

    public static void main(String[] args) throws Exception {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("rest-easy.xml");

        int ioThreads = Runtime.getRuntime().availableProcessors();
        SpringContainer container = new SpringContainer(12345, ioThreads);

        container.start(applicationContext);

    }
}
