package afred.javademo.dispatcher.resteasy.handler;

import afred.javademo.dispatcher.resteasy.annotation.HttpHandler;
import afred.javademo.dispatcher.resteasy.domain.RequestData;
import afred.javademo.dispatcher.resteasy.domain.ResponseData;
import org.jboss.resteasy.specimpl.ResteasyHttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    public ResponseData post(@Valid RequestData data, @Context HttpHeaders headers) {
        logger.debug("收到的请求 : {}", data);

        for (Map.Entry<String, List<String>> each : headers.getRequestHeaders().entrySet()) {
            logger.debug("headers : {}, {}", each.getKey(), each.getValue());
        }

        ResponseData responseData = new ResponseData();
        responseData.setData("hello client");

        return responseData;
    }

}
