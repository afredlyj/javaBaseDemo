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

/**
 * Created by afred on 16/10/30.
 */
@BTrace
public class ProfileTest {

    @Property
    static Profiler profiler = BTraceUtils.Profiling.newProfiler();


        @OnMethod(clazz = "afred.javademo.btrace.netty.HttpDispatcherHandler", method = "channelRead0"
//            ,
            // 把type注释掉，btrace会打印两次相同的日志，不知道为啥
//            type = "void channelRead0(io.netty.channel.ChannelHandlerContext, io.netty.handler.codec.http.FullHttpRequest)"
//            ,
//            location = @Location(Kind.ENTRY)
        )
    public static void entry( @ProbeMethodName(fqn=true) String probeMethod) {
            BTraceUtils.Profiling.recordEntry(profiler, probeMethod);
    }

        @OnMethod(clazz = "afred.javademo.btrace.netty.HttpDispatcherHandler", method = "channelRead0"
                ,
//            type = "void channelRead0(io.netty.channel.ChannelHandlerContext, io.netty.handler.codec.http.FullHttpRequest)",
            location = @Location(Kind.RETURN)
        )
    public static void exit(@ProbeMethodName(fqn=true) String probeMethod, @Duration long duration) {

            BTraceUtils.Profiling.recordExit(profiler, probeMethod, duration);
    }

    @OnTimer(5000)
    public  static  void timer() {
        BTraceUtils.Profiling.printSnapshot("HttpDispatcherHandler performance profile", profiler);
    }
}
