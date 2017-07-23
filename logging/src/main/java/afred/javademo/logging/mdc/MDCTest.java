package afred.javademo.logging.mdc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.UUID;

/**
 * Created by afred on 17/5/21.
 */
public class MDCTest {

    private static final Logger logger = LoggerFactory.getLogger(MDCTest.class);

    public static void main(String[] args) {
        MDC.put("traceId", UUID.randomUUID().toString());

        logger.debug("hello world");
    }

}
