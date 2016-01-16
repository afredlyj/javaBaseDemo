package afred.demo.btrace;

import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.Strings.str;
import static com.sun.btrace.BTraceUtils.Strings.strcat;
import static com.sun.btrace.BTraceUtils.print;
import static com.sun.btrace.BTraceUtils.println;
import static com.sun.btrace.BTraceUtils.timeNanos;

/**
 * Created by winnie on 16/1/16.
 */
@BTrace
public class TraceScript {


    private static int record = 0;

    @TLS private static long startTime = 0;

    /**
     * 启动命令
     * btrace <PID> <Path\>TraceScript.java
     * @param num
     * @param result
     */
    @OnMethod(clazz = "afred.demo.btrace.Counter", method = "add", location = @Location(Kind.RETURN))
    public static void traceEnd(int num,@Return int result) {

        println("======");
        println(strcat("parameter num: ",str(num)));
        println(strcat("return value:",str(result)));
        record = result;

        long endTime = timeNanos();

        println(strcat("add 方法耗时", str(endTime - startTime)));
    }

    @OnMethod(clazz = "afred.demo.btrace.Counter", method = "add")
    public static void traceStart(int num) {

        startTime = timeNanos();
    }

    @OnTimer(1000)
    public static void print() {
        println("定时打印====");
        println(str(record));
    }

}
