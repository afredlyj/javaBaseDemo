package afred.javademo.spi;

import java.util.ServiceLoader;

/**
 * Created by afred on 16/10/6.
 */
public class SpiTest {

    public static void main(String[] args) {

        /**
         * 在classpath路径下创建文件夹
         * META-INF/services/[接口的完整地址]
         */
        ServiceLoader<HelloService> services = ServiceLoader.load(HelloService.class);
        for (HelloService helloService : services) {
            helloService.sayHello();
        }
    }
}
