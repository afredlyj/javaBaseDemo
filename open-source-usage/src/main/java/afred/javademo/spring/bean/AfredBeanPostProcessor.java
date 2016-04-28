package afred.javademo.spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Service;

/**
 * Created by winnie on 15/12/12.
 */
public class AfredBeanPostProcessor implements BeanPostProcessor {
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        LogUtils.log("bean : {}, name : {}", bean, beanName);
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        LogUtils.log("bean : {}, name : {}", bean, beanName);
        return bean;
    }
}
