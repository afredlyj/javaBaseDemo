package afred.javademo.dispatcher.resteasy;

import afred.javademo.dispatcher.resteasy.domain.RequestData;
import afred.javademo.dispatcher.resteasy.domain.ResponseData;
import afred.javademo.dispatcher.resteasy.handler.SpringConfigurableHandler;
import org.jboss.resteasy.plugins.server.netty.NettyContainer;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.RuntimeDelegate;

import java.util.concurrent.TimeUnit;

import static org.jboss.resteasy.test.TestPortProvider.generateURL;
import static org.jboss.resteasy.util.PortProvider.getHost;

/**
 * Created by afred on 16/8/17.
 */
public class SpringTest {


    static Client client;

    static int port = 12345;

    @BeforeClass
    public static void setup() throws Exception
    {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("rest-easy.xml");

        int ioThreads = Runtime.getRuntime().availableProcessors();
        SpringContainer container = new SpringContainer(port, ioThreads);

        container.start(applicationContext);

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

        String path = "/spring/test";
        String url = String.format("http://%s:%d%s", getHost(), port, path);
        System.out.println("url : " + url);
        WebTarget target = client.target(url);
        String val = target.request().get(String.class);
        System.out.println("response : " + val);
        Assert.assertEquals("hello world", val);
//
    }

    @Test
    public void testPost() {


        String path = "/spring/post";
        String url = String.format("http://%s:%d%s", getHost(), port, path);

        WebTarget target = client.target(url);
        String postBody = "hello world";
        String result = (String) target.request().post(Entity.text(postBody), String.class);
        Assert.assertEquals(postBody, result);
    }

    @Test
    public void postData() throws InterruptedException {
        String path = "/spring/post/data";
        String url = String.format("http://%s:%d%s", getHost(), port, path);

        WebTarget target = client.target(url);
        RequestData postBody = new RequestData();
        postBody.setData("hello server");
        postBody.setId(10);
        String result = (String) target.request(MediaType.APPLICATION_JSON).post(Entity.json(postBody), String.class);

        System.out.println("result : " + result );
    }

    @Test
    public void spring() {
        ClassPathXmlApplicationContext con = new ClassPathXmlApplicationContext("rest-easy.xml");

        System.out.println(con.getBean(SpringConfigurableHandler.class));
    }

}
