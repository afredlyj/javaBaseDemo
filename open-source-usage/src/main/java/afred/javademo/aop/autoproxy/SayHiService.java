package afred.javademo.aop.autoproxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by afred on 16/11/23.
 */
@Service("sayHiService")
public class SayHiService {

    private static final Logger logger = LoggerFactory.getLogger(SayHiService.class);

    public void sayHi(String name) {
        logger.info("hello, {}", name);
    }

}
