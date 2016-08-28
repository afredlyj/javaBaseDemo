package afred.javademo.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with basicdemo. 
 * User: liyuanjun(80059138) 
 * Date: 2015-12-29 
 * Time: 14:45
 */
public class Performer {

    private static final Logger logger = LoggerFactory.getLogger(Performer.class);

    public String perform() {
        logger.info("表演开始");
        doPerform("猴子偷桃");
        return "hello";
    }

    public void doPerform(String name) {
        logger.info("开始表演 : {}", name);
        return;
    }

    public void perform1(String name) {
        logger.info("表演节目 : {}", name);
    }

}
