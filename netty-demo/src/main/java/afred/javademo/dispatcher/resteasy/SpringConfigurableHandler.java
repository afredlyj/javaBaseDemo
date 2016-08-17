package afred.javademo.dispatcher.resteasy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by afred on 16/8/17.
 */

@Controller
@Path("/spring")
public class SpringConfigurableHandler {

    private static final Logger logger = LoggerFactory.getLogger(SpringConfigurableHandler.class);

    @GET
    @Path("/test")
//    @Produces("text/plain")
    public String hello() {
        return "hello world";
    }


}
