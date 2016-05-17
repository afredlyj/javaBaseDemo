package afred.javademo.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with basicdemo. 
 * User: liyuanjun(80059138) 
 * Date: 2016-05-05 
 * Time: 10:23
 */
public class Listener {

    private static final Logger logger = LoggerFactory.getLogger(Listener.class);

    private AtomicInteger counter = new AtomicInteger();

    public void listen(String data) {
        logger.debug("收到数据 : {}, {}", data, counter.getAndIncrement());
//        throw new RuntimeException();
    }
}
