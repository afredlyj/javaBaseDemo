package afred.javademo.dispatcher.resteasy;

import org.aspectj.lang.annotation.Before;
import org.jboss.resteasy.plugins.server.netty.NettyContainer;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import static org.jboss.resteasy.test.TestPortProvider.generateURL;

/**
 * Created by afred on 16/8/17.
 */
public class SpringTest {


    static Client client;
    @BeforeClass
    public static void setup() throws Exception
    {

        SpringContainer.start();

        client = ClientBuilder.newClient();
    }

    @AfterClass
    public static void end() throws Exception
    {
        try
        {
            client.close();
        }
        catch (Exception e)
        {

        }
        NettyContainer.stop();
    }


    @Test
    public void testBasic() throws Exception
    {

        String url = generateURL("/spring/test");
        System.out.println("url : " + url);
        WebTarget target = client.target(url);
        String val = target.request().get(String.class);
        System.out.println("response : " + val);
        Assert.assertEquals("hello world", val);
//
//        TimeUnit.SECONDS.sleep(60);
    }

    @Test
    public void spring() {
        ClassPathXmlApplicationContext con = new ClassPathXmlApplicationContext("rest-easy.xml");

        System.out.println(con.getBean(SpringConfigurableHandler.class));
    }

}
