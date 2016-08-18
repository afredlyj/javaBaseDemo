package afred.javademo.dispatcher.resteasy.handler;

import afred.javademo.dispatcher.resteasy.annotation.HttpHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

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


}
