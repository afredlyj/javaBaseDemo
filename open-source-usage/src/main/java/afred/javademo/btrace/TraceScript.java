package afred.javademo.btrace;

import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.Kind;
import com.sun.btrace.annotations.Location;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.Return;
import com.sun.btrace.annotations.TLS;

import static com.sun.btrace.BTraceUtils.Strings.str;
import static com.sun.btrace.BTraceUtils.Strings.strcat;
import static com.sun.btrace.BTraceUtils.println;
import static com.sun.btrace.BTraceUtils.timeNanos;

/**
 * Created by winnie on 16/1/16.
 */
@BTrace
public class TraceScript {

    // 定义thread local
    @TLS private static long startTime = 0;

    /**
     * 启动
     * btrace <PID> <Path\>TraceScript.java
     * @param num
     * @param result
     */
    @OnMethod(clazz = "afred.demo.btrace.Counter", method = "add", location = @Location(Kind.RETURN))
    public static void traceEnd(int num,@Return int result) {

        println("======");
        println(strcat("parameter num: ",str(num)));
        println(strcat("return value:",str(result)));

        long endTime = timeNanos();

        println(strcat("add elapsed ", str(endTime - startTime)));
        println();
    }

    @OnMethod(clazz = "afred.demo.btrace.Counter", method = "add")
    public static void traceStart(int num) {
        println("start======");

        startTime = timeNanos();
        println();

    }



}
