package afred.demo.mbean;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

/**
 * Created with IntelliJ IDEA.
 * User: 80059138
 * Date: 2015-02-11
 * Time: 10:52
 */
public class MXBean {

    public static void main(String[] args) {

        MemoryMXBean bean = ManagementFactory.getMemoryMXBean();
        Runtime runtime = Runtime.getRuntime();

        System.out.println(runtime.maxMemory());
        System.out.println(runtime.totalMemory());
        System.out.println(runtime.freeMemory());
        System.out.println(bean.getHeapMemoryUsage().getUsed());
        System.out.println(bean.getNonHeapMemoryUsage().getUsed());

        String ip = System.getProperty("host.ip");

        System.out.println("hello : " + ip);

    }

}
