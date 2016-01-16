package afred.demo.spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

import java.beans.PropertyDescriptor;

/**
 * Created by winnie on 15/12/12.
 */
public class AfredInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {

    public AfredInstantiationAwareBeanPostProcessor() {
        super();
        LogUtils.log("调用构造函数");
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        LogUtils.log("property value : {}", pvs);
        return super.postProcessPropertyValues(pvs, pds, bean, beanName);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        LogUtils.log("bean : {}, bean name : {}", bean, beanName);

        return super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        LogUtils.log("bean : {}, bean name : {}", bean, beanName);

        return super.postProcessAfterInitialization(bean, beanName);
    }
}
