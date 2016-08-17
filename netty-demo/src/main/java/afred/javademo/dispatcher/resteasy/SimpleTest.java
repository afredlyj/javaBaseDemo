package afred.javademo.dispatcher.resteasy;

import io.netty.channel.ChannelHandlerContext;

import io.netty.channel.ChannelHandlerContext;

import org.jboss.resteasy.plugins.server.netty.NettyContainer;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static org.jboss.resteasy.test.TestPortProvider.generateURL;
import static org.jboss.resteasy.test.TestPortProvider.getHost;
import static org.jboss.resteasy.test.TestPortProvider.getPort;

import org.jboss.resteasy.plugins.server.*;


/**
 * Created by afred on 16/8/14.
 */
public class SimpleTest {

    @Path("/")
    public static class Resource
    {
        @GET
        @Path("/test")
        @Produces("text/plain")
        public String hello()
        {

            System.out.println("hello : ");
            return "hello world";
        }

        @GET
        @Path("empty")
        public void empty() {

        }

        @GET
        @Path("query")
        public String query(@QueryParam("param") String value) {
            return value;

        }


        @GET
        @Path("/exception")
        @Produces("text/plain")
        public String exception() {
            throw new RuntimeException();
        }

        @GET
        @Path("large")
        @Produces("text/plain")
        public String large() {
            StringBuffer buf = new StringBuffer();
            for (int i = 0; i < 1000; i++) {
                buf.append(i);
            }
            return buf.toString();
        }

        @GET
        @Path("/context")
        @Produces("text/plain")
        public String context(@Context ChannelHandlerContext context) {
            return context.channel().toString();
        }

        @POST
        @Path("/post")
        @Produces("text/plain")
        public String post(String postBody) {
            return postBody;
        }

        @GET
        @Path("/test/absolute")
        @Produces("text/plain")
        public String absolute(@Context UriInfo info)
        {
            return "uri: " + info.getRequestUri().toString();
        }
    }

    static Client client;
    @BeforeClass
    public static void setup() throws Exception
    {
//        NettyContainer.start().getRegistry().addPerRequestResource(Resource.class);
        NettyContainer.start().getRegistry().addSingletonResource(new Resource());

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

        String url = generateURL("/test");
        System.out.println("url : " + url);
        WebTarget target = client.target(url);
        String val = target.request().get(String.class);
        System.out.println("response : " + val);
        Assert.assertEquals("hello world", val);
//
//        TimeUnit.SECONDS.sleep(60);
    }

    @Test
    public void testQuery() throws Exception
    {
        WebTarget target = client.target(generateURL("/query"));
        String val = target.queryParam("param", "val").request().get(String.class);
        Assert.assertEquals("val", val);
    }

    @Test
    public void testEmpty() throws Exception
    {
        WebTarget target = client.target(generateURL("/empty"));
        Response response = target.request().get();
        try
        {
            Assert.assertEquals(204, response.getStatus());
        }
        finally
        {
            response.close();
        }
    }

    @Test
    public void testLarge() throws Exception
    {
        WebTarget target = client.target(generateURL("/large"));
        Response response = target.request().get();
        try
        {
            Assert.assertEquals(200, response.getStatus());
            StringBuffer buf = new StringBuffer();
            for (int i = 0; i < 1000; i++) {
                buf.append(i);
            }
            String expected = buf.toString();
            String have = response.readEntity(String.class);
            Assert.assertEquals(expected, have);

        }
        finally
        {
            response.close();
        }
    }

    @Test
    public void testUnhandledException() throws Exception
    {
        WebTarget target = client.target(generateURL("/exception"));
        Response resp = target.request().get();
        try
        {
            Assert.assertEquals(500, resp.getStatus());
        }
        finally
        {
            resp.close();
        }
    }

}
