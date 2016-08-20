package afred.javademo.dispatcher.resteasy;

import afred.javademo.dispatcher.resteasy.annotation.HttpHandler;
import com.google.common.base.Preconditions;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.jboss.resteasy.spi.ResteasyDeployment;
import org.jboss.resteasy.util.PortProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import javax.ws.rs.ext.Provider;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by afred on 16/8/17.
 */
public class SpringContainer {

    private static final Logger logger = LoggerFactory.getLogger(SpringContainer.class);

    private int port;

    private int ioThreads;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getIoThreads() {
        return ioThreads;
    }

    public void setIoThreads(int ioThreads) {
        this.ioThreads = ioThreads;
    }

    public SpringContainer(int port, int ioThreads) {

        Preconditions.checkArgument(port > 0, "port illegal");
        Preconditions.checkArgument(ioThreads > 0, "io thread num illegal");

        this.port = port;
        this.ioThreads = ioThreads;
    }

    public ConfigurableNettyJaxrsServer start(ApplicationContext context) throws Exception {

        ResteasyDeployment deployment = new ResteasyDeployment();

        Collection<Object> beansWithAnnotation = context.getBeansWithAnnotation(HttpHandler.class).values();

        if (beansWithAnnotation != null && !beansWithAnnotation.isEmpty()) {
            deployment.getResources().addAll(beansWithAnnotation);
        }


//        ApiListingResource listingResource = context.getBean(ApiListingResource.class);
//
//        logger.debug("api list resource bean : {}", listingResource);
//        if (listingResource != null) {
//            deployment.getResources().add(listingResource);
//        }

//        SwaggerSerializers swaggerSerializers = context.getBean(SwaggerSerializers.class);
//        logger.debug("swagger serializers bean : {}", swaggerSerializers);
//        if (swaggerSerializers != null) {
//            deployment.getProviders().add(swaggerSerializers);
//        }

//        Collection<Object> providers = context.getBeansWithAnnotation(Provider.class).values();
//        if (providers != null) {
//            deployment.getProviders().addAll(providers);
//        }
//
        ConfigurableNettyJaxrsServer netty = new ConfigurableNettyJaxrsServer(ioThreads);
        netty.setDeployment(deployment);
        netty.setPort(port);
        netty.setRootResourcePath("");
        netty.setSecurityDomain(null);
        netty.start();
        return netty;
    }

}
