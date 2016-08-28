package afred.javademo.dispatcher.resteasy.handler;

import afred.javademo.dispatcher.resteasy.annotation.HttpHandler;
import afred.javademo.dispatcher.resteasy.domain.Header;
import afred.javademo.dispatcher.resteasy.domain.RequestData;
import afred.javademo.dispatcher.resteasy.domain.ResponseData;
import io.swagger.annotations.*;
import org.jboss.resteasy.annotations.Form;
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
@Api(value = "/spring", description = "Operations about pets")
@Path("/spring")
public class SpringConfigurableHandler {

    private static final Logger logger = LoggerFactory.getLogger(SpringConfigurableHandler.class);

    @GET
    @Path("/{petId}")
    @ApiOperation(value = "Find pet by ID",
            notes = "Returns a pet when ID <= 10.  ID > 10 or non-integers will simulate API error conditions"
    )
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Pet not found") })
    public String getPetById(
            @ApiParam(value = "ID of pet that needs to be fetched", allowableValues = "range[1,10]", required = true)
            @PathParam("petId") Long petId) throws NotFoundException {

        return "hello";
    }


    @GET
    @Path("/test")
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
    public ResponseData post(@Valid RequestData data, @Context HttpHeaders headers, @Form Header header) {
        logger.debug("收到的请求 : {}, {}", data, header);

        for (Map.Entry<String, List<String>> each : headers.getRequestHeaders().entrySet()) {
            logger.debug("headers : {}, {}", each.getKey(), each.getValue());
        }

        ResponseData responseData = new ResponseData();
        responseData.setData("hello client");

        return responseData;
    }

}
