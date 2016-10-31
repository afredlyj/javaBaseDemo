package afred.javademo.btrace.netty;

import com.sun.btrace.BTraceUtils;
import com.sun.btrace.Profiler;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.Duration;
import com.sun.btrace.annotations.Kind;
import com.sun.btrace.annotations.Location;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.OnTimer;
import com.sun.btrace.annotations.ProbeMethodName;
import com.sun.btrace.annotations.Property;
import com.sun.btrace.annotations.Self;
import com.sun.btrace.annotations.TLS;
import com.sun.btrace.annotations.TargetInstance;
import com.sun.btrace.annotations.TargetMethodOrField;
import com.sun.btrace.annotations.Where;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

import static com.sun.btrace.BTraceUtils.classForName;
import static com.sun.btrace.BTraceUtils.classOf;
import static com.sun.btrace.BTraceUtils.contextClassLoader;
import static com.sun.btrace.BTraceUtils.field;
import static com.sun.btrace.BTraceUtils.get;
import static com.sun.btrace.BTraceUtils.printNumberMap;
import static com.sun.btrace.BTraceUtils.println;

/**
 * Created by winnie on 2016-01-16 .
 */
@BTrace
public class ElaplsedBtrace {

    /**
     *
     * http://blog.sina.com.cn/s/blog_721770d901018kqt.html
     * http://www.iteye.com/topic/586630
     * http://alicsd.iteye.com/blog/803430
     * http://agapple.iteye.com/blog/1005918
     *
     */

    @TLS private static long startTime = 0;

    @Property
    Profiler profiler = BTraceUtils.Profiling.newProfiler();

    private static Field uriField = field(classForName("io.netty.handler.codec.http.DefaultHttpRequest", contextClassLoader()), "uri");


//    @OnMethod(clazz = "afred.javademo.btrace.netty.HttpDispatcherHandler", method = "channelRead0"
//            ,
//            // 把type注释掉，btrace会打印两次相同的日志，不知道为啥
//            type = "void channelRead0(io.netty.channel.ChannelHandlerContext, io.netty.handler.codec.http.FullHttpRequest)"
//            ,
//            location = @Location(Kind.ENTRY)
//    )
//      public static void traceStart(@ProbeClassName String probeClass, @ProbeMethodName String probeMethod) {
//        println("start ===");
//        print(strcat("entered ", probeClass));
//        println(strcat(".", probeMethod));
//        startTime = timeMillis();
//    }
//
//    @OnMethod(clazz = "afred.javademo.btrace.netty.HttpDispatcherHandler", method = "channelRead0",
//            type = "void channelRead0(io.netty.channel.ChannelHandlerContext, io.netty.handler.codec.http.FullHttpRequest)",
//            location = @Location(Kind.RETURN))
//    public static void traceEnd() {
//        long endTime = timeMillis();
//        println(strcat("elapsed(ms) : ", str(endTime - startTime)));
//    }

    @OnMethod(clazz = "io.netty.channel.SimpleChannelInboundHandler", method = "channelRead",
            location = @Location(value =  Kind.CALL, clazz = "/.*/", method = "/.*/", where = Where.AFTER))
    public static void onChannelRead(@Self Object self, @TargetInstance Object instance, @TargetMethodOrField String method, @Duration long duration, @ProbeMethodName String probeMethod) {

        println(self);
        println(method + " in " + probeMethod + ", elapsed : " + duration);
    }

    @OnMethod(clazz = "afred.javademo.btrace.netty.HttpDispatcherHandler", method = "channelRead0"
            ,
            type = "void channelRead0(io.netty.channel.ChannelHandlerContext, io.netty.handler.codec.http.FullHttpRequest)"
            ,
            location = @Location(Kind.ENTRY)
    )
    public static void printField(@Self Object self, ChannelHandlerContext ctx, FullHttpRequest msg) {

        println("url :" + get(uriField, msg));
    }

    @OnMethod(clazz = "afred.javademo.btrace.netty.HttpDispatcherHandler", method = "channelRead0")
    public static void jstackFor() {
        BTraceUtils.jstack();
    }

    private static Map<String, AtomicInteger> histo = BTraceUtils.Collections.newHashMap();

    @OnMethod(
            clazz="io.netty.handler.codec.http.DefaultHttpRequest",
            method="<init>"
    )
    public static void onnewObject(@Self Object obj) {
        String cn = BTraceUtils.Reflective.name(classOf(obj));
        AtomicInteger ai = BTraceUtils.Collections.get(histo, cn);
        if (ai == null) {
            ai = BTraceUtils.Atomic.newAtomicInteger(1);
            BTraceUtils.Collections.put(histo, cn, ai);
        } else {
            BTraceUtils.Atomic.incrementAndGet(ai);
        }
    }

    @OnTimer(4000)
    public static void print() {
        if (BTraceUtils.Collections.size(histo) != 0) {
            printNumberMap("Component Histogram", histo);
        }

    }

}
