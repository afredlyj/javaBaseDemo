package afred.javademo.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by afred on 17/2/11.
 */
public class CacheTest {

    private static final Logger logger = LoggerFactory.getLogger(CacheTest.class);

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-cache.xml");

        AccountService service = context.getBean(AccountService.class);

        Account result = service.get("hello");

        logger.debug("查询结果 : {}", result);

        logger.debug("再次查询  : {}", service.get("hello"));
        logger.debug("再次查询  : {}", service.get("helloworld"));
    }

}
