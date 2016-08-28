package afred.javademo.tool;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 * Created with basicdemo. 
 * User: liyuanjun(80059138) 
 * Date: 2015-09-21 
 * Time: 15:03
 */
public class Pid {

    public static int getPid() {
        int pid = 0;
            try {
                RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
                String name = runtime.getName(); // format: "pid@hostname"
                pid = Integer.parseInt(name.substring(0, name.indexOf('@')));
            } catch (Throwable e) {
                pid = 0;
            }
        return pid;
    }

    public static void main(String[] args) {
        System.out.println("pid : " + getPid());
    }

}
