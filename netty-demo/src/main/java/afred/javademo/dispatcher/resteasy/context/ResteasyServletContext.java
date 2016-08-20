package afred.javademo.dispatcher.resteasy.context;

import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Created by afred on 16/8/20.
 */

@Component
@Provider
public class ResteasyServletContext implements ContainerRequestFilter {

    @Context
    ServletContext sc;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {

        ResteasyProviderFactory.getContextDataMap().put(ServletContext.class, sc);
    }
}
