package afred.javademo.btrace.netty;

import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.Kind;
import com.sun.btrace.annotations.Location;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.ProbeClassName;
import com.sun.btrace.annotations.ProbeMethodName;
import com.sun.btrace.annotations.TLS;

import static com.sun.btrace.BTraceUtils.Strings.str;
import static com.sun.btrace.BTraceUtils.Strings.strcat;
import static com.sun.btrace.BTraceUtils.print;
import static com.sun.btrace.BTraceUtils.println;
import static com.sun.btrace.BTraceUtils.timeMillis;

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

    @OnMethod(clazz = "afred.demo.btrace.netty.HttpDispatcherHandler", method = "channelRead0"
            ,
            // 把type注释掉，btrace会打印两次相同的日志，不知道为啥
            type = "void channelRead0(io.netty.channel.ChannelHandlerContext, io.netty.handler.codec.http.FullHttpRequest)"
//            ,
//            location = @Location(Kind.ENTRY)
    )
      public static void traceStart(@ProbeClassName String probeClass, @ProbeMethodName String probeMethod) {
        println("start ===");
        print(strcat("entered ", probeClass));
        println(strcat(".", probeMethod));
        startTime = timeMillis();
    }

    @OnMethod(clazz = "afred.demo.btrace.netty.HttpDispatcherHandler", method = "channelRead0",
            type = "void channelRead0(io.netty.channel.ChannelHandlerContext, io.netty.handler.codec.http.FullHttpRequest)",
            location = @Location(Kind.RETURN))
    public static void traceEnd() {
        long endTime = timeMillis();
        println(strcat("elapsed(ms) : ", str(endTime - startTime)));
    }
}
