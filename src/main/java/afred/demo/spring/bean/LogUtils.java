package afred.demo.spring.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by winnie on 15/12/12.
 */
public class LogUtils {

    private static final Logger logger = LoggerFactory.getLogger(LogUtils.class);


    public static void log(String format, Object... data) {

//        logger.debug("format : {}, data : {}", format, data);

        logger.debug("class : {}, " + format,
                Thread.currentThread().getStackTrace()[2],
                data);

    }

}
