package afred.javademo.spring.factorybean;

import org.springframework.beans.factory.FactoryBean;

import afred.javademo.spring.bean.LogUtils;
import afred.javademo.spring.bean.People;


/**
 * Created by winnie on 15/12/12.
 */

public class AfredFactoryBean implements FactoryBean<People> {
    public People getObject() throws Exception {
        LogUtils.log("get object");
        return new People("afred", 18);
    }

    public Class<?> getObjectType() {
        LogUtils.log("get object : {}", "");

        return People.class;
    }

    public boolean isSingleton() {
        LogUtils.log("get object");

        return true;
    }
}
