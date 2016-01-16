package afred.demo.btrace;

import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.OnMethod;

import static com.sun.btrace.BTraceUtils.println;

/**
 * Created by winnie on 16/1/16.
 */
@BTrace
public class HelloWorldTraceScipt {


    @OnMethod(clazz = "java.lang.Thread", method = "start")
    void func() {
        sharedMethod("hello world");
    }

    void sharedMethod(String msg) {
        println(msg);
    }
}
