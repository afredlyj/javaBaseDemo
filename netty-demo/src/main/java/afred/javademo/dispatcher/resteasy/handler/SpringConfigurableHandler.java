package afred.javademo.dispatcher.resteasy.handler;

import afred.javademo.dispatcher.resteasy.annotation.HttpHandler;
import afred.javademo.dispatcher.resteasy.domain.RequestData;
import afred.javademo.dispatcher.resteasy.domain.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by afred on 16/8/17.
 */

@HttpHandler
@Path("/spring")
public class SpringConfigurableHandler {

    private static final Logger logger = LoggerFactory.getLogger(SpringConfigurableHandler.class);

    @GET
    @Path("/test")
//    @Produces("text/plain")
    public String hello() {

        logger.debug("request : ");
        return "hello world";
    }

    @POST
    @Path("/post")
    @Produces("text/plain")
    public String post(String postBody) {
        return postBody;
    }

    @POST
    @Path("/post/data")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseData post(RequestData data) {
        logger.debug("收到的请求 : {}", data);

        ResponseData responseData = new ResponseData();
        responseData.setData("hello client");

        return responseData;
    }

}
