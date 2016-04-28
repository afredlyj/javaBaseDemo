package afred.javademo.spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Service;

/**
 * Created by winnie on 15/12/12.
 */
//@Service("beanFactoryPostProcessor")
public class AfredBeanFactoryPostProcessor implements BeanFactoryPostProcessor{

    public AfredBeanFactoryPostProcessor() {
        LogUtils.log("执行构造函数");
    }

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        LogUtils.log("AfredBeanFactoryPostProcessor.beanFactoryPostProcessor");
    }
}
