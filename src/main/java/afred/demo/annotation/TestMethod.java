package afred.demo.annotation;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * Created with basicdemo. 
 * User: liyuanjun(80059138) 
 * Date: 2016-03-03 
 * Time: 16:28
 */
public class TestMethod {

    @Test public void methodAnno() throws NoSuchMethodException {
        Method method = IUserInfoService.class.getDeclaredMethod("update");
        MethodUrl url = method.getAnnotation(MethodUrl.class);
        System.out.println(url.value());
        Assert.assertTrue("Update".equals(url.value()));
    }

    @Test public void notInherited() throws NoSuchMethodException {

        Method method = UserInfoServiceImpl.class.getDeclaredMethod("update");
        MethodUrl url = method.getAnnotation(MethodUrl.class);
        Assert.assertNull(url);
    }

}
