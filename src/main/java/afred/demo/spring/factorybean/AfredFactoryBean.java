package afred.demo.spring.factorybean;

import afred.demo.spring.bean.LogUtils;
import afred.demo.spring.bean.People;
import org.springframework.beans.factory.FactoryBean;

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
