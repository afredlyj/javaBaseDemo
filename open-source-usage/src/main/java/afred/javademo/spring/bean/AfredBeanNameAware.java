package afred.javademo.spring.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanNameAware;

/**
 * Created by afred on 16/6/19.
 */
public class AfredBeanNameAware implements BeanNameAware {

    private static final Logger logger = LoggerFactory.getLogger(AfredBeanNameAware.class);

    @Override
    public void setBeanName(String name) {
        logger.debug("AfredBeanNameAware name : {}", name);
    }
}
